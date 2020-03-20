package szablewski.bartosz.restHouse.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import szablewski.bartosz.restHouse.model.Room;
import szablewski.bartosz.restHouse.repository.RoomRepository;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RoomServiceTest {

    //TODO TEST!

    private List<Room> prepareRoomData(){
        Room room = new Room();
        room.setId((long) 1);
        room.setBeds(2);
        room.setReservation(false);

        Room room2 = new Room();
        room2.setId((long) 2);
        room2.setBeds(3);
        room2.setReservation(false);

        Room room3 = new Room();
        room3.setId((long) 3);
        room3.setBeds(1);
        room3.setReservation(true);

        return Arrays.asList(room, room2, room3);
    }

    @Test
    void shouldFindAllRooms() {

        //given
        List<Room> roomList = prepareRoomData();
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomService roomService = new RoomService(roomRepository);
        when(roomRepository.findAll()).thenReturn(roomList);

        //when
        List<Room> allRooms = roomService.findAllRooms();

        //then
        assertThat(allRooms, hasSize(3));
    }

    @Test
    void shouldFindAllRoomsWhichIsReservationFalse() {

        //given
        Room room = new Room();
        room.setReservation(false);
        room.setId((long) 1);

        List<Room> roomList = Arrays.asList(room);
        RoomRepository roomRepository = mock(RoomRepository.class);
        RoomService roomService = new RoomService(roomRepository);
        when(roomRepository.findAllRoomsByIsReservation(false)).thenReturn(roomList);

        //when
        List<Room> allRooms = roomService.findAllRoomsWhichIsNotBooking(false);

        //then
        assertThat(allRooms, hasSize(1));
    }

    @Test
    void saveRoom() {
    }

    @Test
    void findById() {
    }

    @Test
    void cancelBook() {
    }
}
