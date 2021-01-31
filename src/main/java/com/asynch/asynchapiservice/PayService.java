package com.asynch.asynchapiservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class PayService {

    static Logger logger = LoggerFactory.getLogger(PayService.class);
    //@Autowired
    public RestTemplate restTemplate;

    public CompletableFuture<Void> save(){
        for(int i =0; i < 6; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info(i+". saving into db...");
        }
        logger.info("saved successfully");
        return CompletableFuture.completedFuture(null);
    }

    @Async("taskExecutor")
    public CompletableFuture<String> asyncCall()  {
            //
        String response = "api response";
        try {
            for(int i =0; i< 3; i++) {
                logger.info("in asynch call");
                Thread.sleep(1000);
            }

            for(int i =0; i < 3; i++) {
                Thread.sleep(1000);
                logger.info(i+". updating into db...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("updated successfully");
        return CompletableFuture.completedFuture(response);
    }
}
