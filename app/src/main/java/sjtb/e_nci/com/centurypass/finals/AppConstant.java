package sjtb.e_nci.com.centurypass.finals;

import infrastructure.finals.ProjectConstant;

/**
 * Created by hui on 2017/3/16 0016.
 *  项目中的常量
 * @author hui
 */

public interface AppConstant {

    /**是否需要回调*/
    String needCallBack = "need_call_back";
    /**是否是注册页面*/
    String isRegistered = "isRegistered";
    /**身份识别的token*/
    String tokenId = "token_id";

    /**地区校验接口*/
    String SIT_URL_AREA_CHECK = ProjectConstant.BASE_URL + "product/car/basic";
    /**创建会话接口*/
    String SIT_URL_CREATE_SESSION = ProjectConstant.BASE_URL + "product/car/detail";
    /**车型查询接口*/
    String SIT_URL_QUERY_MODELS = ProjectConstant.BASE_URL + "product/carModelSelect";
    /**方案查询接口*/
    String SIT_URL_QUERY_SCHEME = ProjectConstant.BASE_URL + "product/proposal";
    /**询价接口*/
    String SIT_URL_INQUIRY = ProjectConstant.BASE_URL + "product/calculate";
    /**订单确认接口*/
    String SIT_URL_CONFIRM_ORDER = ProjectConstant.BASE_URL + "product/orderConfirm";
    /**核保确认接口*/
    String SIT_URL_UNDERWRITING_CONFIRM = ProjectConstant.BASE_URL + "product/underwrite/confirm";
    /**核保接口*/
    String SIT_URL_UNDERWRITING = ProjectConstant.BASE_URL + "product/underwrite";
    /**江苏地区车管所校验码接口*/
    String SIT_URL_VERIFICATION_CODE = ProjectConstant.BASE_URL + "product/verify/image/get";
    /**阳光北京支付前获取短信验证码接口*/
    String SIT_URL_GET_VERIFICATION_CODE_PAY = ProjectConstant.BASE_URL + "product/verify/msg/get";
    /**阳光北京支付前提交短信验证码接口*/
    String SIT_URL_SUBMIT_VERIFICATION_CODE = ProjectConstant.BASE_URL + "product/verify/msg/submit";
    /**OCR识别行驶证接口*/
    String SIT_URL_OCR_DRIVING_LICENCE = ProjectConstant.BASE_URL + "product/ocr/vehicle";
    /**OCR识别驾驶证接口*/
    String SIT_URL_OCR_DRIVE_LICENCE = ProjectConstant.BASE_URL + "product/ocr/driver";
    /**最近询价车辆*/
    String SIT_URL_LAST_INQUIRY = ProjectConstant.BASE_URL + "product/nearly/order";
    /**订单查询*/
    String SIT_URL_ORDER_QUERY = ProjectConstant.BASE_URL + "query/order";
    /**订单详情查询*/
    String SIT_URL_ORDER_DETAILS_QUERY = ProjectConstant.BASE_URL + "query/order/detail";
    /**费率查询*/
    String SIT_URL_PROMOTION_RATE_QUERY = ProjectConstant.BASE_URL + "query/rate";
    /**费率推广费列表*/
    String SIT_URL_PROMOTION_FEE_QUERY = ProjectConstant.BASE_URL + "query/commission";
    /**费率推广费详情*/
    String SIT_URL_PROMOTION_FEE_DETAIL_QUERY = ProjectConstant.BASE_URL + "query/commission/detail";
    /**获取短信验证码*/
    String SIT_URL_GET_VERIFICATION_CODE = ProjectConstant.BASE_URL + "register/msg";
    /**登录、注册*/
    String SIT_URL_LOGIN_REGISTER = ProjectConstant.BASE_URL + "user/sign";
    /**重置密码*/
    String SIT_URL_RESET_PASSWORD = ProjectConstant.BASE_URL + "register/reset";
    /**注册*/
    String SIT_URL_REGISTER = ProjectConstant.BASE_URL + "user/signup";
    /**登录*/
    String SIT_URL_LOGIN = ProjectConstant.BASE_URL + "user/signin";

}
