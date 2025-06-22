package com.brucecompiler.aspect;

import com.brucecompiler.anno.AutoFill;
import com.brucecompiler.constant.AutoFillConstant;
import com.brucecompiler.context.BaseContext;
import com.brucecompiler.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;


/**
 * 公共字段自动填充切面类
 */
@Component
@Aspect
public class AutoFillAspect {

    // 前置通知
    @Before("execution(* com.brucecompiler.mapper.*.*(..)) && @annotation(com.brucecompiler.anno.AutoFill)")
    public void autoFill(JoinPoint joinPoint) {
        System.out.println("----->");
        // 1. 获取目标方法上的注解, 并拿到注解里的属性值
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // 方法对象
        Method method = methodSignature.getMethod();
        AutoFill autoFill = method.getAnnotation(AutoFill.class);

        OperationType operationType = autoFill.value();

        // 2. 获取到目标方法中的参数对象
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];        // 拿到就是实体对象

        // 3. 判断注解中的属性值, 如果是INSERT, 就补充四个字段
        try{
           if(operationType == OperationType.INSERT) {
               // 通过反射去补充属性值
               Method setCreateTime = entity.getClass().getDeclaredMethod(
                       AutoFillConstant.SET_CREATE_TIME,
                       LocalDateTime.class
               );

               Method setUpdateTime = entity.getClass().getDeclaredMethod(
                       AutoFillConstant.SET_UPDATE_TIME,
                       LocalDateTime.class
               );

               Method setCreateUser = entity.getClass().getDeclaredMethod(
                       AutoFillConstant.SET_CREATE_USER,
                       Long.class
               );

               Method setUpdateUser = entity.getClass().getDeclaredMethod(
                       AutoFillConstant.SET_UPDATE_USER,
                       Long.class
               );

               setCreateTime.invoke(entity, LocalDateTime.now());
               setUpdateTime.invoke(entity, LocalDateTime.now());
               setCreateUser.invoke(entity, BaseContext.getCurrentId());
               setUpdateUser.invoke(entity, BaseContext.getCurrentId());
           }else if(operationType == OperationType.UPDATE) {
               // 4. 如果是UPDATE, 就补充两个字段
               Method setUpdateTime = entity.getClass().getDeclaredMethod(
                       AutoFillConstant.SET_UPDATE_TIME,
                       LocalDateTime.class
               );
               Method setUpdateUser = entity.getClass().getDeclaredMethod(
                       AutoFillConstant.SET_UPDATE_USER,
                       Long.class
               );
               setUpdateTime.invoke(entity, LocalDateTime.now());
               setUpdateUser.invoke(entity, BaseContext.getCurrentId());
           }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
