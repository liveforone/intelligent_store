package intelligent_store.mileageservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_store.mileageservice.domain.Mileage;
import intelligent_store.mileageservice.domain.QMileage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MileageRepositoryImpl implements MileageCustomRepository {

    private final JPAQueryFactory queryFactory;
    QMileage mileage = QMileage.mileage;

    public Mileage findOneByUsername(String username) {
        return queryFactory
                .selectFrom(mileage)
                .where(mileage.username.eq(username))
                .fetchOne();
    }
}
