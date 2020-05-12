package szablewski.bartosz.restHouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.model.GuestDetail;
import szablewski.bartosz.restHouse.repository.GuestRepository;

import java.util.Optional;

@Service
public class GuestDetailService implements UserDetailsService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestDetailService(GuestRepository guestRepository){
        this.guestRepository = guestRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Guest> guest = guestRepository.findByUserName(userName);

        guest.orElseThrow(() -> new UsernameNotFoundException("Not found " + userName));

        return guest.map(GuestDetail::new).get();
    }
}