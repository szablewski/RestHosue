package szablewski.bartosz.restHouse.service;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szablewski.bartosz.restHouse.model.Date;
import szablewski.bartosz.restHouse.model.Guest;
import szablewski.bartosz.restHouse.model.Room;
import szablewski.bartosz.restHouse.repository.DateRepository;
import szablewski.bartosz.restHouse.repository.RoomRepository;

@Service
public class DateService {

    private DateRepository dateRepository;
    private RoomRepository roomRepository;

    @Autowired
    public DateService(DateRepository dateRepository, RoomRepository roomRepository) {
        this.dateRepository = dateRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Gives start date by String from thymeleaf form
     *
     * @param check - String to be parsed to LocalDate
     * @return LocalDate of start date.
     */
    public LocalDate parseCheck(String check) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dtf.parseLocalDate(check);
    }

    /**
     * Gives end date by String from thymeleaf form
     *
     * @param checkOut - String to be parsed to LocalDate
     * @return LocalDate of end date.
     */
    public LocalDate parseCheckOut(String checkOut) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dtf.parseLocalDate(checkOut);
    }

    /**
     * Check whether dates given as a String are correct. End date must be after the
     * start date and start date must not be before today.
     *
     * @param check    - String of start date.
     * @param checkOut - String of end date.
     * @return true or false depending on compared dates.
     */
    public boolean checkingDates(String check, String checkOut) {
        if (parseCheck(check).isAfter(parseCheckOut(checkOut)) || parseCheck(check).isBefore(LocalDate.now())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * method parses from "LocalDate" to "util.Date"
     *
     * @param localDate
     * @return
     */
    public java.util.Date parseLocalDate(LocalDate localDate) {
        java.util.Date date = localDate.toDateTimeAtStartOfDay().toDate();
        return date;
    }

    /**
     * Books room, sets start and end dates and saves to room.
     *
     * @param check    - start date.
     * @param checkOut - end date.
     * @param id       - id of the room to be booked.
     */
    public void bookRoom(java.util.Date check, java.util.Date checkOut, long id, Guest guest) {
        try {
            Room room = roomRepository.findRoomById(id);
            room.setReservation(true);
            room.setGuest(guest);
            roomRepository.save(room);
            Date date = new Date();
            date.setLocalDate(check);
            date.setLocalDateOut(checkOut);
            date.setRoom(room);
            dateRepository.save(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
