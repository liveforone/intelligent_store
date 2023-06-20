package intelligent_store.userservice.dto.signupAndLogin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SellerSignupRequest {

    @Email(message = "이메일 형식을 지켜 작성해주세요.")
    @NotBlank(message = "이메일은 반드시 기입해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 반드시 입력하세요.")
    private String password;

    @NotBlank(message = "실명은 반드시 기입해야하며, 잘못된 실명기입은 거래시 오류를 초래할 수 있습니다.")
    private String realName;

    @NotBlank(message = "계좌번호는 반드시 입력해주세요.")
    @Size(min = 13, max = 13, message = "계좌번호는 13자리입니다.")
    private String bankbookNum;

    @NotBlank(message = "도시를 입력해주세요.")
    private String city;

    @NotBlank(message = "도로명 주소를 입력해주세요.")
    private String roadNum;

    @NotBlank(message = "상세주소를 입력해주세요.")
    private String detail;

    @NotBlank(message = "상호명을 입력해주세요.")
    private String shopName;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String tel;
}
