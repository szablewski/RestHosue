package szablewski.bartosz.restHouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.model.Token;
import szablewski.bartosz.restHouse.repository.GuestRepository;
import szablewski.bartosz.restHouse.repository.TokenRepository;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final MailService mailService;

    public void saveUser(Guest guest) {

        guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        guest.setRole("ROLE_GUEST");
        guest.setCreated(LocalDateTime.now());
        guestRepository.save(guest);
        sendToken(guest);
    }

    private void sendToken(Guest guest) {

        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setGuest(guest);
        tokenRepository.save(token);

        String url = "http://localhost:8080/token?value=" + tokenValue;

        try {
            mailService.sendMail(guest.getEmail(), "Confirmation of registration", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Optional<Guest> findGuestByUserName(String userName) {
        return guestRepository.findByUserName(userName);
    }
}