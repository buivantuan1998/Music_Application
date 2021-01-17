package com.bkav.sdl.core.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Length {
    double equal() default -1;

    double lessThan() default -1;

    double greatThan() default -1;

    double lessThanOrEqual() default -1;

    double greatThanOrEqual() default -1;
}
