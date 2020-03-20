package szablewski.bartosz.restHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import szablewski.bartosz.restHouse.repository.GuestRepository;

@SpringBootApplication()
@EnableJpaRepositories(basePackageClasses = GuestRepository.class)
public class HolidayHomeApplication {


    public static void main(String[] args) {
        SpringApplication.run(HolidayHomeApplication.class, args);
    }
}