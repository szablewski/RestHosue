package szablewski.bartosz.restHouse.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.model.Token;
import szablewski.bartosz.restHouse.repository.GuestRepository;
import szablewski.bartosz.restHouse.repository.TokenRepository;
import szablewski.bartosz.restHouse.service.TokenService;

import java.util.Optional;

@Controller
public class TokenController {

    private TokenService tokenService;
    private GuestRepository guestRepository;

    @Autowired
    public TokenController(TokenService tokenService, GuestRepository guestRepository) {
        this.tokenService = tokenService;
        this.guestRepository = guestRepository;
    }

    /**
     * we search in the user database with a given token, we check whether the token
     * from the link is the same token as the token assigned in the database, if so set "isEnabled" to true
     * @param value token value which we check.
     * @return
     */
    @GetMapping("/token")
    public String checkToken(@RequestParam String value) throws NotFoundException {

        tokenService.checkToken(value);

        return "/confirm";
    }
}