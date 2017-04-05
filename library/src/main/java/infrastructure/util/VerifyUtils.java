package infrastructure.util;

import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by hui on 2017/3/20 0020.
 *
 * @author hui
 */

public class VerifyUtils {

    /** 正则表达式：验证用户名 */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /** 正则表达式：验证密码 */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /** 正则表达式：验证校验车牌号*/
    public static final String REGEX_BRAND ="^[A-Za-z]{1}[A-Za-z0-9]{5}$";

    /** 正则表达式：验证车辆识别代号*/
    public static final String REGEX_IDENTIFICATION_CODE ="^[A-Za-z0-9]{17}$";

    /** 正则表达式：验证车辆发动机号*/
    public static final String REGEX_ENGINE_NUM ="^[A-Za-z0-9]+$";

    /** 正则表达式：验证发票号*/
    public static final String REGEX_INVOICE_NUM ="^[0-9]{15}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /** 正则表达式：验证汉字 */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5]+$";

    /** 正则表达式：验证汉字 */
    public static final String REGEX_PROVINCE_SHORT_NAME = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]$";

    /** 正则表达式：验证手机号 */
    public static final String REGEX_MOBILE = "^0?(13|14|15|17|18|19)[0-9]{9}$";

    /** 正则表达式：手机验证码 */
    public static final String REGEX_VERIFICATION_CODE = "^[0-9]{4}$";

    /** 正则表达式：验证身份证号 */
    public static final String REGEX_ID_CARD = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(" +
            "([0|1|2]\\d)|3[0-1])\\d{3}[\\d|x|X]$";


    /**
     *  校验手机号
     * @param phoneNo
     * @return
     *          true: 表示手机号正确
     *          false:表示手机号不正确
     */
    public static boolean checkPhoneNo(String phoneNo) {
        if (TextUtils.isEmpty(phoneNo)) {
            ToastUtils.showToast("请输入手机号");
            return false;
        }
        if (! phoneNo.matches(REGEX_MOBILE)) {
            ToastUtils.showToast("手机号输入有误");
            return false;
        }
        return true;
    }


    /**
     *  校验车辆行驶的城市
     * @param runCity
     * @return
     *          true: 表示车辆行驶的城市正确
     *          false:表示车辆行驶的城市不正确
     */
    public static boolean checkVehicleRunCity(String runCity) {
        if (TextUtils.isEmpty(runCity)) {
            ToastUtils.showToast("请选择车辆行驶城市");
            return false;
        }
        return true;
    }



    /**
     *  校验完整的车牌号
     * @param brand
     * @return
     *          true: 表示车牌号正确
     *          false:表示车牌号不正确
     */
    public static boolean checkVehicleWholeBrand(String brand) {
        if (TextUtils.isEmpty(brand)) {
            ToastUtils.showToast("请输入车牌号");
            return false;
        }
        if (!String.valueOf(brand.charAt(0)).matches(REGEX_PROVINCE_SHORT_NAME)) {
            ToastUtils.showToast("车牌的第一个字为省份的简称");
            return false;
        }

        if (!brand.substring(1).matches(REGEX_BRAND)) {
            ToastUtils.showToast("车牌号不符合规则");
            return false;
        }
        return true;
    }

}
