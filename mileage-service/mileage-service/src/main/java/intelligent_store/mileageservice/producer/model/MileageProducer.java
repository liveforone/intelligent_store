package intelligent_store.mileageservice.producer.model;

import com.google.gson.Gson;
import intelligent_store.mileageservice.async.AsyncConstant;
import intelligent_store.mileageservice.producer.log.KafkaProducerLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import static intelligent_store.mileageservice.producer.model.ProducerTopic.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class MileageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    @Async(AsyncConstant.commandAsync)
    public void userMileageFailRollbackOrder(Long shopId) {
        String jsonOrder = gson.toJson(shopId);
        String topic = USE_MILEAGE_FAIL_ROLLBACK_ORDER;
        kafkaTemplate.send(topic, jsonOrder);
        log.info(KafkaProducerLog.KAFKA_SEND_LOG.getLog() + topic);
    }
}
