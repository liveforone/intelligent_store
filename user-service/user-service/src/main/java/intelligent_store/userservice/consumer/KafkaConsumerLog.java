package intelligent_store.userservice.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaConsumerLog {

    KAFKA_RECEIVE_LOG("Consumer receive Kafka Message -> "),
    KAFKA_NULL_LOG("!! Kafka Message is Null !!");

    private final String log;
}
