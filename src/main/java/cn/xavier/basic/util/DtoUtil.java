package cn.xavier.basic.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 数据传输对象工具类
 *
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
public class DtoUtil {

    /**
     * Any empty field exist
     * DTO里面的都是必要参数，有任何字段为空都返回true
     * @param dtoObj
     * @return
     */
    @SneakyThrows
    public static boolean anyEmptyFieldExist(Object dtoObj) {
        BeanInfo beanInfo = Introspector.getBeanInfo(dtoObj.getClass());
        PropertyDescriptor[] proDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor prop : proDescriptors) {
            Method readMethod = prop.getReadMethod();
            // 有任何null或空字符串返回true, StringUtils.isEmpty可以判断任意对象null
            if (StringUtils.isEmpty(readMethod.invoke(dtoObj))) {
                return true;
            }
        }
        return false;
    }

    /**
     * To json string 对象转JSON字符串
     *
     * @return the string
     */
    @SneakyThrows
    public static String toJsonString(Object obj) {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
