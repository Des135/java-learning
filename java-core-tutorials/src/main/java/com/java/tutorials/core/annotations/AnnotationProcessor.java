package com.java.tutorials.core.annotations;

public class AnnotationProcessor {

    public static void main(String[] args) {
        inspectService(SimpleService.class);
        inspectService(LazyService.class);
        inspectService(String.class);
    }

    static void inspectService(Class<?> service) {
        System.out.println(String.format("Class %s inspection..", service));
        if (service.isAnnotationPresent(Service.class)) {
            Service annotation = service.getAnnotation(Service.class);
            System.out.println(String.format("Annotation 'Service' is present: name=%s, lazyLoad=%s", annotation.name(), annotation.lazyLoad()));
        } else {
            System.out.println("Annotation 'Service' not found.");
        }
    }
}
