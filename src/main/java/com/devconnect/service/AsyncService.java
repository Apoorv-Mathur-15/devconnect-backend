package com.devconnect.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Async("taskExecutor")
    public CompletableFuture<String> processContent(String content) {

        System.out.println("Thread: " +  Thread.currentThread().getName());

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Processed content");

        return CompletableFuture.completedFuture("Content Done");
    }

    @Async("taskExecutor")
    public CompletableFuture<String> sendNotification(String email) {
        System.out.println("Thread: " +  Thread.currentThread().getName());
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Notification sent to: " + email);
        return CompletableFuture.completedFuture("Notification Done");
    }

    @Async("taskExecutor")
    public CompletableFuture<String> saveAnalytics(String email) {
        System.out.println("Thread: " +  Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Analytics saved for " + email);

        return CompletableFuture.completedFuture("Analytics Done");
    }
}
