package intelligent_store.mileageservice.controller.query;

import intelligent_store.mileageservice.authentication.AuthenticationInfo;
import intelligent_store.mileageservice.controller.constant.MileageParam;
import intelligent_store.mileageservice.dto.response.MileageResponse;
import intelligent_store.mileageservice.service.query.MileageQueryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static intelligent_store.mileageservice.controller.constant.MileageUrl.*;

@RestController
@RequiredArgsConstructor
public class MileageController {

    private final MileageQueryService mileageQueryService;
    private final AuthenticationInfo authenticationInfo;

    @GetMapping(MY_MILEAGE_GET)
    public ResponseEntity<?> myMileageGet(
            @PathVariable(MileageParam.USERNAME) String username
    ) {
        MileageResponse mileage = mileageQueryService.getMileageByUsername(username);
        return ResponseEntity.ok(mileage);
    }

    @PostMapping(MY_MILEAGE_POST)   //주문시 조회하도록 post 지원
    public ResponseEntity<?> myMileagePost(
            HttpServletRequest request
    ) {
        MileageResponse mileage = mileageQueryService.getMileageByUsername(authenticationInfo.getUsername(request));
        return ResponseEntity.ok(mileage);
    }
}
