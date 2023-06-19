package intelligent_store.mileageservice.repository;

import intelligent_store.mileageservice.domain.Mileage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageRepository extends JpaRepository<Mileage, Long>, MileageCustomRepository {
}
