package com.sporty.bet.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SportsEventOutcome {
    private String eventId;
    private String eventName;
    private String eventWinnerId;
} 