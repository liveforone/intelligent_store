package intelligent_store.shopservice.controller.restResponse;

import org.springframework.http.ResponseEntity;

public class RestResponse {

    //==fail==//
    public static ResponseEntity<?> duplicateEntityValue() {
        return ResponseEntity
                .status(ResponseMessage.DUPLICATE_ENTITY_VALUE.getStatus())
                .body(ResponseMessage.DUPLICATE_ENTITY_VALUE.getValue());
    }

    //==success==//
    public static ResponseEntity<?> changeShopNameSuccess() {
        return ResponseEntity.ok(ResponseMessage.CHANGE_SHOP_NAME_SUCCESS.getValue());
    }

    public static ResponseEntity<?> changeTelSuccess() {
        return ResponseEntity.ok(ResponseMessage.CHANGE_TEL_SUCCESS.getValue());
    }
}
