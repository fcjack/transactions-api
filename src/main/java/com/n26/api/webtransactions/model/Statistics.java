package com.n26.api.webtransactions.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class Statistics implements Serializable {

    private Double sum;
    private Double avg;
    private Double max;
    private Double min;
    private Long count;
}
