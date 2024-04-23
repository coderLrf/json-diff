package com.lrf.comparator;

import com.lrf.annotation.ComparePrimary;
import com.lrf.comparator.enums.ComparatorType;
import com.lrf.domain.CompareResult;
import com.lrf.exception.MultiplePrimaryException;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 比较器-抽象类
 *
 * @author lrf
 */
public abstract class AbstractComparator<T> {
    
    /**
     * 比较两个对象，返回差异结果
     * 
     * @param o1 对象1
     * @param o2 对象2
     * @param currentClass 要比较的class对象
     * @return 结果
     */
    public abstract CompareResult<Object> doCompare(T o1, T o2, Class<?> currentClass);
    
    /**
     * 根据传入类型来决定采用哪种比较器
     *
     * @param value1 第一个待比较的对象
     * @param value2 第二个待比较的对象
     * @param parentClazz 要比较的class对象
     * @return 返回比较结果
     */
    protected CompareResult<Object> doCompareItem(Object value1, Object value2, Class<?> parentClazz) {
        AbstractComparator<Object> comparator = ComparatorType.getComparatorType(value1);
        return comparator.doCompare(value1, value2, parentClazz);
    }

    /**
     * 比较器项-抽象类
     *
     * @author lrf
     */
    protected static abstract class AbstractCompareItem<T> {

        // 父级比较器，用来中转到其他类型比较器
        protected final AbstractComparator<?> parentComparator;

        // 比较的class类型
        protected final Class<?> clazz;

        // 结果存储器
        protected CompareResult<Object> result;

        // 字段列表Map式存储器
        protected final Map<String, Field> fieldMap = new LinkedHashMap<>();

        // 用于主键key字段处理
        protected String primaryKey;

        public AbstractCompareItem(AbstractComparator<?> parentComparator, Class<?> clazz) {
            this.parentComparator = parentComparator;
            this.clazz = clazz;
            this.result = new CompareResult<>();
            if (clazz != null) setFieldMap(clazz);
        }

        /**
         * 设置字段集合
         */
        private void setFieldMap(Class<?> clazz) {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                fieldMap.put(field.getName(), field);
                if (field.isAnnotationPresent(ComparePrimary.class)) {
                    if (!Objects.isNull(primaryKey)) { // 存在多个主键注解
                        throw new MultiplePrimaryException("There are multiple " + ComparePrimary.class.getSimpleName() + field);
                    }
                    primaryKey = field.getName();
                }
            }
        }

        /**
         * 处理每一项的比较逻辑
         *
         * @param o1 before对象
         * @param o2 after对象
         * @return 比较结果
         */
        protected abstract CompareResult<Object> doCompareItem(T o1, T o2);

        /**
         * 处理父类比较逻辑
         */
        protected CompareResult<Object> doParentCompareItem(Object value1, Object value2, Class<?> parentClazz) {
            return parentComparator.doCompareItem(value1, value2, parentClazz);
        }
    }
}
