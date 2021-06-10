package com.springbank.bankacc.cmd.api.controllers;

import com.springbank.bankacc.cmd.api.commands.*;
import com.springbank.bankacc.core.dto.*;
import org.axonframework.commandhandling.gateway.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping("api/v1/withdrawFunds")
public class WithdrawController {

    private final CommandGateway commandGateway;

    @Autowired
    public WithdrawController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable(value = "id") String id,
                                                     @Valid @RequestBody WithdrawFundsCommand command) {
        try {
            command.setId(id);
            commandGateway.send(command).get();

            return new ResponseEntity<>(new BaseResponse("Withdraw successfully completed"),
                    HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage =
                    "Error while request to withdraw funds from bank account for " + "id" + " - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
