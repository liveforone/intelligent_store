package intelligent_store.mileageservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mileage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private long mileagePoint;

    private Mileage(String username) {
        this.username = username;
        this.mileagePoint = 0;
    }

    public static Mileage create(String username) {
        return new Mileage(username);
    }
}
