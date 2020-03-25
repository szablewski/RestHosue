package szablewski.bartosz.restHouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.repository.GuestRepository;

import java.util.Optional;


@Service
public class GuestService {

    private GuestRepository guestRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public GuestService(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Guest> findByUserName(String name) {
        return guestRepository.findByUserName(name);
    }

    public void saveUser(Guest guest) {

        guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        guest.setRole("ROLE_GUEST");
        guestRepository.save(guest);
    }

    public void saveAdmin(Guest guest) {
        guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        guest.setRole("ROLE_ADMIN");
        guestRepository.save(guest);
    }

    public Guest findGuestByUserName(String userName) {
        return guestRepository.findGuestByUserName(userName);
    }
}