package com.db.api.repository;

import com.db.api.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findBySecurityId(Long postId);
}
