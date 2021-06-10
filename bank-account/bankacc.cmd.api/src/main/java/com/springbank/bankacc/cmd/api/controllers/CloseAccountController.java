package com.springbank.bankacc.cmd.api.controllers;

import com.springbank.bankacc.cmd.api.commands.*;
import com.springbank.bankacc.core.dto.*;
import org.axonframework.commandhandling.gateway.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/closeBankAccount")
public class CloseAccountController {

    private final CommandGateway commandGateway;

    @Autowired
    public CloseAccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> closeAccount(@PathVariable(value = "id") String id) {
        try {
            var command = CloseAccountCommand.builder().id(id).build();

            commandGateway.send(command);

            return new ResponseEntity<>(new BaseResponse("Bank account successfully closed!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage =
                    "Error while processing request to close bank account for id " + "-" + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
