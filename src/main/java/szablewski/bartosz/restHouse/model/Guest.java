package szablewski.bartosz.restHouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
public class Guest {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String userName;
    private String firstName;
    private String lastName;
    @Size(min = 8)
    private String password;
    private String email;
    private String role;

    @OneToMany(mappedBy = "guest")
    @JsonIgnore
    private List<Room> guestRoom;

}