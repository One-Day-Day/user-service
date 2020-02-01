package com.lovecode.system.logs.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DebugLogAnnotation {
    String name();
    boolean intoDb() default false;
}
