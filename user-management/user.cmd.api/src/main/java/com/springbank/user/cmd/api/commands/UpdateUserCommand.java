package com.springbank.user.cmd.api.commands;

import com.springbank.user.core.models.*;
import lombok.*;
import org.axonframework.modelling.command.*;

import javax.validation.*;

@Data
@Builder
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String id;

    private User user;
}
