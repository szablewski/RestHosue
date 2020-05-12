package szablewski.bartosz.restHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import szablewski.bartosz.restHouse.repository.GuestRepository;

@SpringBootApplication()
@EnableJpaRepositories(basePackageClasses = GuestRepository.class)
public class HolidayHomeApplication {


    public static void main(String[] args) {
        SpringApplication.run(HolidayHomeApplication.class, args);
    }
}