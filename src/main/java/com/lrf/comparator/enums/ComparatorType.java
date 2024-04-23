package com.lrf.comparator.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lrf.comparator.AbstractComparator;
import com.lrf.comparator.impl.JsonArrayComparator;
import com.lrf.comparator.impl.JsonObjectComparator;
import com.lrf.comparator.impl.PrimitiveComparator;

/**
 * 比较器类型
 *
 * @author lrf
 */
public enum ComparatorType {
    
    /** jsonObject比较器 */
    JSON_OBJECT_TYPE(JSONObject.class, new JsonObjectComparator()),
    
    /** jsonArray比较器 */
    JSON_ARRAY_TYPE(JSONArray.class, new JsonArrayComparator()),
    
    /** 默认比较器 */
    DEFAULT_TYPE(Object.class, new PrimitiveComparator())
    ;
    
    private Class<?> clazz;
    
    private AbstractComparator<?> comparator;

    ComparatorType(Class<?> clazz, AbstractComparator<?> comparator) {
        this.clazz = clazz;
        this.comparator = comparator;
    }
    
    public static <T> AbstractComparator<T> getComparatorType(Object value) {
        try {
            for (ComparatorType comparatorType : ComparatorType.values()) {
                if (comparatorType.getClazz().isAssignableFrom(value.getClass())) {
                    return comparatorType.getComparator();
                }
            }
        } catch (Exception ignore) {}
        return DEFAULT_TYPE.getComparator();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    public <T> AbstractComparator<T> getComparator() {
        return (AbstractComparator<T>) comparator;
    }

    public void setComparator(AbstractComparator<?> comparator) {
        this.comparator = comparator;
    }
}
