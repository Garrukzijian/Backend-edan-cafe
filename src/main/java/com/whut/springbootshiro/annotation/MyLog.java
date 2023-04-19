package com.whut.springbootshiro.annotation;



import java.lang.annotation.*;

/**
 * @author Lei
 * 这个主要就是可以进行设置这个controller的执行位置，并且就是设置这个controller的提示的接口意义
 * @create 2021-08-19 23:18
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {
    String value() default "";
}
