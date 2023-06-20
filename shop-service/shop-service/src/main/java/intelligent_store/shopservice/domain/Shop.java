package intelligent_store.shopservice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String tel;

    private Shop(String username, String shopName, String tel) {
        this.username = username;
        this.shopName = shopName;
        this.tel = tel;
    }

    public void updateShopName(String shopName) {
        this.shopName = shopName;
    }

    public void updateTel(String tel) {
        this.tel = tel;
    }
}
