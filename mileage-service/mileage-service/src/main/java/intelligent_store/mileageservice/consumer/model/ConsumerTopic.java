package intelligent_store.mileageservice.consumer.model;

public final class ConsumerTopic {
    private ConsumerTopic() {}

    public static final String CREATE_MILEAGE = "create-mileage";
    public static final String REMOVE_MILEAGE_BELONG_MEMBER = "remove-mileage-belong-member";
    public static final String MILEAGE_REQUEST_WHEN_ORDER = "mileage-request-when-order";

    //==rollback==//
    public static final String ORDER_FAIL_ROLLBACK_MILEAGE = "order-fail-rollback-mileage";
}
