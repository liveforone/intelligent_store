package intelligent_store.shopservice.producer.model;

import com.google.gson.Gson;
import intelligent_store.shopservice.async.AsyncConstant;
import intelligent_store.shopservice.producer.log.ProducerLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static intelligent_store.shopservice.producer.model.ProducerTopic.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ShopProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    @Async(AsyncConstant.commandAsync)
    public void removeRecommend(Long shopId) {
        String jsonOrder = gson.toJson(shopId);
        String topic = REMOVE_RECOMMEND_BELONG_SHOP;
        kafkaTemplate.send(topic, jsonOrder);
        log.info(ProducerLog.KAFKA_SEND_LOG.getLog() + topic);
    }

    @Async(AsyncConstant.commandAsync)
    public void removeItem(Long shopId) {
        String jsonOrder = gson.toJson(shopId);
        String topic = REMOVE_ITEM_BELONG_SHOP;
        kafkaTemplate.send(topic, jsonOrder);
        log.info(ProducerLog.KAFKA_SEND_LOG.getLog() + topic);
    }
}
