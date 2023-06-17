package intelligent_store.userservice.query;

import intelligent_store.userservice.controller.restResponse.ResponseMessage;
import intelligent_store.userservice.dto.response.MemberResponse;
import intelligent_store.userservice.exception.MemberCustomException;
import intelligent_store.userservice.query.util.MemberMapper;
import intelligent_store.userservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public MemberResponse getMemberByEmail(String email) {
        return MemberMapper.entityToDto(
                memberRepository.findOneByEmail(email)
                        .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL))
        );
    }

    public MemberResponse getMemberByUsername(String username) {
        return MemberMapper.entityToDto(
                memberRepository.findOneByUsername(username)
                        .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL))
        );
    }
}
