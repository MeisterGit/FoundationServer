package foundation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan // Starts at this package, and goes down from there.
@EnableAutoConfiguration // Starts at this package, and goes down from there.
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
