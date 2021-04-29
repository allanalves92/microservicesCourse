package com.springbank.bankacc.query.api.repositories;

import com.springbank.bankacc.core.models.*;
import org.springframework.data.repository.*;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
}
