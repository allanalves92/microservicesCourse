package com.springbank.bankacc.query.api.queries;

import com.springbank.bankacc.query.api.dto.*;
import lombok.*;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery {

    private EqualityType equalityType;
    private double balance;
}
