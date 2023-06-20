package intelligent_store.shopservice.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControllerLog {

    CHANGE_SHOP_NAME_SUCCESS("상호 변경 성공 | Shop Id : ");

    private final String value;
}
