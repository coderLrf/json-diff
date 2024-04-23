package com.lrf.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONValidator;
import com.lrf.comparator.enums.ComparatorType;
import com.lrf.domain.CompareResult;

import java.util.Objects;

/**
 * 比较器工具类
 *
 * @author lrf
 */
public class ComparatorUtil {
    
    private Class<?> clazz;
    
    public ComparatorUtil() {
    }
    
    public ComparatorUtil(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * 比较两个对象
     * 
     * @param o1 第一个比较的对象
     * @param o2 第二个比较的对象
     * @return 差异结果
     */
    public CompareResult<Object> compare(Object o1, Object o2) {
        if (Objects.isNull(o1) || Objects.isNull(o2)) {
            throw new IllegalArgumentException("输入的JSON对象不能为空");
        }
        String json1 = o1 instanceof String ? (String) o1 : JSONObject.toJSONString(o1);
        String json2 = o2 instanceof String ? (String) o2 : JSONObject.toJSONString(o2);
        JSONValidator validator = JSONValidator.from(json1);
        if (validator.validate()) {
            // 采用jsonObject的比较器
            if (validator.getType() == JSONValidator.Type.Object) {
                return doCompare(ComparatorType.JSON_OBJECT_TYPE, JSONObject.parseObject(json1), JSONObject.parseObject(json2));
            } 
            // 采用jsonArray的比较器
            else if (validator.getType() == JSONValidator.Type.Array) {
                return doCompare(ComparatorType.JSON_ARRAY_TYPE, JSONObject.parseArray(json1), JSONObject.parseArray(json2));
            }
            // 采用默认原始比较器
            else {
                return doCompare(ComparatorType.DEFAULT_TYPE, json1, json2);
            }
        }
        throw new IllegalArgumentException("输入的JSON对象格式不正确");
    }

    /**
     * 比较两个对象
     * 
     * @param comparatorType 比较器类型
     * @param v1 第一个比较的对象
     * @param v2 第二个比较的对象
     * @return 结果
     */
    private CompareResult<Object> doCompare(ComparatorType comparatorType, Object v1, Object v2) {
        return comparatorType.getComparator().doCompare(v1, v2, clazz);
    }

}
