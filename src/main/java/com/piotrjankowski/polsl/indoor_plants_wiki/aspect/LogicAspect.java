package com.piotrjankowski.polsl.indoor_plants_wiki.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogicAspect {
    Object aroundCreateNewCategory(ProceedingJoinPoint jp){
        //jp.
        return null;
    }
}
