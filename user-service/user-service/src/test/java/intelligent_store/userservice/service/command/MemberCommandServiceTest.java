package intelligent_store.userservice.service.command;

import intelligent_store.userservice.domain.Role;
import intelligent_store.userservice.dto.changeInfo.ChangeAddressRequest;
import intelligent_store.userservice.dto.changeInfo.ChangeEmailRequest;
import intelligent_store.userservice.dto.changeInfo.ChangePasswordRequest;
import intelligent_store.userservice.dto.signupAndLogin.MemberSignupRequest;
import intelligent_store.userservice.service.query.MemberQueryService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberCommandServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberCommandService memberCommandService;

    @Autowired
    MemberQueryService memberQueryService;

    String createMember(String email, String bankbookNum, String password, String realName, String city, String roadNum, String detail) {
        MemberSignupRequest requestDto = new MemberSignupRequest();
        requestDto.setEmail(email);
        requestDto.setBankbookNum(bankbookNum);
        requestDto.setPassword(password);
        requestDto.setRealName(realName);
        requestDto.setCity(city);
        requestDto.setRoadNum(roadNum);
        requestDto.setDetail(detail);
        return memberCommandService.signup(requestDto, Role.MEMBER);
    }

    @Test
    @Transactional
    void signupTest() {
        //given
        String email = "create_test@gmail.com";
        String bankbookNum = "1234567898765";
        String password = "1111";
        String realName = "create_test_member";
        String city = "서울";
        String roadNum = "종로1가-1234-1";
        String detail = "101동 101호";

        //when
        MemberSignupRequest requestDto = new MemberSignupRequest();
        requestDto.setEmail(email);
        requestDto.setBankbookNum(bankbookNum);
        requestDto.setPassword(password);
        requestDto.setRealName(realName);
        requestDto.setCity(city);
        requestDto.setRoadNum(roadNum);
        requestDto.setDetail(detail);
        String username = memberCommandService.signup(requestDto, Role.MEMBER);
        em.flush();
        em.clear();

        //then
        assertThat(memberQueryService.getMemberByUsername(username).getEmail())
                .isEqualTo(email);
    }

    @Test
    @Transactional
    void updateEmailTest() {
        //given
        String email = "email_test@gmail.com";
        String bankbookNum = "1234567898765";
        String password = "1111";
        String realName = "email_test";
        String city = "서울";
        String roadNum = "종로2가-2222-2";
        String detail = "201동 201호";
        String username = createMember(email, bankbookNum, password, realName, city, roadNum, detail);
        em.flush();
        em.clear();

        //when
        String newEmail = "updatedEmail@gmail.com";
        ChangeEmailRequest requestDto = new ChangeEmailRequest();
        requestDto.setEmail(newEmail);
        memberCommandService.updateEmail(requestDto, username);
        em.flush();
        em.clear();

        //then
        assertThat(memberQueryService.getMemberByUsername(username).getEmail())
                .isEqualTo(newEmail);
    }

    @Test
    @Transactional
    void updatePasswordTest() {
        //given
        String email = "password_test@gmail.com";
        String bankbookNum = "1234567898765";
        String password = "1111";
        String realName = "password_test";
        String city = "서울";
        String roadNum = "종로3가-3333-3";
        String detail = "301동 301호";
        String username = createMember(email, bankbookNum, password, realName, city, roadNum, detail);
        em.flush();
        em.clear();

        //when
        String newPassword = "1234";
        ChangePasswordRequest requestDto = new ChangePasswordRequest();
        requestDto.setNewPassword(newPassword);
        requestDto.setOldPassword(password);
        memberCommandService.updatePassword(requestDto, username);
        em.flush();
        em.clear();

        //then
        assertThat(memberQueryService.getMemberByUsername(username))
                .isNotNull();
    }

    @Test
    @Transactional
    void updateAddressTest() {
        //given
        String email = "address_test@gmail.com";
        String bankbookNum = "1234567898765";
        String password = "1111";
        String realName = "address_test";
        String city = "서울";
        String roadNum = "종로4가-4444-4";
        String detail = "401동 401호";
        String username = createMember(email, bankbookNum, password, realName, city, roadNum, detail);
        em.flush();
        em.clear();

        //when
        String newCity = "경기";
        String newRoadNum = "대왕 판교로 7길";
        String newDetail = "707동 707호";
        ChangeAddressRequest requestDto = new ChangeAddressRequest();
        requestDto.setCity(newCity);
        requestDto.setRoadNum(newRoadNum);
        requestDto.setDetail(newDetail);
        memberCommandService.updateAddress(requestDto, username);
        em.flush();
        em.clear();

        //then
        assertThat(memberQueryService.getMemberByUsername(username).getAddress().getDetail())
                .isEqualTo(newDetail);
    }
}