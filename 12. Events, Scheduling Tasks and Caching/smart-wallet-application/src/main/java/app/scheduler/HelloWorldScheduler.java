package app.scheduler;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class HelloWorldScheduler {

    // Scheduled Job = Cron Job
//    @Scheduled(fixedDelay = 10000) // 10 seconds
    public void sayHelloEvery10Seconds() {

        System.out.println(LocalDateTime.now() + " Hello World!!!!");
    }
}
