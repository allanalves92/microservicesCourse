package com.springbank.user.query.api.dto;

import com.springbank.user.core.models.*;
import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
public class UserLookupResponse {
    private List<User> users;

    public UserLookupResponse(User user) {
        this.users = new ArrayList<>();
        this.users.add(user);
    }
}
