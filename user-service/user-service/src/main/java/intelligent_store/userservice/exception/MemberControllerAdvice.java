package intelligent_store.userservice.exception;

import intelligent_store.userservice.controller.restResponse.RestResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<?> loginFailHandle() {
        return RestResponse.loginFail();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<?> duplicateEntityValue() {
        return RestResponse.duplicateEntityValue();
    }

    @ExceptionHandler(MemberCustomException.class)
    protected ResponseEntity<?> memberCustomHandle(MemberCustomException customException) {
        return ResponseEntity
                .status(customException.getResponseMessage().getStatus())
                .body(customException.getResponseMessage().getValue());
    }

    @ExceptionHandler(BindingCustomException.class)
    protected ResponseEntity<?> bindingErrorHandle(BindingCustomException customException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(customException.getMessage());
    }
}