package com.springbank.bankacc.cmd.api.controllers;

import com.springbank.bankacc.cmd.api.commands.*;
import com.springbank.bankacc.cmd.api.dto.*;
import org.axonframework.commandhandling.gateway.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/openBankAccount")
public class OpenAccountController {

    private final CommandGateway commandGateway;

    @Autowired
    public OpenAccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<OpenAccountResponse> openAccount(@Valid @RequestBody OpenAccountCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.send(command);

            return new ResponseEntity<>(new OpenAccountResponse(id, "successfully opened a new " +
                    "bank account!"), HttpStatus.CREATED);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request to open a new bank account for" +
                    " id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new OpenAccountResponse(id, safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
