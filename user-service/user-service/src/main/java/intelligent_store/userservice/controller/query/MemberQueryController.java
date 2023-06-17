package intelligent_store.userservice.controller.query;

import intelligent_store.userservice.authentication.AuthenticationInfo;
import intelligent_store.userservice.controller.constant.MemberParam;
import intelligent_store.userservice.controller.restResponse.RestResponse;
import intelligent_store.userservice.dto.response.AddressResponse;
import intelligent_store.userservice.dto.response.BankbookResponse;
import intelligent_store.userservice.dto.response.MemberResponse;
import intelligent_store.userservice.query.MemberQueryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static intelligent_store.userservice.controller.constant.MemberUrl.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberQueryController {

    private final MemberQueryService memberQueryService;
    private final AuthenticationInfo authenticationInfo;

    @GetMapping(MY_INFO)
    public ResponseEntity<?> myInfo(HttpServletRequest request) {
        String username = authenticationInfo.getUsername(request);
        MemberResponse member = memberQueryService.getMemberByUsername(username);

        return ResponseEntity.ok(member);
    }

    @GetMapping(MY_BANKBOOK_NUM)
    public ResponseEntity<?> myBankbookNum(
            @PathVariable(MemberParam.USERNAME) String username
    ) {
        BankbookResponse bankbook = memberQueryService.getBankbookByUsername(username);
        return ResponseEntity.ok(bankbook);
    }

    @GetMapping(MY_ADDRESS)
    public ResponseEntity<?> myAddress(
            @PathVariable(MemberParam.USERNAME) String username
    ) {
        AddressResponse address = memberQueryService.getAddressByUsername(username);
        return ResponseEntity.ok(address);
    }

    @GetMapping(PROHIBITION)
    public ResponseEntity<?> prohibition() {
        return RestResponse.prohibition();
    }
}
