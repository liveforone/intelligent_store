package intelligent_store.userservice.domain;

import intelligent_store.userservice.domain.util.PasswordUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    void createMemberTest() {
        //given
        String email = "create_test@gmail.com";
        String bankbookNum = "1234567898765";
        String password = "1111";
        String realName = "create_test_member";
        String city = "서울";
        String roadNum = "종로5가-1234-1";
        String detail = "101동 101호";

        //when
        Member member = Member.createMember(email, bankbookNum, password, realName, city, roadNum, detail);

        //then
        assertThat(member.getAuth())
                .isEqualTo(Role.MEMBER);
    }

    @Test
    void createSellerTest() {
        //given
        String email = "create_test@gmail.com";
        String bankbookNum = "1234567898765";
        String password = "1111";
        String realName = "create_test_seller";
        String city = "서울";
        String roadNum = "종로3가-1114-2";
        String detail = "102동 102호";

        //when
        Member member = Member.createSeller(email, bankbookNum, password, realName, city, roadNum, detail);

        //then
        assertThat(member.getAuth())
                .isEqualTo(Role.SELLER);
    }

    @Test
    void updateEmailTest() {
        //given
        String email = "email_test@gmail.com";
        String bankbookNum = "1234567898765";
        String password = "1111";
        String realName = "update_email";
        String city = "서울";
        String roadNum = "종로2가-2222-4";
        String detail = "201동 102호";
        Member member = Member.createMember(email, bankbookNum, password, realName, city, roadNum, detail);

        //when
        String newEmail = "new_email@gmail.com";
        member.updateEmail(newEmail);

        //then
        assertThat(member.getEmail())
                .isEqualTo(newEmail);
    }

    @Test
    void updatePasswordTest() {
        //given
        String email = "password_test@gmail.com";
        String bankbookNum = "1234567898765";
        String password = "1111";
        String realName = "password_test";
        String city = "서울";
        String roadNum = "종로4가-4234-4";
        String detail = "401동 401호";
        Member member = Member.createMember(email, bankbookNum, password, realName, city, roadNum, detail);

        //when
        String newPassword = "1234";
        member.updatePassword(newPassword, password);

        //then
        assertThat(PasswordUtils.isMatchPassword(newPassword, member.getPassword()))
                .isTrue();
    }

    @Test
    void updateAddressTest() {
        //given
        String email = "address_test@gmail.com";
        String bankbookNum = "1234567898765";
        String password = "1111";
        String realName = "address_test";
        String city = "서울";
        String roadNum = "종로1가-1111-1";
        String detail = "101동 101호";
        Member member = Member.createMember(email, bankbookNum, password, realName, city, roadNum, detail);

        //when
        String newCity = "경기도";
        String newRoadNum = "판교대장로 7길";
        String newDetail = "7동 707호";
        member.updateAddress(newCity, newRoadNum, newDetail);

        //then
        assertThat(member.getAddress().getDetail())
                .isEqualTo(newDetail);
    }
}