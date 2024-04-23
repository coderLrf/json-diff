package com.lrf.comparator.impl;

import com.alibaba.fastjson.JSONObject;
import com.lrf.annotation.CompareField;
import com.lrf.domain.CompareResult;
import com.lrf.comparator.AbstractComparator;
import com.lrf.utils.CommonUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * json对象比较器
 *
 * @author lrf
 */
public class JsonObjectComparator extends AbstractComparator<JSONObject> {

    @Override
    public CompareResult<Object> doCompare(JSONObject o1, JSONObject o2, Class<?> currentClass) {
        return new JsonObjectItemCompare(this, currentClass).doCompareItem(o1, o2);
    }

    /**
     * json项对象比较器
     *
     * @author lrf
     */
    private static class JsonObjectItemCompare extends AbstractCompareItem<JSONObject> {

        private final Map<String, Object> result1 = new HashMap<>();
        private final Map<String, Object> result2 = new HashMap<>();

        public JsonObjectItemCompare(AbstractComparator<?> parentComparator, Class<?> clazz) {
            super(parentComparator, clazz);
        }

        @Override
        protected CompareResult<Object> doCompareItem(JSONObject o1, JSONObject o2) {
            // 存储标识性fieldKeys，key = 比较字段，value = 期望字段
            Map<String, String> markerKeys = new HashMap<>();
            // 遍历处理
            CommonUtil.collectKeys(o1, o2).forEach(key -> {
                Object value1 = o1.get(key);
                Object value2 = o2.get(key);
                Class<?> clazz = this.clazz;
                if (!Objects.isNull(clazz)) {
                    if (fieldMap.containsKey(key)) {
                        Field field = fieldMap.get(key);
                        clazz = CommonUtil.getClassGenricType(field, 0);
                        if (field.isAnnotationPresent(CompareField.class)) {
                            CompareField compareField = field.getDeclaredAnnotation(CompareField.class);
                            key = compareField.name().isEmpty() ? key : compareField.name();
                            if (compareField.isIgnoreMarker()) {
                                markerKeys.putIfAbsent(field.getName(), key);
                                return;
                            }
                            if (compareField.isMarker()) markerKeys.putIfAbsent(field.getName(), key);
                            if (compareField.isIgnore()) return;
                        }
                    } else { 
                        // 表示表单内没有，字段名有改动，标志性返回
                        // markerKeys.putIfAbsent(key, key);
                    }
                }
                doCompareField(value1, value2, key, clazz);
            });
            if (!markerKeys.isEmpty() && (!result1.isEmpty() || !result2.isEmpty())) {
                markerKeys.forEach((fieldName, key) -> {
                    result1.put(key, o1.get(fieldName));
                    result2.put(key, o2.get(fieldName));
                });
            }
            return getResult();
        }

        private void doCompareField(Object value1, Object value2, String key, Class<?> currentClass) {
            CompareResult<?> result = doParentCompareItem(value1, value2, currentClass);
            if (!result.isAbsent()) {
                result1.put(key, result.getO1());
                result2.put(key, result.getO2());
            }
        }

        /**
         * 对结果重新排序
         */
        private void doReorder() {
            List<String> keys = new ArrayList<>(CommonUtil.collectKeys(result1, result2)).stream()
                    .sorted((k1, k2) -> getOrdinal(k1) - getOrdinal(k2))
                    .collect(Collectors.toList());
            Map<String, Object> sortedResult1 = new LinkedHashMap<>();
            Map<String, Object> sortedResult2 = new LinkedHashMap<>();
            keys.forEach(key -> {
                sortedResult1.put(key, result1.get(key));
                sortedResult2.put(key, result2.get(key));
            });
            result.setO1(new JSONObject(sortedResult1));
            result.setO2(new JSONObject(sortedResult2));
        }

        /**
         * 返回结果
         */
        private CompareResult<Object> getResult() {
            if (result1.isEmpty() && result2.isEmpty()) {
                return CompareResult.ofNull();
            } else if (!Objects.isNull(this.clazz)) {
                // 对结果重新排序
                doReorder();
            } else {
                result.setO1(new JSONObject(result1));
                result.setO2(new JSONObject(result2));
            }
            return result;
        }

        /**
         * 获取序号值，默认为Short.MAX
         */
        private Integer getOrdinal(String key) {
            Field field = fieldMap.get(key);
            if (field != null && field.isAnnotationPresent(CompareField.class)) {
                return field.getDeclaredAnnotation(CompareField.class).ordinal();
            }
            return Integer.MAX_VALUE;
        }
    }
}
