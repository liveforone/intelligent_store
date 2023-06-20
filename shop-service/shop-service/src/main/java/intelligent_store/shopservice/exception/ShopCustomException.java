package intelligent_store.shopservice.exception;

import intelligent_store.shopservice.controller.restResponse.ResponseMessage;
import lombok.Getter;

@Getter
public class ShopCustomException extends RuntimeException{
    private ResponseMessage responseMessage;

    public ShopCustomException(ResponseMessage responseMessage) {
        super(responseMessage.getValue());
        this.responseMessage = responseMessage;
    }
}
