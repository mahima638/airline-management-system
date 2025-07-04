package TY_PROJECT.Programs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "TY_PROJECT.Programs.controller",
    "TY_PROJECT.Programs.service",
    "TY_PROJECT.Programs.dto",
    "TY_PROJECT.Programs.config"  
})
@EntityScan(basePackages = "TY_PROJECT.ProgramsController.entity") 
public class ProgramsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProgramsApplication.class, args);
    }
}
