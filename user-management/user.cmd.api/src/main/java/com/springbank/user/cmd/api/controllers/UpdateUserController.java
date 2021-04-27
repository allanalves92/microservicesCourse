package com.springbank.user.cmd.api.controllers;

import com.springbank.user.cmd.api.commands.*;
import com.springbank.user.core.dto.*;
import org.axonframework.commandhandling.gateway.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
@RequestMapping(path = "/api/v1/updateUser")
public class UpdateUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public UpdateUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id,
                                                   @Valid @RequestBody UpdateUserCommand command) {
        try {
            command.setId(id);
            commandGateway.send(command);

            return new ResponseEntity<>(new BaseResponse("User successfully updated!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing update user request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
