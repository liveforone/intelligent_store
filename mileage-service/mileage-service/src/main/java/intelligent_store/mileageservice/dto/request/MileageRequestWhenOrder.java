package intelligent_store.mileageservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MileageRequestWhenOrder {
    private long itemPrice;
    private long spentMileage;
    private String username;
}
