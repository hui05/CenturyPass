package sjtb.e_nci.com.centurypass.module.login.bean;

/**
 * Created by hui on 2017/1/19 0019.
 *
 * @author hui
 *
 *  登录  --> 返回的JavaBean
 */

public class LoginResponseBean {

    public String code;
    public String errMsg;
    public MessageBodyBean messageBody;

    public static class MessageBodyBean {
        public String appOpenId;
    }
}
