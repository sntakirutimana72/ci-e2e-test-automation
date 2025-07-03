package com.allure.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Preconditions {
  String[] value() default {};
  String[] steps() default {};
}
