package intelligent_store.gatewayservice.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(FallbackUrl.BASE)
public class FallbackController {

    @GetMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserGet() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @PostMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserPost() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @PutMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserPatch() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @DeleteMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserDelete() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @GetMapping(FallbackUrl.MILEAGE)
    public Mono<String> fallbackMileageGet() {
        return Mono.just(FallbackMessage.MILEAGE_LOG);
    }

    @PostMapping(FallbackUrl.MILEAGE)
    public Mono<String> fallbackMileagePost() {
        return Mono.just(FallbackMessage.MILEAGE_LOG);
    }

    @GetMapping(FallbackUrl.SHOP)
    public Mono<String> fallbackShopGet() {
        return Mono.just(FallbackMessage.SHOP_LOG);
    }

    @PutMapping(FallbackUrl.SHOP)
    public Mono<String> fallbackShopPut() {
        return Mono.just(FallbackMessage.SHOP_LOG);
    }
}
