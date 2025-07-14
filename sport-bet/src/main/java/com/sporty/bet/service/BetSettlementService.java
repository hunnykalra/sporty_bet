package com.sporty.bet.service;

import com.sporty.bet.entity.Bet;
import com.sporty.bet.request.SportsEventOutcome;
import com.sporty.bet.exception.SportyBetException;
import java.util.List;

public interface BetSettlementService {
    void settlementBet(SportsEventOutcome sportsEventOutcome) throws SportyBetException;
} 