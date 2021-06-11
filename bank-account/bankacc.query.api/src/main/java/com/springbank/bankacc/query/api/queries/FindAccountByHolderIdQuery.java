package com.springbank.bankacc.query.api.queries;

import lombok.*;

@Data
@AllArgsConstructor
public class FindAccountByHolderIdQuery {

    private String accountHolderId;
}
