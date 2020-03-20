package szablewski.bartosz.restHouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szablewski.bartosz.restHouse.model.Room;
import szablewski.bartosz.restHouse.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * @return all rooms in restHotel
     */
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    /**
     * @param isReservation - isReservation flag
     * @return all rooms which isn't booking
     */
    public List<Room> findAllRoomsWhichIsNotBooking(boolean isReservation) {
        return roomRepository.findAllRoomsByIsReservation(false);
    }

    /**
     * @param room - object currently saved
     * @return
     */
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    /**
     * @param id - id current room
     * @return
     */
    public Optional<Room> findById(long id) {
        return roomRepository.findById(id);
    }

    /**
     * @param id - id current room
     * @return cancel book with current id
     */
    public Room cancelBook(long id) {
        Room room = roomRepository.getOne(id);
        room.setGuest(null);
        room.setReservation(false);
        return roomRepository.save(room);
    }
}