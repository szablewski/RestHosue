package szablewski.bartosz.restHouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.service.GuestService;

@Controller
public class GuestController {

    private GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
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
    public String register(Guest guest, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(guest);
            model.addAttribute("message", result.getAllErrors());
            return "/register";
        } else {
            guestService.saveUser(guest);
            return "redirect:/login";
        }
    }
}
