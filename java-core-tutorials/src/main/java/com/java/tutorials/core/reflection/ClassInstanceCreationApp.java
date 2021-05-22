package com.java.tutorials.core.reflection;

import com.java.tutorials.core.annotations.SimpleService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassInstanceCreationApp {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> class1 = Class.forName("com.java.tutorials.core.annotations.SimpleService");
        Object obj = class1.newInstance();
        SimpleService objectCreatedByClassNewInstance = (SimpleService) obj;

        Constructor<?> constructor = class1.getConstructor();
        Object objCreatedByConstructor = constructor.newInstance();

        System.out.println(objectCreatedByClassNewInstance);
        System.out.println(objCreatedByConstructor);
    }
}
