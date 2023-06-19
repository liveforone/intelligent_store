package intelligent_store.mileageservice.consumer.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum
KafkaConsumerLog {

    KAFKA_RECEIVE_LOG("Consumer receive Kafka Message -> "),
    KAFKA_NULL_LOG("!! Kafka Message is Null !!"),
    CREATE_MILEAGE_SUCCESS("Create Mileage Success || Username : "),
    MILEAGE_REQUEST_WHEN_ORDER_SUCCESS("Mileage Request when order success || Username : "),
    REMOVE_MILEAGE_BELONG_MEMBER_SUCCESS("Remove Mileage Belong Member Success || Username : "),
    ORDER_FAIL_ROLLBACK_MILEAGE_SUCCESS("Order Fail Rollback Mileage Success || Username : ");

    private final String log;
}
