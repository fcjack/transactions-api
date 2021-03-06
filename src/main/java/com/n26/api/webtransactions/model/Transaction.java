package com.n26.api.webtransactions.model;

import com.n26.api.webtransactions.validation.AtLeast;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable {

    @NotNull
    private Double amount;

    @NotNull
    @AtLeast(duration = 60, unit = ChronoUnit.SECONDS)
    private Long timestamp;
}
