package com.java.tutorials.core.annotations;

public class AnnotationProcessor {

    public static void main(String[] args) {
        inspectService(SimpleService.class);
        inspectService(LazyService.class);
        inspectService(String.class);
    }

    static void inspectService(Class<?> service) {
        System.out.printf("Class %s inspection..%n", service);
        if (service.isAnnotationPresent(Service.class)) {
            Service annotation = service.getAnnotation(Service.class);
            System.out.printf("Annotation 'Service' is present: name=%s, lazyLoad=%s%n", annotation.name(), annotation.lazyLoad());
        } else {
            System.out.println("Annotation 'Service' not found.");
        }
    }
}
