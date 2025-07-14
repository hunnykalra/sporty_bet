package com.sporty.bet.repository;

import com.sporty.bet.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BetRepository extends JpaRepository<Bet, String> {
    List<Bet> findByEventId(String eventId);
} 