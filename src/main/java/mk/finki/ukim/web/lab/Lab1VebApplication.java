package mk.finki.ukim.web.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Lab1VebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1VebApplication.class, args);
    }

}
