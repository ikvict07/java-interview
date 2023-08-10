package com.example.javabackendinterview.imitaion;

import java.util.concurrent.ThreadLocalRandom;

public class MyTimer {
    public static void Imitate() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(4, 11) * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
