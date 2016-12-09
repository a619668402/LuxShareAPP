package com.luxshare.textokhttp;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Luxshare-ICT on 2016/12/5.
 */

public class ModelReflector {

    public static Object setProperty(Object bean, String propertyName, Object value) {
        Class clazz = bean.getClass();
        try {
            Field field = clazz.getField(propertyName);
            Method method = clazz.getDeclaredMethod(getSetterName(field.getName()),new Class[]{field.getType()});
            return method.invoke(bean,new Object[]{value});
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getGetterName(String propertyName) {
        String method = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        return method;
    }

    public static String getSetterName(String propertyName) {
        String method = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        return method;
    }
}
