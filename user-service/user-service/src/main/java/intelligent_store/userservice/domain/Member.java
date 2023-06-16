package intelligent_store.userservice.domain;

import intelligent_store.userservice.controller.restResponse.ResponseMessage;
import intelligent_store.userservice.converter.RoleConverter;
import intelligent_store.userservice.domain.util.MemberConstant;
import intelligent_store.userservice.domain.util.PasswordUtils;
import intelligent_store.userservice.exception.MemberCustomException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String bankbookNum;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable = false)
    private String realName;

    @Convert(converter = RoleConverter.class)
    @Column(nullable = false)
    private Role auth;

    @Embedded
    private Address address;

    private Member(String username, String email, String bankbookNum, String password, String realName, Role auth, Address address) {
        this.username = username;
        this.email = email;
        this.bankbookNum = bankbookNum;
        this.password = password;
        this.realName = realName;
        this.auth = auth;
        this.address = address;
    }

    public static Member createMember(String email, String bankbookNum, String password, String realName, String city, String detail, String roadNum) {
        final String ADMIN = "admin@intelligentstore.com";

        return new Member(
                createUsername(),
                email,
                bankbookNum,
                PasswordUtils.encodePassword(password),
                realName,
                ADMIN.equals(email) ? Role.ADMIN : Role.MEMBER,
                new Address(city, detail, roadNum)
        );
    }

    public static Member createSeller(String email, String bankbookNum, String password, String realName, String city, String detail, String roadNum) {
        return new Member(
                createUsername(),
                email,
                bankbookNum,
                PasswordUtils.encodePassword(password),
                realName,
                Role.SELLER,
                new Address(city, detail, roadNum)
        );
    }

    private static String createUsername() {
        return UUID.randomUUID() + RandomStringUtils.randomAlphabetic(MemberConstant.RANDOM_STRING_LENGTH);
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }

    public void updatePassword(String newPassword, String originalPassword) {
        if (!PasswordUtils.isMatchPassword(originalPassword, password)) {
            throw new MemberCustomException(ResponseMessage.NOT_MATCH_PASSWORD);
        }

        this.password = PasswordUtils.encodePassword(newPassword);
    }

    public void updateAddress(String city, String detail, String roadNum) {
        this.address = new Address(city, detail, roadNum);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(auth.getAuth()));
        return authList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
