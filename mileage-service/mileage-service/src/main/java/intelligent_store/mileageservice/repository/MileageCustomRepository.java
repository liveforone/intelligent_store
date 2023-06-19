package intelligent_store.mileageservice.repository;

import intelligent_store.mileageservice.domain.Mileage;

public interface MileageCustomRepository {
    Mileage findOneByUsername(String username);
}
