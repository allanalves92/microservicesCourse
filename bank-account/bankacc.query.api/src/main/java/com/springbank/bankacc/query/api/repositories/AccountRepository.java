package com.springbank.bankacc.query.api.repositories;

import com.springbank.bankacc.core.models.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface AccountRepository extends CrudRepository<BankAccount, String> {

    Optional<BankAccount> findByAccountHolderId(String accountHolderId);

    List<BankAccount> findByBalanceGreaterThan(double balance);

    List<BankAccount> findByBalanceLessThan(double balance);
}
