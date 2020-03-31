package szablewski.bartosz.restHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.model.Token;
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

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {

        model.addAttribute("guest", new Guest());
        return "register";
    }

    /**
     * Registration form of new guest.
     *
     * @param guest  - new user that is being registering.
     * @param result
     * @param model
     * @return login form.
     */
    @PostMapping("/register")
    public String register(Guest guest, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute(guest);
            model.addAttribute("message", result.getAllErrors());
            return "redirect:/register";
        } else {

            guestService.saveUser(guest);
            return "signonSuccess";
        }
    }
}