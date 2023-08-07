package com.db.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name="security")
public class Security {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String isin;
    @NotEmpty
    private String cusip;
    @NotEmpty
    private String issuer;

    private Date maturityDate = new Date();

    private Double coupon;
    @NotEmpty
    private String type;

    private Double faceValue;
    @NotEmpty
    private String status;
//
//    @OneToMany(mappedBy = "security_id", cascade = CascadeType.ALL)
//    private Set<Trade> trades;
}
