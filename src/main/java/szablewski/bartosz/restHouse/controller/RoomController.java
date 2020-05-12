package szablewski.bartosz.restHouse.controller;

import javassist.NotFoundException;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.model.Room;
import szablewski.bartosz.restHouse.service.DateService;
import szablewski.bartosz.restHouse.service.GuestService;
import szablewski.bartosz.restHouse.service.RoomService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class RoomController {

    private RoomService roomService;
    private GuestService guestService;
    private DateService dateService;

    @Autowired
    public RoomController(DateService dateService, RoomService roomService, GuestService guestService) {
        this.roomService = roomService;
        this.guestService = guestService;
        this.dateService = dateService;
    }

    /**
     * @param model
     * @return all rooms
     */
    @GetMapping("/")
    public String findAllRooms(Model model) {
        model.addAttribute("rooms", roomService.findAllRooms());
        return "rooms";
    }

    /**
     * @param model
     * @return all rooms
     */
    @GetMapping("/rooms")
    public String findAllRoomsIsNotBooking(Model model) {
        model.addAttribute("rooms", roomService.findAllRooms());
        return "rooms";
    }

    /**
     * @param model
     * @return all rooms that are already booked
     */
    @GetMapping("/rooms/reservation")
    public String findAllRoomsIsBooking(Model model) {

        //TODO in the application view the "Rooms isn't reservation" button should be invisible or inactive
        model.addAttribute("rooms", roomService.findAllRoomsWhichIsNotBooking(false));
        return "rooms";
    }

    /**
     * @param model
     * @return room form
     */
    @GetMapping("/room")
    public String roomForm(Model model) {
        model.addAttribute("room", new Room());
        return "roomForm";
    }

    /**
     * @param room
     * @param result
     * @param model
     * @return create new room
     */
    @PostMapping("/room")
    public String saveRoom(Room room, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute(room);
            model.addAttribute("message", result.getAllErrors());
            return "signUp";
        } else {
            roomService.saveRoom(room);
            return "redirect:/rooms";
        }
    }

    /**
     * @param id    - id current room
     * @param model
     * @return detail room with current id
     * @throws NotFoundException
     */
    @GetMapping("/rooms/{id}")
    public String roomDetail(@PathVariable("id") long id, Model model) throws NotFoundException {

        Optional<Room> room = roomService.findById(id);

        model.addAttribute("room", room.orElseThrow(() ->
                new NotFoundException("Not found room with id: " + room.get().getId())));
        return "roomDetails";
    }

    /**
     * @param id           - current room
     * @param localDate    - start date of stay
     * @param localDateOut - end date of stay
     * @param model
     * @param principal
     * @return booking room with current user
     * @throws NotFoundException
     */
    @PostMapping("/rooms/{id}/book")
    public String bookingRoom(@PathVariable("id") long id, @RequestParam("localDate") String localDate,
                              @RequestParam("localDateOut") String localDateOut,
                              Model model, Principal principal) throws NotFoundException {

        //TODO when the room is reserved, the next user cannot write the reservation
        Optional<Room> room = roomService.findById(id);

        String userName = principal.getName();
        Guest guest = guestService.findGuestByUserName(userName);

        if (!dateService.checkingDates(localDate, localDateOut) == true) {
            model.addAttribute("info", "Please select correct dates");
            return "roomDetails";
        }

        //TODO improve date parsing
        LocalDate ld = dateService.parseCheck(localDate);
        LocalDate ld1 = dateService.parseCheck(localDateOut);
        java.util.Date parseLocalDate = dateService.parseLocalDate(ld);
        java.util.Date parseLocalDate1 = dateService.parseLocalDate(ld1);
        model.addAttribute("room", room.orElseThrow(() ->
                new NotFoundException("Not found room with id: " + room.get().getId())));
        dateService.bookRoom(parseLocalDate, parseLocalDate1, id, guest);
        return "redirect:/rooms";
    }

    /**
     * @param id
     * @return cancel reservation
     */
    @PostMapping("/rooms/{id}/cancel")
    public String cancelBook(@PathVariable("id") long id) {

        //TODO only users who have made a reservation should be able to delete reservations
        roomService.cancelBook(id);
        return "redirect:/rooms";
    }
}