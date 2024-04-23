package com.lrf.domain;

/**
 * 比较返回结果
 * 
 * @author lrf
 */
public class CompareResult<T> {

    private T o1;
    
    private T o2;

    public CompareResult() {
    }

    public CompareResult(T o1, T o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
    
    public static <T> CompareResult<T> of(T o1, T o2) {
        return new CompareResult<>(o1, o2);
    }
    
    public static <T> CompareResult<T> ofIsAbsent(boolean isAbsent1, T o1, boolean isAbsent2, T o2) {
        return of(isAbsent1 ? null : o1, isAbsent2 ? null : o2);
    }
    
    public static CompareResult<Object> ofNull() { return of(null, null); }
    
    public T getO1() {
        return o1;
    }

    public void setO1(T o1) {
        this.o1 = o1;
    }

    public T getO2() {
        return o2;
    }

    public void setO2(T o2) {
        this.o2 = o2;
    }
    
    public boolean isAbsent() {
        return o1 == null && o2 == null;
    }
}
