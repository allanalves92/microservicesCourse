package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.query.api.dto.*;
import com.springbank.bankacc.query.api.queries.*;

public interface AccountQueryHandler {

    AccountLookupResponse findAccountById(FindAccountByIdQuery query);
    AccountLookupResponse findAccountByHolderId(FindAccountByHolderId query);
    AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);
    AccountLookupResponse findAccountWithBalance(FindAccountWithBalanceQuery query);
}
