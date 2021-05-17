package com.java.tutorials.core.annotations;

@Service(name = "thisIsLazyService", lazyLoad = true)
public class LazyService {

    @Init
    public void lazyInitialization(){
        System.out.println("Lazy initialization..");
    }

}
