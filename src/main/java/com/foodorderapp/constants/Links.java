package com.foodorderapp.constants;

public class Links {

    // --- COMMON --- //
    public static final String API = "/api";
    public static final String API_AUTH = "/api/auth";

    // --- HOME --- //
    public static final String HOME = "/home";

    // --- PRODUCTS --- //
    public static final String PRODUCTS = "/menu";
    public static final String PRODUCTS_ALL = "/menu/all";
    public static final String PRODUCT_ADD = "/menu/add";
    public static final String PRODUCT_GET_BY_ID = "/menu/{id}";
    public static final String PRODUCT_EDIT = "/menu/edit";
    public static final String PRODUCT_DELETE_BY_ID = "/menu/delete/{id}";
    public static final String PRODUCT_BY_ID = "/menu/{id}";
    public static final String PRODUCT_BY_TYPE = "/menu/{type}";

    // --- WORKER --- //
    public static final String WORKERS_LOG_IN = "/signin";
    public static final String WORKERS_REGISTER = "/signup";

    // --- USER --- //
    public static final String USER = "/user";
    public static final String USER_CURRENT = "/user/me";

    // --- IMAGE --- //
    public static final String IMAGE_ADD = "/image/upload";
    public static final String IMAGE_GET = "/image/get/{name}";
    public static final String IMAGE_DELETE = "/image/delete/{name}";
    public static final String IMAGE_EDIT = "/image/edit";

    // --- ORDER --- //
    public static final String ORDER_ALL = "/order/all";
    public static final String ORDER_ADD = "/order/add";
    public static final String ORDER_EDIT = "/order/edit";
    //public static final String ORDER_DELETE_BY_ID = "/order/delete/{id}";
    public static final String ORDER_BY_ID = "/order/{id}";
    public static final String ORDER_ACTIVE = "/order/{active}";
}
