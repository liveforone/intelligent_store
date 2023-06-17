package intelligent_store.userservice.producer.model;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

//    @Async(AsyncConstant.commandAsync)
//    public void removeRecommend(Long shopId) {
//        String jsonOrder = gson.toJson(shopId);
//        String topic = REMOVE_RECOMMEND;
//        kafkaTemplate.send(topic, jsonOrder);
//        log.info(KafkaLog.KAFKA_SEND_LOG.getValue() + topic);
//    }
}
