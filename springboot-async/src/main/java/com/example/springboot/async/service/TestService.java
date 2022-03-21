package com.example.springboot.async.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TestService {


    public void serviceTest() {
        //简单来个stream遍历
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}).forEach(t -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test:" + t);
        });
    }
}
