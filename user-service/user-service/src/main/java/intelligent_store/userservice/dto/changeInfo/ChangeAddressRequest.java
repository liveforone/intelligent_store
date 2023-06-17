package intelligent_store.userservice.dto.changeInfo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeAddressRequest {

    @NotBlank(message = "도시를 입력해주세요.")
    private String city;

    @NotBlank(message = "도로명 주소를 입력해주세요.")
    private String roadNum;

    @NotBlank(message = "상세주소를 입력해주세요.")
    private String detail;
}
