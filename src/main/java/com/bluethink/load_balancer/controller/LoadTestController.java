package com.bluethink.load_balancer.controller;


import org.apache.catalina.Executor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/test")
public class LoadTestController {

    @GetMapping("/load")
    public String testLoadBalancer(){
        int totalRequests = 100;
        int totalThreads = 100;

        String url = "http://localhost/api/user";
        HttpClient client = HttpClient.newHttpClient();
        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);

            for(int i = 1;i<=totalRequests;i++){
                final int requestNumber = i;
                executor.submit(()->{
                    try{
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .GET()
                                .build();
                        HttpResponse<String> response =
                                client.send(
                                        request,
                                        HttpResponse.BodyHandlers.ofString()
                                );
                        System.out.println("Request: " + requestNumber +
                                "| Status " + response.statusCode() +
                                "| Response " + response.body());
                    }catch (Exception e){
                        System.out.println("Request failed: " + requestNumber + "|" + e.getMessage());
                    }
                });

            }
            executor.close();
            return "100 request submit to Nginx";
    }
}
