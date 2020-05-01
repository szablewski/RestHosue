package szablewski.bartosz.restHouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.model.Token;
import szablewski.bartosz.restHouse.repository.GuestRepository;
import szablewski.bartosz.restHouse.repository.TokenRepository;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.UUID;


@Service
public class GuestService {

    private GuestRepository guestRepository;
    private PasswordEncoder passwordEncoder;
    private TokenRepository tokenRepository;
    private MailService mailService;

    @Autowired
    public GuestService(GuestRepository guestRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository,
                        MailService mailService) {
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    public Optional<Guest> findByUserName(String name) {
        return guestRepository.findByUserName(name);
    }

    public void saveUser(Guest guest) {

        guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        guest.setRole("ROLE_GUEST");
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
            mailService.sendMail(guest.getEmail(), "Potwierdzenie rejestracji", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Guest findGuestByUserName(String userName) {
        return guestRepository.findGuestByUserName(userName);
    }
}