package intelligent_store.mileageservice.producer.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaProducerLog {

    KAFKA_SEND_LOG("Kafka send Success | Topic : ");

    private final String log;
}
