package intelligent_store.mileageservice.consumer.model;

public final class ConsumerTopic {
    private ConsumerTopic() {}

    public static final String CREATE_MILEAGE = "create-mileage";
    public static final String ACCUMULATE_MILEAGE = "accumulate-mileage";
    public static final String USE_MILEAGE = "use-mileage";
    public static final String REMOVE_MILEAGE_BELONG_MEMBER = "remove-mileage-belong-member";

    //==rollback==//
    public static final String ORDER_FAIL_ROLLBACK_MILEAGE = "order-fail-rollback-mileage";
}
