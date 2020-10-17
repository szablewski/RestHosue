package szablewski.bartosz.restHouse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue
    private long id;
    private double price;
    private int beds;
    private boolean isReservation = false;
    private String description;

    @ManyToOne
    private Guest guest;
}