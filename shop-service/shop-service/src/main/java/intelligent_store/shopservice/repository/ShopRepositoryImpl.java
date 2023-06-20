package intelligent_store.shopservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_store.shopservice.domain.QShop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ShopRepositoryImpl implements ShopCustomRepository {

    private final JPAQueryFactory queryFactory;
    QShop shop = QShop.shop;
}
