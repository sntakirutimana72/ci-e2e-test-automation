package com.allure.annotations;

import io.qameta.allure.LabelAnnotation;

import java.lang.annotation.*;

@LabelAnnotation(name = "Preconditions")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Preconditions {
  String[] value() default {};
  String[] steps() default {};
}
