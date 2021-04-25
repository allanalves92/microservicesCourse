package com.springbank.user.cmd.api.commands;

import lombok.*;
import org.axonframework.modelling.command.*;

@Data
public class RemoveUserCommand {

    @TargetAggregateIdentifier
    private String id;
}
