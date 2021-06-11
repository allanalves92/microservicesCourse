package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.core.models.*;
import com.springbank.bankacc.query.api.dto.*;
import com.springbank.bankacc.query.api.queries.*;
import com.springbank.bankacc.query.api.repositories.*;
import org.axonframework.queryhandling.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

public class AccountQueryHandlerImpl implements AccountQueryHandler {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
        var bankAccount = accountRepository.findById(query.getId());
        var response = bankAccount.isPresent() ? new AccountLookupResponse("Bank Account " +
                "successfully returned!", bankAccount.get()) : new AccountLookupResponse("No " +
                "Bank Account found for ID - " + query.getId());

        return response;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountByHolderId(FindAccountByHolderId query) {
        var bankAccount = accountRepository.findByAccountHolder(query.getAccountHolderId());
        var response = bankAccount.isPresent() ? new AccountLookupResponse("Bank Account " +
                "successfully returned!", bankAccount.get()) : new AccountLookupResponse("No " +
                "Bank Account found for Holder ID - " + query.getAccountHolderId());

        return response;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
        var bankAccountIterator = accountRepository.findAll();

        if (!bankAccountIterator.iterator().hasNext()) {
            return new AccountLookupResponse("No Bank Accounts were found!");
        }

        var bankAccounts = new ArrayList<BankAccount>();
        bankAccountIterator.forEach(i -> bankAccounts.add(i));
        var count = bankAccounts.size();

        return new AccountLookupResponse("Successfully Returned " + count + " Bank Accounts(s)!",
                bankAccounts);
    }

    @QueryHandler
    @Override
    public AccountLookupResponse findAccountWithBalance(FindAccountWithBalanceQuery query) {

        var bankAccounts = query.getEqualityType() == EqualityType.GREATER_THAN ?
                accountRepository.findByBalanceGreaterThan(query.getBalance()) :
                accountRepository.findByBalanceLessThan(query.getBalance());

        var response = bankAccounts != null && bankAccounts.size() > 0 ?
                new AccountLookupResponse("Successfully Returned " + bankAccounts.size() + " Bank"
                        + " Account(s)!", bankAccounts) : new AccountLookupResponse("No Bank " +
                "Accounts were found!");

        return response;
    }
}
