package intelligent_store.userservice.command;

import intelligent_store.userservice.controller.restResponse.ResponseMessage;
import intelligent_store.userservice.domain.Member;
import intelligent_store.userservice.domain.Role;
import intelligent_store.userservice.exception.MemberCustomException;
import intelligent_store.userservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return createUserDetails(memberRepository.findOneByUsername(email)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL)));
    }

    private UserDetails createUserDetails(Member member) {
        if (member.getAuth() == Role.ADMIN) {
            String ADMIN_ROLE = "ADMIN";
            return User.builder()
                    .username(member.getUsername())
                    .password(member.getPassword())
                    .roles(ADMIN_ROLE)
                    .build();
        } else if (member.getAuth() == Role.SELLER) {
            String SELLER_ROLE = "SELLER";
            return User.builder()
                    .username(member.getUsername())
                    .password(member.getPassword())
                    .roles(SELLER_ROLE)
                    .build();
        } else {
            String MEMBER_ROLE = "MEMBER";
            return User.builder()
                    .username(member.getUsername())
                    .password(member.getPassword())
                    .roles(MEMBER_ROLE)
                    .build();
        }
    }
}
