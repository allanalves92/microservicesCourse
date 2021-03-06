package com.springbank.user.cmd.api.controllers;

import com.springbank.user.cmd.api.commands.*;
import com.springbank.user.cmd.api.dto.*;
import lombok.extern.slf4j.*;
import org.axonframework.commandhandling.gateway.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping(path = "/api/v1/registerUser")
@Slf4j
public class RegisterUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command) {
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.send(command);

            return new ResponseEntity<>(new RegisterUserResponse(id, "User successfully " +
                    "registered!"), HttpStatus.CREATED);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing register user request for id - " + id;
            log.error(e.toString());

            return new ResponseEntity<>(new RegisterUserResponse(id, safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
