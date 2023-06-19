package intelligent_store.mileageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MileageResponse {

    private Long id;
    private String username;
    private long mileagePoint;
}
