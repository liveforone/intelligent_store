package intelligent_store.userservice.producer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaProducerLog {

    KAFKA_SEND_LOG("Kafka send Success | Topic : ");

    private final String log;
}
