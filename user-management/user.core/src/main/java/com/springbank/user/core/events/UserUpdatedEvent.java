package com.springbank.user.core.events;

import com.springbank.user.core.models.*;
import lombok.*;

@Data
@Builder
public class UserUpdatedEvent {
    private String id;
    private User user;
}
