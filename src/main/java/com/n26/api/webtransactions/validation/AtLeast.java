package com.n26.api.webtransactions.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = AtLeastValidator.class)
public @interface AtLeast {

    int duration();

    ChronoUnit unit();

    String message() default "{javax.validation.constraints.AtLeast.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
