package szablewski.bartosz.restHouse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.repository.GuestRepository;

@Configuration
public class Start {

    public Start(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        Guest guestFinanteq = new Guest();
        guestFinanteq.setUserName("Finanteq");
        guestFinanteq.setPassword(passwordEncoder.encode("Finanteq123"));
        guestFinanteq.setRole("ROLE_ADMIN");

        Guest guestTadeusz = new Guest();
        guestTadeusz.setUserName("Tadeusz");
        guestTadeusz.setPassword(passwordEncoder.encode("Tadeusz123"));
        guestTadeusz.setRole("ROLE_GUEST");

        guestRepository.save(guestFinanteq);
        guestRepository.save(guestTadeusz);
    }
}
