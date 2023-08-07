package com.db.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TradeDTO {
    private Long securityId;
    private Long bookId;
    private Long counterpartyId;
    private Long quantity;
    private String status;
    private Long price;
    private Boolean buySell;
    private Date tradeDate;
    private Date settlementDate;

    // Getters and setters
}