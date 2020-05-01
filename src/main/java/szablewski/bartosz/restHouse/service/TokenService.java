package szablewski.bartosz.restHouse.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.model.Token;
import szablewski.bartosz.restHouse.repository.GuestRepository;
import szablewski.bartosz.restHouse.repository.TokenRepository;

import java.util.Optional;

@Service
public class TokenService {

    private TokenRepository tokenRepository;
    private GuestRepository guestRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository, GuestRepository guestRepository) {
        this.tokenRepository = tokenRepository;
        this.guestRepository = guestRepository;
    }

    public void checkToken(String value) throws NotFoundException{

        Optional<Token> tokenValue;
        Optional.ofNullable(tokenValue = tokenRepository.findByValue(value));
        tokenValue.orElseThrow(() -> new NotFoundException("Not Found token " + value));
        Guest guest = tokenValue.get().getGuest();
        guest.setEnabled(true);
        guestRepository.save(guest);
    }
}