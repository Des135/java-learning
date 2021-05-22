package com.java.tutorials.core.reflection;

public class ClassRetrieverApp {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassRetrieverApp cra = new ClassRetrieverApp();
        Class<?> class1 = cra.getClass();

        Class<?> class2 = ClassRetrieverApp.class;

        Class<?> class3 = Class.forName(ClassRetrieverApp.class.getName());

        System.out.println(class1);
        System.out.println(class2);
        System.out.println(class3);
    }
}
