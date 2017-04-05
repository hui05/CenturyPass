package sjtb.e_nci.com.centurypass.module.login.bean;

/**
 * Created by hui on 2017/1/19 0019.
 *
 * @author hui
 *
 *  获取短信验证码  --> 上传的JavaBean
 */

public class GetVerificationCodeRequestBean {
    public String appOpenId;
    public String transNo;
    public MessageBodyBean messageBody;

    public static class MessageBodyBean {
        public String mobile;
    }
}
