package intelligent_store.userservice.producer.model;

public final class ProducerTopic {

    private ProducerTopic() {}

    public static final String CREATE_MILEAGE = "create-mileage";
    public static final String REMOVE_MILEAGE_BELONG_MEMBER = "remove-mileage-belong-member";
    public static final String CREATE_SHOP = "create-shop";
    public static final String REMOVE_SHOP_BELONG_MEMBER = "remove-shop-belong-member";
}