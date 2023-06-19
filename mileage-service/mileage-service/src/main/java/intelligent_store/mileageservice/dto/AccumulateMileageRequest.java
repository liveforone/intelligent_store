package intelligent_store.mileageservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccumulateMileageRequest {
    private long orderPrice;
    private String username;
}
