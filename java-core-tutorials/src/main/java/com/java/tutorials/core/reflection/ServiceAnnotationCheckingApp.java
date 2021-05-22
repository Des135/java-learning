package com.java.tutorials.core.reflection;

import com.java.tutorials.core.annotations.Init;
import com.java.tutorials.core.annotations.LazyService;
import com.java.tutorials.core.annotations.Service;
import com.java.tutorials.core.annotations.SimpleService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ServiceAnnotationCheckingApp {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Optional<Object> obj1 = instantiateIfHasServiceAnnotation(SimpleService.class);
        Optional<Object> obj2 = instantiateIfHasServiceAnnotation(LazyService.class);
        Optional<Object> obj3 = instantiateIfHasServiceAnnotation(String.class);

        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3);

        List<Optional<Object>> objects = Arrays.asList(obj1, obj2, obj3);
        Map<String, Object> serviceMap = new HashMap<>();

        List<Object> serviceObjects = objects.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

        for (Object o : serviceObjects) {
            Service annotation = o.getClass().getAnnotation(Service.class);
            serviceMap.put(annotation.name(), o);
        }

        System.out.println("Service object map:");
        System.out.println(serviceMap);

        LazyService lazyService = (LazyService) serviceMap.get("thisIsLazyService");
        System.out.println(lazyService);

        SimpleService simpleService = (SimpleService) serviceMap.get("thisIsSimpleService");
        System.out.println(simpleService);

        System.out.println("Invoking init methods on service objects:");
        invokeInitMethodOnServiceObjects(serviceMap);
    }

    public static Optional<Object> instantiateIfHasServiceAnnotation(Class<?> someClass) throws IllegalAccessException, InstantiationException {
        if (someClass.isAnnotationPresent(Service.class)) {
            return Optional.of(someClass.newInstance());
        } else {
            return Optional.empty();
        }
    }

    public static void invokeInitMethodOnServiceObjects(Map<String, Object> objects) throws InvocationTargetException, IllegalAccessException {
        for (Object obj : objects.values()) {
           invokeInitMethod(obj.getClass().getDeclaredMethods(), obj);
        }
    }

    public static void invokeInitMethod(Method[] methods, Object obj) throws InvocationTargetException, IllegalAccessException {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Init.class)){
                Init initAnnotation = method.getAnnotation(Init.class);
                if (initAnnotation.suppressException()) {
                    try {
                        method.invoke(obj);
                    } catch (Exception e) {
                        System.out.println("Ignoring exception");
                    }
                } else {
                    method.invoke(obj);
                }
            }
        }
    }
}
