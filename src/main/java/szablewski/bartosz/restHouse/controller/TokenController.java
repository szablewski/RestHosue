package szablewski.bartosz.restHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.model.Token;
import szablewski.bartosz.restHouse.repository.GuestRepository;
import szablewski.bartosz.restHouse.repository.TokenRepository;

@Controller
public class TokenController {

    private TokenRepository tokenRepository;
    private GuestRepository guestRepository;

    @Autowired
    public TokenController(TokenRepository tokenRepositoryl, GuestRepository guestRepository) {
        this.tokenRepository = tokenRepositoryl;
        this.guestRepository = guestRepository;
    }

    /**
     * we search in the user database with a given token, we check whether the token
     * from the link is the same token as the token assigned in the database, if so set "isEnabled" to true
     * @param value token value which we check.
     * @return
     */
    @GetMapping("/token")
    public String checkToken(@RequestParam String value){
        Token tokenValue = tokenRepository.findByValue(value);
        Guest guest = tokenValue.getGuest();
        guest.setEnabled(true);
        guestRepository.save(guest);
        return "/confirm";
    }
}