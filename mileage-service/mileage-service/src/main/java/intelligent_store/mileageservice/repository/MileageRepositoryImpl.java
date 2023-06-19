package intelligent_store.mileageservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_store.mileageservice.domain.QMileage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MileageRepositoryImpl {

    private final JPAQueryFactory queryFactory;
    QMileage mileage = QMileage.mileage;
}
