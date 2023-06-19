package intelligent_store.mileageservice.dto.request;

import intelligent_store.mileageservice.dto.request.MileageFailRollbackOrderRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MileageRequestWhenOrder {
    private long itemPrice;
    private long spentMileage;
    private String username;

    public MileageFailRollbackOrderRequest toMileageFailRollbackOrderDto() {
        return MileageFailRollbackOrderRequest.builder()
                .itemPrice(itemPrice)
                .spentMileage(spentMileage)
                .username(username)
                .build();
    }
}
