package eu.brickpics.maurice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class MauriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MauriceApplication.class, args);
    }

}
