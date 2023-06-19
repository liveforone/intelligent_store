package intelligent_store.mileageservice.producer.model;

import com.google.gson.Gson;
import intelligent_store.mileageservice.async.AsyncConstant;
import intelligent_store.mileageservice.dto.MileageFailRollbackOrderRequest;
import intelligent_store.mileageservice.producer.log.ProducerLog;
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
    public void mileageFailRollbackOrder(MileageFailRollbackOrderRequest requestDto) {
        String jsonOrder = gson.toJson(requestDto);
        String topic = MILEAGE_FAIL_ROLLBACK_ORDER;
        kafkaTemplate.send(topic, jsonOrder);
        log.info(ProducerLog.KAFKA_SEND_LOG.getLog() + topic);
    }
}
