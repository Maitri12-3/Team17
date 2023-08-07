package com.db.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "trade")
public class Trade {

    @Id
    @GeneratedValue
    private Long id;
    private Long bookId;
    private Long counterpartyId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "security_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Security security;

    private Long quantity;
    private String status;
    private Long price;
    private Boolean buySell;
    private Date tradeDate;
    private Date settlementDate;

}