package uz.pdp.apiattachment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ApiAttachmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiAttachmentApplication.class, args);
    }

}
