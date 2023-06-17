package intelligent_store.userservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_store.userservice.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;
}
