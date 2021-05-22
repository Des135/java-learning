package com.java.tutorials.core.reflection;

import com.java.tutorials.core.annotations.Init;
import com.java.tutorials.core.annotations.LazyService;
import com.java.tutorials.core.annotations.SimpleService;

import java.lang.reflect.Method;

public class InitAnnotationCheckingApp {

    public static void main(String[] args) {
        checkIfClassHasInitAnnotatedMethods(LazyService.class);
        checkIfClassHasInitAnnotatedMethods(SimpleService.class);
    }

    public static void checkIfClassHasInitAnnotatedMethods(Class<?> class1) {
        System.out.println("Checking Init annotation presence among methods declared directly in class (both private and public): " + class1);
        checkIfThereAreInitAnnotatedMethods(class1.getDeclaredMethods());
        System.out.println("...");

        System.out.println("Checking Init annotation presence among methods in class (only public + parent public): " + class1);
        checkIfThereAreInitAnnotatedMethods(class1.getMethods());
        System.out.println("...");
    }

    public static void checkIfThereAreInitAnnotatedMethods(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Init.class)) {
                System.out.println("this method has init annotation! method=" + method);
            } else {
                System.out.println("non found");
            }
        }
    }

}
