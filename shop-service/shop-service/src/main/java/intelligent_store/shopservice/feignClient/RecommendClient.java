package intelligent_store.shopservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import static intelligent_store.shopservice.feignClient.constant.RecommendUrl.*;

@FeignClient(name = BASE)
public interface RecommendClient {

    @GetMapping(GET_RECOMMEND_SHOP_ID)
    Long getRecommendShopId();
}
