package com.lovecode.system.logs.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DebugLog {
    String name();
    boolean intoDb() default false;
}
