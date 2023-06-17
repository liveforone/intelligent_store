package intelligent_store.userservice.query.util;

import intelligent_store.userservice.domain.Member;
import intelligent_store.userservice.dto.response.MemberResponse;

public class MemberMapper {

    public static MemberResponse entityToDto(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .realName(member.getRealName())
                .auth(member.getAuth())
                .address(member.getAddress())
                .build();
    }
}
