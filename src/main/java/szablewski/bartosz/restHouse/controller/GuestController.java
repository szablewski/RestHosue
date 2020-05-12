package szablewski.bartosz.restHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.repository.GuestRepository;
import szablewski.bartosz.restHouse.repository.TokenRepository;
import szablewski.bartosz.restHouse.service.GuestService;

@Controller
public class GuestController {

    private GuestService guestService;
    private TokenRepository tokenRepository;
    private GuestRepository guestRepository;

    @Autowired
    public GuestController(GuestService guestService, TokenRepository tokenRepository, GuestRepository guestRepository) {
        this.guestService = guestService;
        this.tokenRepository = tokenRepository;
        this.guestRepository = guestRepository;
    }

    @GetMapping("/sign_in")
    public String loginForm() {
        return "signIn";
    }

    @GetMapping("/sign_up")
    public String registerForm(Model model) {

        model.addAttribute("guest", new Guest());
        return "signUp";
    }

    /**
     * Registration form of new guest.
     *
     * @param guest  - new user that is being registering.
     * @param result
     * @param model
     * @return login form.
     */
    @PostMapping("/sign_up")
    public String register(Guest guest, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(guest);
            model.addAttribute("message", result.getAllErrors());
            return "redirect:/sign_up";
        } else {

            guestService.saveUser(guest);
            return "signUpSuccess";
        }
    }
}