package cn.xavier.util;

import cn.xavier.system.domain.DictionaryDetail;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
public class IntrospectorTest {
    private static DictionaryDetail getInstance() {
        DictionaryDetail dictionaryDetail = new DictionaryDetail();
        dictionaryDetail.setId(1L);
        dictionaryDetail.setName("test");
        dictionaryDetail.setTypes_id(2L);
        return dictionaryDetail;
    }

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException,
            IllegalAccessException {
        DictionaryDetail instance = getInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(instance.getClass());
        PropertyDescriptor[] proDescriptors = beanInfo.getPropertyDescriptors();
        int i = 1;
        for (PropertyDescriptor prop : proDescriptors) {
            System.out.print(i++ + ": ");
            Method methodGetx = prop.getReadMethod();
            System.out.println(methodGetx.invoke(instance));
        }
    }
}
