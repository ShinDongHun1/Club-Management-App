package com.example.memberservice.global.aop.log;

import java.lang.annotation.*;

/**
 * Created by ShinD on 2022-03-15.
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Trace {
}
