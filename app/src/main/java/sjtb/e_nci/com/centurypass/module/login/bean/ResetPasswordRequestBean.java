package sjtb.e_nci.com.centurypass.module.login.bean;

/**
 * Created by hui on 2017/1/19 0019.
 *
 * @author hui
 *
 *  设置密码或登录  --> 上传的JavaBean
 */

public class ResetPasswordRequestBean {

    public String appOpenId;
    public String transNo;
    public MessageBodyBean messageBody;

    public static class MessageBodyBean {
        public String mobile;
        public String password;
        public String code;
    }
}
