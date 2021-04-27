package com.springbank.user.query.api.queries;

import lombok.*;

@Data
@AllArgsConstructor
public class FindUserByIdQuery {
    private String id;
}
