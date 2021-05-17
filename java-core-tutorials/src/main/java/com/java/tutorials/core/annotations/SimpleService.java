package com.java.tutorials.core.annotations;

@Service(name = "thisIsSimpleService")
public class SimpleService {

    @Init
    public void init(){
        System.out.println("Initialization..");
    }

    public void method2(){
        System.out.println("Method 2 execution..");
    }
}
