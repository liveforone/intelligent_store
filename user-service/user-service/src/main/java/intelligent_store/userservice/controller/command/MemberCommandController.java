package intelligent_store.userservice.controller.command;

import intelligent_store.userservice.authentication.AuthenticationInfo;
import intelligent_store.userservice.dto.signupAndLogin.SellerSignupRequest;
import intelligent_store.userservice.producer.model.MemberProducer;
import intelligent_store.userservice.service.command.MemberCommandService;
import intelligent_store.userservice.controller.constant.ControllerLog;
import intelligent_store.userservice.controller.restResponse.RestResponse;
import intelligent_store.userservice.dto.changeInfo.ChangeAddressRequest;
import intelligent_store.userservice.dto.changeInfo.ChangeEmailRequest;
import intelligent_store.userservice.dto.changeInfo.ChangePasswordRequest;
import intelligent_store.userservice.dto.signupAndLogin.MemberLoginRequest;
import intelligent_store.userservice.dto.signupAndLogin.MemberSignupRequest;
import intelligent_store.userservice.jwt.TokenInfo;
import intelligent_store.userservice.jwt.constant.JwtConstant;
import intelligent_store.userservice.validator.ControllerValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static intelligent_store.userservice.controller.constant.MemberUrl.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberCommandController {

    private final MemberCommandService memberCommandService;
    private final MemberProducer memberProducer;
    private final AuthenticationInfo authenticationInfo;
    private final ControllerValidator controllerValidator;

    @PostMapping(SIGNUP_MEMBER)
    public ResponseEntity<?> signupMemberWithCreateMileage(
            @RequestBody @Valid MemberSignupRequest requestDto,
            BindingResult bindingResult
    ) {
        controllerValidator.validateBinding(bindingResult);

        String username = memberCommandService.signupMember(requestDto);
        memberProducer.createMileage(username);
        log.info(ControllerLog.SIGNUP_SUCCESS.getValue());

        return RestResponse.signupSuccess();
    }

    @PostMapping(SIGNUP_SELLER)
    public ResponseEntity<?> signupSellerWithCreateShop(
            @RequestBody @Valid SellerSignupRequest requestDto,
            BindingResult bindingResult
    ) {
        controllerValidator.validateBinding(bindingResult);

        String username = memberCommandService.signupSeller(requestDto);
        memberProducer.createShop(requestDto, username);
        log.info(ControllerLog.SIGNUP_SUCCESS.getValue());

        return RestResponse.signupSuccess();
    }

    @PostMapping(LOGIN)
    public ResponseEntity<?> login(
            @RequestBody @Valid MemberLoginRequest requestDto,
            BindingResult bindingResult,
            HttpServletResponse response
    ) {
        controllerValidator.validateBinding(bindingResult);

        TokenInfo tokenInfo = memberCommandService.login(requestDto);
        log.info(ControllerLog.LOGIN_SUCCESS.getValue());

        response.addHeader(JwtConstant.ACCESS_TOKEN, tokenInfo.getAccessToken());
        response.addHeader(JwtConstant.REFRESH_TOKEN, tokenInfo.getRefreshToken());
        return RestResponse.loginSuccess();
    }

    @PutMapping(CHANGE_EMAIL)
    public ResponseEntity<?> changeEmail(
            @RequestBody @Valid ChangeEmailRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateBinding(bindingResult);

        String username = authenticationInfo.getUsername(request);
        memberCommandService.updateEmail(requestDto, username);
        log.info(ControllerLog.CHANGE_EMAIL_SUCCESS.getValue() + username);

        return RestResponse.changeEmailSuccess();
    }

    @PutMapping(CHANGE_PASSWORD)
    public ResponseEntity<?> changePassword(
            @RequestBody @Valid ChangePasswordRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateBinding(bindingResult);

        String username = authenticationInfo.getUsername(request);
        memberCommandService.updatePassword(requestDto, username);
        log.info(ControllerLog.CHANGE_PASSWORD_SUCCESS.getValue() + username);

        return RestResponse.changePasswordSuccess();
    }

    @PutMapping(CHANGE_ADDRESS)
    public ResponseEntity<?> changeAddress(
            @RequestBody @Valid ChangeAddressRequest requestDto,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        controllerValidator.validateBinding(bindingResult);

        String username = authenticationInfo.getUsername(request);
        memberCommandService.updateAddress(requestDto, username);
        log.info(ControllerLog.CHANGE_ADDRESS_SUCCESS.getValue() + username);

        return RestResponse.changeAddressSuccess();
    }

    @DeleteMapping(WITHDRAW)
    public ResponseEntity<?> withdraw(HttpServletRequest request) {
        String username = authenticationInfo.getUsername(request);
        memberCommandService.withdraw(username);
        memberProducer.removeMileage(username);
        memberProducer.removeShop(username);
        log.info(ControllerLog.WITHDRAW_SUCCESS.getValue() + username);

        return RestResponse.withdrawSuccess();
    }
}
