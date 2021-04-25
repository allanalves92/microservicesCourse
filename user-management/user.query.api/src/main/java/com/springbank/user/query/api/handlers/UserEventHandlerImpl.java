package com.springbank.user.query.api.handlers;

import com.springbank.user.core.events.*;
import com.springbank.user.query.api.repositories.*;
import org.axonframework.config.*;
import org.axonframework.eventhandling.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserEventHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
