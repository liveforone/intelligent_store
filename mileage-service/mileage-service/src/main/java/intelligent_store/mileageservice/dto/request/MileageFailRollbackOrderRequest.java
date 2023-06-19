package intelligent_store.mileageservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MileageFailRollbackOrderRequest {
    private long itemPrice;
    private long spentMileage;
    private String username;
}
