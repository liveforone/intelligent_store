package intelligent_store.userservice.service.command;

import intelligent_store.userservice.controller.restResponse.ResponseMessage;
import intelligent_store.userservice.domain.Member;
import intelligent_store.userservice.domain.Role;
import intelligent_store.userservice.dto.changeInfo.ChangeAddressRequest;
import intelligent_store.userservice.dto.changeInfo.ChangeEmailRequest;
import intelligent_store.userservice.dto.changeInfo.ChangePasswordRequest;
import intelligent_store.userservice.dto.signupAndLogin.MemberLoginRequest;
import intelligent_store.userservice.dto.signupAndLogin.MemberSignupRequest;
import intelligent_store.userservice.exception.MemberCustomException;
import intelligent_store.userservice.jwt.JwtTokenProvider;
import intelligent_store.userservice.jwt.TokenInfo;
import intelligent_store.userservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long signup(MemberSignupRequest requestDto, Role auth) {
        Member member = Member.create(
                requestDto.getEmail(),
                requestDto.getBankbookNum(),
                requestDto.getPassword(),
                requestDto.getRealName(),
                auth,
                requestDto.getCity(),
                requestDto.getRoadNum(),
                requestDto.getDetail()
        );
        return memberRepository.save(member).getId();
    }

    public TokenInfo login(MemberLoginRequest requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        Member member = memberRepository.findOneByEmail(email)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(member.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);

        return jwtTokenProvider
                .generateToken(authentication);
    }

    public void updateEmail(ChangeEmailRequest requestDto, String username) {
        Member member = memberRepository.findOneByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));
        member.updateEmail(requestDto.getEmail());
    }

    public void updatePassword(ChangePasswordRequest requestDto, String username) {
        Member member = memberRepository.findOneByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));
        member.updatePassword(requestDto.getNewPassword(), requestDto.getOldPassword());
    }

    public void updateAddress(ChangeAddressRequest requestDto, String username) {
        Member member = memberRepository.findOneByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));
        member.updateAddress(requestDto.getCity(), requestDto.getRoadNum(), requestDto.getDetail());
    }

    public void withdraw(String username) {
        Member member = memberRepository.findOneByUsername(username)
                .orElseThrow(() -> new MemberCustomException(ResponseMessage.MEMBER_IS_NULL));
        memberRepository.delete(member);
    }
}
