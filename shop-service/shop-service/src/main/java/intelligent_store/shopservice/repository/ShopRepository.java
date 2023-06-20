package intelligent_store.shopservice.repository;

import intelligent_store.shopservice.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long>, ShopCustomRepository {
}
