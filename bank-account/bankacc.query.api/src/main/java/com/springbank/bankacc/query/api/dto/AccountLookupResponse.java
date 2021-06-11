package com.springbank.bankacc.query.api.dto;

import com.springbank.bankacc.core.dto.*;
import com.springbank.bankacc.core.models.*;
import lombok.*;

import java.util.*;

public class AccountLookupResponse extends BaseResponse {

    @Getter
    @Setter
    private List<BankAccount> accounts;

    public AccountLookupResponse(String message) {
        super(message);
    }

    public AccountLookupResponse(String message, List<BankAccount> accounts) {
        super(message);
        this.accounts = accounts;
    }

    public AccountLookupResponse(String message, BankAccount account) {
        super(message);
        this.accounts = new ArrayList<>();
        this.accounts.add(account);
    }


}
