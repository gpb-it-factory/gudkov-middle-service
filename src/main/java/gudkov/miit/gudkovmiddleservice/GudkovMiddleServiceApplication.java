package gudkov.miit.gudkovmiddleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"gudkov.miit.gudkovmiddleservice", "org.openapi.example.*"})
@EnableFeignClients
public class GudkovMiddleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GudkovMiddleServiceApplication.class, args);
    }

}
