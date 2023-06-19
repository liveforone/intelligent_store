package intelligent_store.mileageservice.dto.util;

import intelligent_store.mileageservice.domain.Mileage;
import intelligent_store.mileageservice.dto.request.MileageFailRollbackOrderRequest;
import intelligent_store.mileageservice.dto.request.MileageRequestWhenOrder;
import intelligent_store.mileageservice.dto.response.MileageResponse;

public class MileageMapper {

    public static MileageResponse entityToDto(Mileage mileage) {
        return MileageResponse
                .builder()
                .id(mileage.getId())
                .mileagePoint(mileage.getMileagePoint())
                .build();
    }

    public static MileageFailRollbackOrderRequest mileageRequestToFailRollbackOrder(MileageRequestWhenOrder requestDto) {
        return MileageFailRollbackOrderRequest.builder()
                .itemPrice(requestDto.getItemPrice())
                .spentMileage(requestDto.getSpentMileage())
                .username(requestDto.getUsername())
                .build();
    }
}
