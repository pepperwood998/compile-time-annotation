package com.tuan.exercise;

import com.tuan.exercise.ct_annot.annotation.StaticMethod;

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
    @StaticMethod
    public void someMethod() {

    }
}
