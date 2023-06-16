package intelligent_store.userservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private String roadNum;
}
