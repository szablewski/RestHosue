package szablewski.bartosz.restHouse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Long id;
    private String value;

    @OneToOne
    private Guest guest;
}
