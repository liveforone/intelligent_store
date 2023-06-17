package intelligent_store.userservice.repository;

import intelligent_store.userservice.domain.Member;
import intelligent_store.userservice.dto.response.AddressResponse;
import intelligent_store.userservice.dto.response.BankbookResponse;

import java.util.Optional;

public interface MemberCustomRepository {

    Optional<Member> findOneById(Long id);
    Optional<Member> findOneByEmail(String email);
    Optional<Member> findOneByUsername(String username);
    BankbookResponse findBankbookNumByUsername(String username);
    AddressResponse findAddressByUsername(String username);
}
