package com.n26.api.webtransactions.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class Transaction implements Serializable {

    private Double amount;
    private Long timestamp;
}
