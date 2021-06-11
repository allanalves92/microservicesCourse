package com.springbank.bankacc.query.api.controllers;

import com.springbank.bankacc.query.api.dto.*;
import com.springbank.bankacc.query.api.queries.*;
import org.axonframework.messaging.responsetypes.*;
import org.axonframework.queryhandling.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bankAccountLookup")
public class AccountLookupController {

    private final QueryGateway queryGateway;

    @Autowired
    public AccountLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAllAccounts() {
        try {
            var query = new FindAllAccountsQuery();
            var response = queryGateway.query(query,
                    ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all accounts request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountById(@PathVariable(value = "id") String id) {
        try {
            var query = new FindAccountByIdQuery(id);
            var response = queryGateway.query(query,
                    ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account by ID request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byHolderId/{accountHolderId}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountByHolderId(@PathVariable(value = "accountHolderId") String accountHolderId) {
        try {
            var query = new FindAccountByHolderIdQuery(accountHolderId);
            var response = queryGateway.query(query,
                    ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account by Holder ID request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/withBalance/{equalityType}/{balance}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccountWithBalance(@PathVariable(value =
            "equalityType") EqualityType equalityType, @PathVariable(value = "balance") double balance) {
        try {
            var query = new FindAccountWithBalanceQuery(equalityType, balance);
            var response = queryGateway.query(query,
                    ResponseTypes.instanceOf(AccountLookupResponse.class)).join();

            if (response == null || response.getAccounts() == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account with balance request";
            System.out.println(e.toString());

            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
