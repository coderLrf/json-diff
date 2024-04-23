package com.lrf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 比较字段注解，动态控制字段状态
 *
 * @author lrf
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CompareField {
    
    /**
     * 序号
     */
    int ordinal() default Integer.MAX_VALUE;

    /**
     * 字段名称
     */
    @Deprecated
    String name() default "";

    /**
     * 是否忽略此字段的比较，默认被忽略的字段将不返回，若需要返回可以使用 isMarker() 属性
     */
    boolean isIgnore() default false;

    /**
     * 是否属于标志性字段，如同一个表单内有字段变更，将此字段固定式返回
     */
    boolean isMarker() default false;

    /**
     * 该属性为 isIgnore() 和 isMarker() 的组合属性
     */
    boolean isIgnoreMarker() default false;
    
}
