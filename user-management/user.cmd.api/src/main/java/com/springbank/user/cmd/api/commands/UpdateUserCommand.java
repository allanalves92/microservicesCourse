package com.springbank.user.cmd.api.commands;

import com.springbank.user.core.models.*;
import lombok.*;
import org.axonframework.modelling.command.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Data
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "no user details were supplied")
    @Valid
    private User user;
}
