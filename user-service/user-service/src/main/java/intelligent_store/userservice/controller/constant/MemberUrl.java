package intelligent_store.userservice.controller.constant;

public final class MemberUrl {

    private MemberUrl() {}

    //==GET==//
    public static final String MY_INFO = "/my-info";
    public static final String MY_BANKBOOK_NUM = "/provide/my-bankbookNum/{username}";
    public static final String MY_ADDRESS = "/provide/my-address/{username}";
    public static final String PROHIBITION = "/prohibition";
    public static final String ACTUATOR = "/actuator/**";

    //==POST==//
    public static final String SIGNUP_MEMBER = "/signup/member";
    public static final String SIGNUP_SELLER = "/signup/seller";
    public static final String LOGIN = "/login";

    //==PUT==//
    public static final String CHANGE_EMAIL = "/change/email";
    public static final String CHANGE_PASSWORD = "/change/password";
    public static final String CHANGE_ADDRESS = "/change/address";

    //==DELETE==//
    public static final String WITHDRAW = "/withdraw";
}
