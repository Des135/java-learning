package com.java.tutorials.core.annotations;

@Service(name = "thisIsLazyService", lazyLoad = true)
public class LazyService {

    @Init(suppressException = true)
    public void lazyInitialization() {
        System.out.println("Lazy initialization..");
        throw new IllegalStateException();
    }

    @Init(suppressException = true)
    private void somePrivateInitializationMethod() {
        System.out.println("Private initialization method execution");
    }

}
