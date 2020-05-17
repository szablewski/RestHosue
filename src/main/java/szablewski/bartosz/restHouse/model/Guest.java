package szablewski.bartosz.restHouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Guest {

    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    private String userName;
    private String firstName;
    private String lastName;
    @Size(min = 8)
    private String password;
    private String email;
    private String role;
    private LocalDateTime created;
    private boolean isEnabled;

    @OneToMany(mappedBy = "guest")
    @JsonIgnore
    private List<Room> guestRoom;

    public void setCreate(LocalDateTime now) {

    }
}