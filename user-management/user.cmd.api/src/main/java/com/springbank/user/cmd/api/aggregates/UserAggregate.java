package com.springbank.user.cmd.api.aggregates;

import com.springbank.user.cmd.api.commands.*;
import com.springbank.user.cmd.api.security.*;
import com.springbank.user.core.events.*;
import com.springbank.user.core.models.*;
import org.axonframework.commandhandling.*;
import org.axonframework.eventsourcing.*;
import org.axonframework.modelling.command.*;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.*;

@Aggregate
public class UserAggregate {

    private final PasswordEncoder passwordEncoder;

    @AggregateIdentifier
    private String id;
    private User user;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        var newUser = command.getUser();
        newUser.setId(command.getId());
        var password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        var hashedPassword = passwordEncoder.hashPassword(password);
        newUser.getAccount().setPassword(hashedPassword);

        var event = UserRegisteredEvent.builder().id(command.getId()).user(newUser).build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        var password = updatedUser.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        updatedUser.getAccount().setPassword(hashedPassword);

        var event =
                UserUpdatedEvent.builder().id(UUID.randomUUID().toString()).user(updatedUser).build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var event = new UserRemovedEvent();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
