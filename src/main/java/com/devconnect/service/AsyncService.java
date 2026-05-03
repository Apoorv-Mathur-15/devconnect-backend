package com.devconnect.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public void processPostAsync(String content, String email) {

        try {
            System.out.println("Async task started for email: " + email);

            Thread.sleep(3000);

            System.out.println("Processed post: " + content);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
