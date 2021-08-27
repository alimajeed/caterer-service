package com.hunzaconsulting.catererservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CapacityValidator.class)
@Documented
public @interface CapacityRange {
    String message() default "max is smaller than min";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
