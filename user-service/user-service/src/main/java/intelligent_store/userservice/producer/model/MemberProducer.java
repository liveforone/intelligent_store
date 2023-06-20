package intelligent_store.userservice.producer.model;

import com.google.gson.Gson;
import intelligent_store.userservice.async.AsyncConstant;
import intelligent_store.userservice.dto.shop.ShopRequest;
import intelligent_store.userservice.dto.signupAndLogin.SellerSignupRequest;
import intelligent_store.userservice.producer.log.ProducerLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    @Async(AsyncConstant.commandAsync)
    public void createMileage(String username) {
        String jsonOrder = gson.toJson(username);
        String topic = ProducerTopic.CREATE_MILEAGE;
        kafkaTemplate.send(topic, jsonOrder);
        log.info(ProducerLog.KAFKA_SEND_LOG.getLog() + topic);
    }

    @Async(AsyncConstant.commandAsync)
    public void removeMileage(String username) {
        String jsonOrder = gson.toJson(username);
        String topic = ProducerTopic.REMOVE_MILEAGE_BELONG_MEMBER;
        kafkaTemplate.send(topic, jsonOrder);
        log.info(ProducerLog.KAFKA_SEND_LOG.getLog() + topic);
    }

    @Async(AsyncConstant.commandAsync)
    public void createShop(SellerSignupRequest requestDto, String username) {
        ShopRequest request = ShopRequest.builder()
                .shopName(requestDto.getShopName())
                .tel(requestDto.getTel())
                .username(username)
                .build();
        String jsonOrder = gson.toJson(request);
        String topic = ProducerTopic.CREATE_SHOP;
        kafkaTemplate.send(topic, jsonOrder);
        log.info(ProducerLog.KAFKA_SEND_LOG.getLog() + topic);
    }

    @Async(AsyncConstant.commandAsync)
    public void removeShop(String username) {
        String jsonOrder = gson.toJson(username);
        String topic = ProducerTopic.REMOVE_SHOP_BELONG_MEMBER;
        kafkaTemplate.send(topic, jsonOrder);
        log.info(ProducerLog.KAFKA_SEND_LOG.getLog() + topic);
    }
}
