package intelligent_store.userservice.controller;

import intelligent_store.userservice.command.MemberCommandService;
import intelligent_store.userservice.controller.constant.ControllerLog;
import intelligent_store.userservice.controller.restResponse.RestResponse;
import intelligent_store.userservice.domain.Role;
import intelligent_store.userservice.dto.signupAndLogin.MemberLoginRequest;
import intelligent_store.userservice.dto.signupAndLogin.MemberSignupRequest;
import intelligent_store.userservice.jwt.TokenInfo;
import intelligent_store.userservice.jwt.constant.JwtConstant;
import intelligent_store.userservice.query.MemberQueryService;
import intelligent_store.userservice.validator.ControllerValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static intelligent_store.userservice.controller.constant.MemberUrl.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final ControllerValidator controllerValidator;

    @PostMapping(SIGNUP_MEMBER)
    public ResponseEntity<?> signupMember(
            @RequestBody @Valid MemberSignupRequest requestDto,
            BindingResult bindingResult
    ) {
        controllerValidator.validateBinding(bindingResult);

        memberCommandService.signup(requestDto, Role.MEMBER);
        log.info(ControllerLog.SIGNUP_SUCCESS.getValue());

        return RestResponse.signupSuccess();
    }

    @PostMapping(SIGNUP_SELLER)
    public ResponseEntity<?> signupSeller(
            @RequestBody @Valid MemberSignupRequest requestDto,
            BindingResult bindingResult
    ) {
        controllerValidator.validateBinding(bindingResult);

        memberCommandService.signup(requestDto, Role.SELLER);
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
}
