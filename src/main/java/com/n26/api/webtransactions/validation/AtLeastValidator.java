package com.n26.api.webtransactions.validation;

import com.n26.api.webtransactions.exception.InvalidTimestampException;
import com.n26.api.webtransactions.util.DateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AtLeastValidator implements ConstraintValidator<AtLeast, Long> {

    private AtLeast atLeastAnnotation;

    @Override
    public void initialize(AtLeast constraintAnnotation) {
        this.atLeastAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (DateUtil.olderThenLimit(value, atLeastAnnotation.unit(), atLeastAnnotation.duration())) {
            throw new InvalidTimestampException(atLeastAnnotation.message());
        }

        return true;
    }
}
