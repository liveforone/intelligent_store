package intelligent_store.mileageservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderFailRollbackMileageRequest {
    private long itemPrice;  //적립취소를 위함
    private long spentMileage;  //사용한 마일리지 복구
    private String username;
}
