package intelligent_store.mileageservice.dto.util;

import intelligent_store.mileageservice.domain.Mileage;
import intelligent_store.mileageservice.dto.response.MileageResponse;

public class MileageMapper {

    public static MileageResponse entityToDto(Mileage mileage) {
        return MileageResponse
                .builder()
                .id(mileage.getId())
                .mileagePoint(mileage.getMileagePoint())
                .build();
    }
}
