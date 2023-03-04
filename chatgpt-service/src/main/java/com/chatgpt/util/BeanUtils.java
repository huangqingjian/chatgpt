package com.chatgpt.util;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * bean工具
 */
public class BeanUtils {
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 基于Dozer转换对象的类型

     * @param source 资源数据
     * @param destinationClass 目标对象类型
     * @return
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型

     * @param sourceList 资源数据列表
     * @param destinationClass 目标对象类型
     * @return
     */
    public static <T, V> List<V> mapList(Collection<T> sourceList, Class<V> destinationClass) {
        List<V> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            V destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 复制对象属性
     *
     * @param source
     * @param target
     * @param ignoreNull
     * @param <T>
     * @param <V>
     */
    public static <T, V> void copy(T source, V target, boolean ignoreNull) {
        if(ignoreNull) {
            org.springframework.beans.BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
        } else {
            org.springframework.beans.BeanUtils.copyProperties(source, target, new String[]{});
        }

    }

    /**
     * 获取对象空属性
     *
     * @param source
     * @return
     */
    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target){

    }

}
