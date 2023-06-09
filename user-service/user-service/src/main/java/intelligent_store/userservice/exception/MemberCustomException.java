package intelligent_store.userservice.exception;

import intelligent_store.userservice.controller.restResponse.ResponseMessage;
import lombok.Getter;

@Getter
public class MemberCustomException extends RuntimeException{
    private ResponseMessage responseMessage;

    public MemberCustomException(ResponseMessage responseMessage) {
        super(responseMessage.getValue());
        this.responseMessage = responseMessage;
    }
}
