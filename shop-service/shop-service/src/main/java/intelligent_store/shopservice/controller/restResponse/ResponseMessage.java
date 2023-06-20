package intelligent_store.shopservice.controller.restResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    //==success==//
    CHANGE_SHOP_NAME_SUCCESS(200, "상호명 변경에 성공하였습니다."),
    CHANGE_TEL_SUCCESS(200, "전화번호 변경에 성공하였습니다."),

    //==fail==//
    SHOP_IS_NULL(404, "존재하지 않는 상점입니다."),
    USERNAME_NOT_MATCH(400, "일치하지 않는 회원입니다."),
    DUPLICATE_ENTITY_VALUE(400, "중복되는 데이터가 존재합니다.");

    private final int status;
    private final String value;
}
