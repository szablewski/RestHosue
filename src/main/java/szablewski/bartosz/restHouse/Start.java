package szablewski.bartosz.restHouse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.repository.GuestRepository;

import java.time.LocalDateTime;

@Configuration
public class Start {

    public Start(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {

        Guest guestFinanteq = new Guest();
        guestFinanteq.setUserName("Finanteq");
        guestFinanteq.setPassword(passwordEncoder.encode("Finanteq123"));
        guestFinanteq.setEnabled(true);
        guestFinanteq.setRole("ROLE_ADMIN");
        guestFinanteq.setCreated(LocalDateTime.now());

        Guest guestTadeusz = new Guest();
        guestTadeusz.setUserName("Tadeusz");
        guestTadeusz.setPassword(passwordEncoder.encode("Tadeusz123"));
        guestTadeusz.setEnabled(true);
        guestTadeusz.setRole("ROLE_GUEST");
        guestTadeusz.setCreated(LocalDateTime.now());

        guestRepository.save(guestFinanteq);
        guestRepository.save(guestTadeusz);
    }
}
