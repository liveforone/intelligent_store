package intelligent_store.userservice.command;

import intelligent_store.userservice.domain.Member;
import intelligent_store.userservice.domain.Role;
import intelligent_store.userservice.dto.signupAndLogin.MemberSignupRequest;
import intelligent_store.userservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;

    public Long createMember(MemberSignupRequest requestDto) {
        Member member = Member.create(
                requestDto.getEmail(),
                requestDto.getBankbookNum(),
                requestDto.getPassword(),
                requestDto.getRealName(),
                Role.MEMBER,
                requestDto.getCity(),
                requestDto.getRoadNum(),
                requestDto.getDetail()
        );
        return memberRepository.save(member).getId();
    }
}
