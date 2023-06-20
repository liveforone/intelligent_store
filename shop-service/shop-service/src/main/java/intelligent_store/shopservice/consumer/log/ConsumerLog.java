package intelligent_store.shopservice.consumer.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConsumerLog {

    KAFKA_RECEIVE_LOG("Consumer receive Kafka Message -> "),
    KAFKA_NULL_LOG("!! Kafka Message is Null !!"),
    CREATE_SHOP_SUCCESS("Create Shop Success || ID : "),
    REMOVE_SHOP_SUCCESS("Remove Shop Success || Username : ");

    private final String log;
}
