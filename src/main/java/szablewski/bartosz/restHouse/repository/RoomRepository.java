package szablewski.bartosz.restHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import szablewski.bartosz.restHouse.model.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllRoomsByIsReservation(boolean isReservation);
    Room findRoomById(long id);
}
