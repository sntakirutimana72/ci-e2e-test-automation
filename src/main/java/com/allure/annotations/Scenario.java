package com.allure.annotations;

import io.qameta.allure.LabelAnnotation;

import java.lang.annotation.*;

@LabelAnnotation(name = "Scenario")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Scenario {
  String value() default "";
}
