package com.lrf.comparator.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lrf.comparator.AbstractComparator;
import com.lrf.domain.CompareResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * json数组比较器
 *
 * @author lrf
 */
public class JsonArrayComparator extends AbstractComparator<JSONArray> {
    
    @Override
    public CompareResult<Object> doCompare(JSONArray o1, JSONArray o2, Class<?> currentClass) {
        return new JsonArrayItemCompare(this, currentClass).doCompareItem(o1, o2);
    }

    /**
     * json数组项比较器
     *
     * @author lrf
     */
    private static class JsonArrayItemCompare extends AbstractCompareItem<JSONArray> {
        
        private final List<Object> result1 = new ArrayList<>();
        private final List<Object> result2 = new ArrayList<>();

        public JsonArrayItemCompare(AbstractComparator<?> parentComparator, Class<?> clazz) {
            super(parentComparator, clazz);
            this.result = CompareResult.of(new JSONArray(result1), new JSONArray(result2));
        }

        @Override
        protected CompareResult<Object> doCompareItem(JSONArray o1, JSONArray o2) {
            return Objects.isNull(primaryKey) ? doWithoutPrimaryKeyCompare(o1, o2) : doWithPrimaryKeyCompare(o1, o2, primaryKey);
        }

        /**
         * 处理没有主键注解的逻辑
         *
         * @param o1 列表1
         * @param o2 列表2
         */
        private CompareResult<Object> doWithoutPrimaryKeyCompare(JSONArray o1, JSONArray o2) {
            int minSize = Math.min(o1.size(), o2.size());
            int maxSize = Math.max(o1.size(), o2.size());

            for (int i = 0; i < minSize; i++) {
                // 处理集合项
                CompareResult<Object> itemResult = doParentCompareItem(o1.get(i), o2.get(i), this.clazz);
                if (itemResult.getO1() != null) result1.add(itemResult.getO1());
                if (itemResult.getO2() != null) result2.add(itemResult.getO2());
            }

            // 处理剩余部分
            if (minSize != maxSize) {
                if (o1.size() == minSize) result1.addAll(o2.subList(minSize, maxSize));
                if (o2.size() == minSize) result2.addAll(o1.subList(minSize, maxSize));
            }
            return getResult();
        }

        /**
         * 处理带有主键注解的逻辑
         *
         * @param o1 列表1
         * @param o2 列表2
         * @param primaryKey 主键key
         */
        private CompareResult<Object> doWithPrimaryKeyCompare(JSONArray o1, JSONArray o2, String primaryKey) {
            Map<Object, Object> map1 = o1.stream().collect(Collectors.toMap(o -> ((JSONObject) o).get(primaryKey), o -> o));
            Map<Object, Object> map2 = o2.stream().collect(Collectors.toMap(o -> ((JSONObject) o).get(primaryKey), o -> o));
            map1.forEach((k, v) -> {
                if (map2.containsKey(k)) {
                    // 处理集合项
                    CompareResult<Object> itemResult = doParentCompareItem(v, map2.get(k), this.clazz);
                    if (!itemResult.isAbsent()) {
                        result1.add(itemResult.getO1());
                        result2.add(itemResult.getO2());
                    }
                    // 移除map2
                    map2.remove(k);
                } else {
                    result1.add(v);
                }
            });
            // 处理剩余的map2
            map2.forEach((k, v) -> result2.add(v));
            return getResult();
        }

        // 返回结果
        private CompareResult<Object> getResult() {
            if (result1.isEmpty() && result2.isEmpty()) {
                return CompareResult.ofNull();
            }
            return result;
        }
    }
}
