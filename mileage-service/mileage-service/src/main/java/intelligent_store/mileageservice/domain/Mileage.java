package intelligent_store.mileageservice.domain;

import intelligent_store.mileageservice.exception.MileageRequestFailException;
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

    public void accumulate(long itemPrice) {
        final double ACCUMULATE_PERCENT = 0.01;
        this.mileagePoint += (long) (itemPrice * ACCUMULATE_PERCENT);
    }

    public void useMileage(long spentMileage) {
        if (mileagePoint < spentMileage) {
            throw new MileageRequestFailException();
        }
        this.mileagePoint -= spentMileage;
    }

    public void rollbackMileage(long itemPrice, long spentMileage) {
        final double ACCUMULATE_PERCENT = 0.01;
        this.mileagePoint -= (long) (itemPrice * ACCUMULATE_PERCENT);
        this.mileagePoint += spentMileage;
    }
}
