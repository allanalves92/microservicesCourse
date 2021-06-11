package com.springbank.bankacc.query.api.queries;

import lombok.*;

@Data
@AllArgsConstructor
public class FindAccountByHolderId {

    private String accountHolderId;
}
