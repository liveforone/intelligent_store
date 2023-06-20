package intelligent_store.shopservice.controller.constant;

public final class ShopUrl {

    private ShopUrl() {}

    //==GET==//
    public static final String SHOP_HOME = "/shop/home";
    public static final String SHOP_SEARCH = "/shop/search";
    public static final String SHOP_DETAIL = "/shop/{id}";
    public static final String SHOP_INFO = "/shop/info";

    //==PUT==//
    public static final String CHANGE_SHOP_NAME = "/shop/change/shop-name/{id}";
    public static final String CHANGE_TEL = "/shop/change/tel/{id}";

}
