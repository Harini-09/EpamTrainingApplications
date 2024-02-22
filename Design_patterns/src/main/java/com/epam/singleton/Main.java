package com.epam.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void createInstance(){
        CandyMaker cart = CandyMaker.createCandy();
        System.out.println(cart);
    }
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(Main::createInstance);
        service.submit(Main::createInstance);
        service.shutdown();
    }
}