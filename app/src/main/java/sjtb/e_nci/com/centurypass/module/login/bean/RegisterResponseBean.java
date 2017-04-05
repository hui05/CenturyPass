package sjtb.e_nci.com.centurypass.module.login.bean;

/**
 * Created by hui on 2017/1/19 0019.
 *
 * @author hui
 *
 *  注册  --> 返回的JavaBean
 */

public class RegisterResponseBean {

    public String code;
    public String errMsg;
    public MessageBodyBean messageBody;

    public static class MessageBodyBean {
        public String appOpenId;
    }
}
