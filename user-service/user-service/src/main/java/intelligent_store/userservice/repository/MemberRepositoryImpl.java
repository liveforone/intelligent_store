package intelligent_store.userservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_store.userservice.domain.Member;
import intelligent_store.userservice.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;

    public Optional<Member> findOneById(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.id.eq(id))
                .fetchOne());
    }

    public Optional<Member> findOneByEmail(String email) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne());
    }

    public Optional<Member> findOneByUsername(String username) {
        return Optional.ofNullable(queryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetchOne());
    }
}
