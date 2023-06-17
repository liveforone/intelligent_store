package intelligent_store.userservice.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControllerLog {

    SIGNUP_SUCCESS("회원 가입 성공"),
    LOGIN_SUCCESS("로그인 성공"),
    CHANGE_EMAIL_SUCCESS("이메일 변경 성공 | Username : "),
    CHANGE_PASSWORD_SUCCESS("비밀번호 변경 성공 | Username : "),
    CHANGE_ADDRESS_SUCCESS("주소 변경 성공 | Username : "),
    WITHDRAW_SUCCESS("회원 탈퇴 성공 | Username : ");

    private final String value;
}
