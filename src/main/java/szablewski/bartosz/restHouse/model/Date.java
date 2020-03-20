package szablewski.bartosz.restHouse.model;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.persistence.*;

@Data
@Entity
public class Date {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "local_Date_check", columnDefinition = "DATE")
    private java.util.Date localDate;
    @Column(name = "local_Date_checkOut", columnDefinition = "DATE")
    private java.util.Date localDateOut;

    @ManyToOne
    private Room room;
}
