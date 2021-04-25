package com.springbank.user.cmd.api.commands;

import com.springbank.user.core.models.*;
import lombok.*;
import org.axonframework.modelling.command.*;

@Data
@Builder
public class RegisterUserCommand {

    @TargetAggregateIdentifier
    private String id;

    private User user;
}
