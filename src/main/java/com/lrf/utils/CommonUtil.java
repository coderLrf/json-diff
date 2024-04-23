package com.lrf.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 工具类
 *
 * @author lrf
 */
public class CommonUtil {
    
    /**
     * 收集两个对象所有keys
     */
    public static Set<String> collectKeys(Map<String, Object> o1, Map<String, Object> o2) {
        Set<String> keys = new HashSet<>();
        keys.addAll(o1.keySet());
        keys.addAll(o2.keySet());
        return keys;
    }

    /**
     * 判断为java原始类型
     */
    public static Boolean isPrimitiveType(Object o1) {
        return o1 instanceof String || o1 instanceof Number || o1 instanceof Character || o1 instanceof Boolean || o1 instanceof Date;
    }

    /**
     * 通过反射, 获得字段泛型对象
     * 如无法找到, 返回type原始类型
     */
    public static Class<?> getClassGenricType(final Field field, final int index)
    {
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType))
        {
            return field.getType();
        }

        Type[] params = ((ParameterizedType) genericType).getActualTypeArguments();

        if (index >= params.length || index < 0)
        {
            return field.getType();
        }
        if (!(params[index] instanceof Class))
        {
            return field.getType();
        }

        return (Class<?>) params[index];
    }
    
}
