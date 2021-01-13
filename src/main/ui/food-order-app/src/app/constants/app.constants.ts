export class AppConstants {

    // --- COMMON --- //
    private static BASE_URL = "http://localhost:8080/";
    private static OAUTH2_URL = AppConstants.BASE_URL + "oauth2/authorization/";
    private static REDIRECT_URL = "?redirect_uri=http://localhost:8081/login";

    public static BASE_API_URL = AppConstants.BASE_URL + "api/";
    public static BASE_AUTH_API = AppConstants.BASE_API_URL + "auth/";
    public static GOOGLE_AUTH_URL = AppConstants.OAUTH2_URL + "google" + AppConstants.REDIRECT_URL;

    public static HOME_URL: string = 'home';
    public static ALL_URL: string = 'all';
    public static ADD_URL: string = 'add';
    public static EDIT_BY_ID: string = 'edit/:id';


    // --- PRODUCT --- //
    public static PRODUCT_URL: string = 'menu';
    public static PRODUCT_ALL_URL: string = 'menu/all';
    public static PRODUCT_BY_ID: string = "menu/{id}";
    public static PRODUCT_ADD_URL: string = 'menu/add';
    public static PRODUCT_EDIT: string = "menu/edit/";
    public static PRODUCT_DELETE_BY_ID: string = 'menu/delete/{id}';
    public static PRODUCT_BY_TYPE: string = 'menu/{type}';

    public static PRODUCT_IMAGE_ADD: string = 'image/upload';
    public static PRODUCT_IMAGE_GET_BY_NAME: string = 'image/get/';
    public static PRODUCT_IMAGE_EDIT: string = 'image/edit';
    public static PRODUCT_IMAGE_DELETE: string = "image/delete/";

    public static PRODUCT_IMAGE_DATA_FORMAT = 'data:image/jpeg;base64,';


    // --- WORKER --- //
    public static WORKER_URL: string = 'worker';
    public static WORKER_LOG_IN_URL: string = 'worker/login';


    // --- USER --- //
    public static USER_URL: string = 'user';
    public static USER_ME_URL: string = 'user/me';
    public static USER_LOG_IN_URL: string = 'user/login';


    // --- LOGIN --- //
    public static LOG_IN: string = 'login';
    public static SIGN_IN: string = 'signin';
    public static SINGN_UP: string = 'signup';


    // --- CONTACTS --- //
    public static CONTACTS: string = 'contacts';


    // --- ORDER --- //
    public static ORDER_URL: string = 'order';
    public static ORDER_ALL_URL: string = 'order/all';
    public static ORDER_ADD_URL: string = 'order/add';
    public static ORDER_EDIT: string = 'order/edit';


}