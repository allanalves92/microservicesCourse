package com.springbank.bankacc.cmd.api.commands;

import lombok.*;
import org.axonframework.modelling.command.*;

import javax.validation.constraints.*;

@Data
@Builder
public class WithdrawFundsCommand {
    @TargetAggregateIdentifier
    private String id;

    @Min(value = 1, message = "The withdraw amount must be greater than 0")
    private double amount;
}
