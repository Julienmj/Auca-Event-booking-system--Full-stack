package eventbooking.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regId;

    private String name;
    private Long eventId;
}
