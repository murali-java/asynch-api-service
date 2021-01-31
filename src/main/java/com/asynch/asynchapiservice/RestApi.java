package com.asynch.asynchapiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class RestApi {

    @Autowired
    PayService payService;

    @GetMapping("/hit")
    public String hit() {
       return "response from AWS";
    }

    /**
     * Saving and calling rest call will run in parallel. And Response will be return only after two tasks/threads completes.
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/invoke")
    public String invoke() throws ExecutionException, InterruptedException {
        CompletableFuture<String> aynck = payService.asyncCall();
        CompletableFuture.allOf(payService.save(), aynck).join();
        return aynck.get();
    }

    /**
     * After save(), asyncCall() is called and response will return immediately and will not wait untill asyncCall() completes.
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/invoke2")
    public String invoke2() throws ExecutionException, InterruptedException {
        payService.save();
        payService.asyncCall();
        return UUID.randomUUID().toString();
    }

    @PostMapping(value = "test", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String test(@Validated @RequestBody SSN body){
        return "test";
    }
}
