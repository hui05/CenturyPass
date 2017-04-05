package sjtb.e_nci.com.centurypass.module.login.bean;

/**
 * Created by hui on 2017/1/19 0019.
 *
 * @author hui
 *
 *  获取短信验证码  --> 返回的JavaBean
 */

public class GetVerificationCodeResponseBean {
    public String code;
    public String errMsg;
    public MessageBodyBean messageBody;

    public static class MessageBodyBean {
        public String code;
    }
}
