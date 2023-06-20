package intelligent_store.shopservice.clientWrapper;

import intelligent_store.shopservice.feignClient.RecommendClient;
import intelligent_store.shopservice.feignClient.constant.CircuitLog;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecommendClientWrapper {
    private final RecommendClient recommendClient;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    public Long getRecommendId() {
        return circuitBreakerFactory
                .create(CircuitLog.SHOP_CIRCUIT_LOG.getValue())
                .run(
                        recommendClient::getRecommendShopId,
                        throwable -> null
                );
    }
}
