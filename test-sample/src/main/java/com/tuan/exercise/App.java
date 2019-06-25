package com.tuan.exercise;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    // this will produce a compile-time error
    // cause this annotation can only be applied to static method
    // uncomment below line and import the class to see the result
//    @StaticMethod
    public void someMethod() {

    }
}
