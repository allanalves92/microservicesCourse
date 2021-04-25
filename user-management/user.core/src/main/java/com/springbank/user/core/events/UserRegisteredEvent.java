package com.springbank.user.core.events;

import com.springbank.user.core.models.*;
import lombok.*;

@Data
@Builder
public class UserRegisteredEvent {
    private String id;
    private User user;
}
