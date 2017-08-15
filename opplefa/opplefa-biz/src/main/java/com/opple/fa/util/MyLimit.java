package com.opple.fa.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })  
// 标注只能放在类或接口的注解  
@Retention(RetentionPolicy.RUNTIME)  
// 在运行时有效  
public @interface MyLimit {  
    //这个字段是否为必填  
    boolean isNotNull() default false;  
    // 字段描述  
    String description() default "";  
    int MaxLength() default 0;  
    int MinLength() default 0;  
    //表示这个字段是否为int类型  
    boolean isInt() default false;  
    int MaxNum() default 50;  
    int MinNum() default 10;  
}  