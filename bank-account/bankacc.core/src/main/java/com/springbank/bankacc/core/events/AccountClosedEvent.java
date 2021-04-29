package com.springbank.bankacc.core.events;

import lombok.*;

@Data
@Builder
public class AccountClosedEvent {
    private String id;
}
