package com.lrf.comparator.impl;

import com.lrf.domain.CompareResult;
import com.lrf.comparator.AbstractComparator;

import java.util.Objects;

/**
 * 原始类型比较器
 *
 * @author lrf
 */
public class PrimitiveComparator extends AbstractComparator<Object> {

    @Override
    public CompareResult<Object> doCompare(Object o1, Object o2, Class<?> currentClass) {
        if (Objects.equals(o1, o2)) {
            return CompareResult.ofNull();
        }
        return new CompareResult<>(o1, o2);
    }
    
}
