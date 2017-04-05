package sjtb.e_nci.com.centurypass.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import infrastructure.util.ToastUtils;

/**
 * Created by hui on 2016/10/28 0028.
 * 校验用户录入信息的表达式
 *
 * @author hui
 */
public class CheckUtils {

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

    /**
     *  校验车牌号
     * @param brand
     * @return
     *          true: 表示车牌号正确
     *          false:表示车牌号不正确
     */
    public static boolean checkVehicleBrand(String brand) {
        if (TextUtils.isEmpty(brand)) {
            ToastUtils.showToast("请输入车牌号");
            return false;
        }
        if (brand.length() < 6) {
            ToastUtils.showToast("车牌号不能少于6位!");
            return false;
        }
        if (!brand.matches(REGEX_BRAND)) {
            ToastUtils.showToast("车牌号不符合规则!");
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
     *  校验车辆识别代号
     * @param identificationCode
     * @return
     *          true: 表示车辆识别代号正确
     *          false:表示车辆识别代号不正确
     */
    public static boolean checkVehicleIdentificationCode(String identificationCode) {
        if (TextUtils.isEmpty(identificationCode)) {
            ToastUtils.showToast("请输入车辆识别代号");
            return false;
        }
        if (identificationCode.length() != 17) {
            ToastUtils.showToast("车辆识别代号必须是17位!");
            return false;
        }
        if (! identificationCode.matches(REGEX_IDENTIFICATION_CODE)) {
            ToastUtils.showToast("车辆识别代号不符合规则!");
            return false;
        }
        return true;
    }


    /**
     *  校验车辆发动机号
     * @param engineNum
     * @return
     *          true: 表示车辆发动机号正确
     *          false:表示车辆发动机号不正确
     */
    public static boolean checkVehicleEngineNum(String engineNum) {
        if (TextUtils.isEmpty(engineNum)) {
            ToastUtils.showToast("请输入车辆发动机号");
            return false;
        }
        if (engineNum.length() > 15) {
            ToastUtils.showToast("车辆发动机号不能大于15位!");
            return false;
        }
        if (! engineNum.matches(REGEX_ENGINE_NUM)) {
            ToastUtils.showToast("车辆发动机号不符合规则!");
            return false;
        }
        return true;
    }

    /**
     *  校验车主姓名
     * @param ownerName
     * @return
     *          true: 表示车主姓名正确
     *          false:表示车主姓名不正确
     */
    public static boolean checkVehicleOwnerName(String ownerName) {
        if (TextUtils.isEmpty(ownerName)) {
            ToastUtils.showToast("请输入车主姓名");
            return false;
        }
        if (! ownerName.matches(REGEX_CHINESE)) {
            ToastUtils.showToast("车主姓名必须是中文!");
            return false;
        }
        return true;
    }

//    /**
//     *  校验车主身份证号
//     * @param ownerID
//     * @return
//     *          true: 表示车主身份证号正确
//     *          false:表示车主身份证号不正确
//     */
//    public static boolean checkVehicleOwnerID(String ownerID) {
//        if (TextUtils.isEmpty(ownerID)) {
//            ToastUtils.showToast("请输入车主身份证号");
//            return false;
//        }
//        if (!ownerID.equals(IDCardValidate.validate_effective(ownerID))) {
//            ToastUtils.showToast(IDCardValidate.validate_effective(ownerID));
//            return false;
//        }
//        return true;
//    }

    /**
     *  校验车主手机号
     * @param ownerMobile
     * @return
     *          true: 表示车主手机号正确
     *          false:表示车主手机号不正确
     */
    public static boolean checkVehicleOwnerMobile(String ownerMobile) {
        if (TextUtils.isEmpty(ownerMobile)) {
            ToastUtils.showToast("请输入车主手机号");
            return false;
        }
        if (! ownerMobile.matches(REGEX_MOBILE)) {
            ToastUtils.showToast("车主手机号不符合规则!");
            return false;
        }
        return true;
    }

//    /**
//     *  校验车辆初登日期
//     * @param loginDate
//     * @return
//     *          true: 表示车辆初登日期正确
//     *          false:表示车辆初登日期不正确
//     */
//    public static boolean checkVehicleLoginDate(String loginDate) {
//        if (TextUtils.isEmpty(loginDate)) {
//            ToastUtils.showToast("请输入车辆初登日期");
//            return false;
//        }
//        Long userData = Long.valueOf(loginDate.replaceAll("[-\\s:]", ""));
//        Long systemData = Long.valueOf(DialogUtils.getToday().replaceAll("[-\\s:]", ""));
//
//        if (userData > systemData) {
//            ToastUtils.showToast("初登日期应小于当前日期!");
//            return false;
//        }
//        return true;
//    }

//    /**
//     *  校验车辆过户日期
//     * @param tranferDate
//     * @return
//     *          true: 表示车辆过户日期正确
//     *          false:表示车辆过户日期不正确
//     */
//    public static boolean checkVehicleTranferDate(String tranferDate) {
//        if (TextUtils.isEmpty(tranferDate)) {
//            ToastUtils.showToast("请输入车辆过户日期");
//            return false;
//        }
//        Long userData = Long.valueOf(tranferDate.replaceAll("[-\\s:]", ""));
//        Long systemData = Long.valueOf(DialogUtils.getToday().replaceAll("[-\\s:]", ""));
//
//        if (userData > systemData) {
//            ToastUtils.showToast("过户日期应小于当前日期!");
//            return false;
//        }
//        return true;
//    }

    /**
     *  校验发票号
     * @param invoiceNum
     * @return
     *          true: 表示发票号正确
     *          false:表示发票号不正确
     */
    public static boolean checkVehicleInvoiceNum(String invoiceNum) {
        if (TextUtils.isEmpty(invoiceNum)) {
            ToastUtils.showToast("请输入发票号");
            return false;
        }
        if (! invoiceNum.matches(REGEX_INVOICE_NUM)) {
            ToastUtils.showToast("发票号不符合规则!");
            return false;
        }
        return true;
    }

//    /**
//     *  校验发票日期
//     * @param invoiceDate
//     * @return
//     *          true: 表示发票日期正确
//     *          false:表示发票日期不正确
//     */
//    public static boolean checkVehicleInvoiceDate(String invoiceDate) {
//        if (TextUtils.isEmpty(invoiceDate)) {
//            ToastUtils.showToast("请输入发票日期");
//            return false;
//        }
//        Long userData = Long.valueOf(invoiceDate.replaceAll("[-\\s:]", ""));
//        Long systemData = Long.valueOf(DialogUtils.getToday().replaceAll("[-\\s:]", ""));
//
//        if (userData > systemData) {
//            ToastUtils.showToast("发票日期应小于当前日期!");
//            return false;
//        }
//        return true;
//    }

    /**
     *  校验车型信息
     * @param vehicleInformation
     * @return
     *          true: 表示车型信息正确
     *          false:表示车型信息不正确
     */
    public static boolean checkVehicleInformation(String vehicleInformation) {
        if (TextUtils.isEmpty(vehicleInformation)) {
            ToastUtils.showToast("请输入车型信息");
            return false;
        }
        return true;
    }

//    /**
//     *  校验保险日期(交强险, 商业险)
//     * @param insuranceDate
//     * @return
//     *          true: 表示保险日期正确
//     *          false:表示保险日期不正确
//     */
//    public static boolean checkVehicleInsuranceDate(String insuranceDate) {
//        if (TextUtils.isEmpty(insuranceDate)) {
//            ToastUtils.showToast("日期不能为空");
//            return false;
//        }
//        Long userData = Long.valueOf(insuranceDate.replaceAll("[-\\s:]", ""));
//        Long systemData = Long.valueOf(DialogUtils.getToday().replaceAll("[-\\s:]", ""));
//
//        if (userData < systemData) {
//            ToastUtils.showToast("日期应大于当前日期");
//            return false;
//        }
//        return true;
//    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 确保保单及填写...  页面


    /**
     *  校验车主姓名
     * @param ownerName
     * @return
     *          true: 表示车主姓名正确
     *          false:表示车主姓名不正确
     */
    public static boolean checkOwnerName(String ownerName) {
        if (TextUtils.isEmpty(ownerName)) {
            ToastUtils.showToast("请输入车主姓名");
            return false;
        }
        if (! ownerName.matches(REGEX_CHINESE)) {
            ToastUtils.showToast("车主姓名必须是中文");
            return false;
        }
        return true;
    }
//
//    /**
//     *  校验车主身份证号
//     * @param ownerID
//     * @return
//     *          true: 表示车主身份证号正确
//     *          false:表示车主身份证号不正确
//     */
//    public static boolean checkOwnerID(String ownerID) {
//        if (TextUtils.isEmpty(ownerID)) {
//            ToastUtils.showToast("请输入车主身份证号");
//            return false;
//        }
//        if (!ownerID.equals(IDCardValidate.validate_effective(ownerID))) {
//            ToastUtils.showToast(IDCardValidate.validate_effective(ownerID));
//            return false;
//        }
//        return true;
//    }

    /**
     *  校验车主手机号
     * @param ownerMobile
     * @return
     *          true: 表示车主手机号正确
     *          false:表示车主手机号不正确
     */
    public static boolean checkOwnerMobile(String ownerMobile) {
        if (TextUtils.isEmpty(ownerMobile)) {
            ToastUtils.showToast("请输入车主手机号");
            return false;
        }
        if (! ownerMobile.matches(REGEX_MOBILE)) {
            ToastUtils.showToast("手机号输入有误");
            return false;
        }
        return true;
    }


    /**
     *  校验车主身份证地址
     * @param ownerAddressSimple
     * @return
     *          true: 表示车主身份证地址正确
     *          false:表示车主身份证地址不正确
     */
    public static boolean checkOwnerAddressSimple(String ownerAddressSimple) {
        if (TextUtils.isEmpty(ownerAddressSimple)) {
            ToastUtils.showToast("请输入车主身份证地址");
            return false;
        }
        return true;
    }


    /**
     *  校验车主身份证地址
     * @param ownerAddressDetail
     * @return
     *          true: 表示车主身份证地址正确
     *          false:表示车主身份证地址不正确
     */
    public static boolean checkOwnerAddressDetail(String ownerAddressDetail) {
        if (TextUtils.isEmpty(ownerAddressDetail)) {
            ToastUtils.showToast("请输入车主身份证详细地址");
            return false;
        }
        return true;
    }


    /**
     *  校验车主邮箱
     * @param ownerEmail
     * @return
     *          true: 表示车主身份证地址正确
     *          false:表示车主身份证地址不正确
     */
    public static boolean checkOwnerEmail(String ownerEmail) {
        if (TextUtils.isEmpty(ownerEmail)) {
            ToastUtils.showToast("请输入车主邮箱");
            return false;
        }
        if (! ownerEmail.matches(REGEX_EMAIL)) {
            ToastUtils.showToast("车主邮箱不符合规则");
            return false;
        }
        return true;
    }

    /**
     *  校验投保人姓名
     * @param applicantName
     * @return
     *          true: 表示投保人姓名正确
     *          false:表示投保人姓名不正确
     */
    public static boolean checkApplicantName(String applicantName) {
        if (TextUtils.isEmpty(applicantName)) {
            ToastUtils.showToast("请输入投保人姓名");
            return false;
        }
        if (! applicantName.matches(REGEX_CHINESE)) {
            ToastUtils.showToast("投保人姓名必须是中文");
            return false;
        }
        return true;
    }

//    /**
//     *  校验投保人身份证号
//     * @param applicantID
//     * @return
//     *          true: 表示投保人身份证号正确
//     *          false:表示投保人身份证号不正确
//     */
//    public static boolean checkApplicantID(String applicantID) {
//        if (TextUtils.isEmpty(applicantID)) {
//            ToastUtils.showToast("请输入投保人身份证号");
//            return false;
//        }
//        if (!applicantID.equals(IDCardValidate.validate_effective(applicantID))) {
//            ToastUtils.showToast(IDCardValidate.validate_effective(applicantID));
//            return false;
//        }
//        return true;
//    }

    /**
     *  校验投保人手机号
     * @param applicantMobile
     * @return
     *          true: 表示投保人手机号正确
     *          false:表示投保人手机号不正确
     */
    public static boolean checkApplicantMobile(String applicantMobile) {
        if (TextUtils.isEmpty(applicantMobile)) {
            ToastUtils.showToast("请输入投保人手机号");
            return false;
        }
        if (! applicantMobile.matches(REGEX_MOBILE)) {
            ToastUtils.showToast("投保人手机号不符合规则");
            return false;
        }
        return true;
    }


    /**
     *  校验投保人身份证地址
     * @param applicantAddressSimple
     * @return
     *          true: 表示车主身份证地址正确
     *          false:表示车主身份证地址不正确
     */
    public static boolean checkApplicantAddressSimple(String applicantAddressSimple) {
        if (TextUtils.isEmpty(applicantAddressSimple)) {
            ToastUtils.showToast("请输入投保人身份证地址");
            return false;
        }
        return true;
    }


    /**
     *  校验投保人身份证地址
     * @param applicantAddressDetail
     * @return
     *          true: 表示投保人身份证地址正确
     *          false:表示投保人身份证地址不正确
     */
    public static boolean checkApplicantAddressDetail(String applicantAddressDetail) {
        if (TextUtils.isEmpty(applicantAddressDetail)) {
            ToastUtils.showToast("请输入投保人身份证详细地址");
            return false;
        }
        return true;
    }


    /**
     *  校验投保人邮箱
     * @param applicantEmail
     * @return
     *          true: 表示投保人身份证地址正确
     *          false:表示投保人身份证地址不正确
     */
    public static boolean checkApplicantEmail(String applicantEmail) {
        if (TextUtils.isEmpty(applicantEmail)) {
            ToastUtils.showToast("请输入投保人邮箱");
            return false;
        }
        if (! applicantEmail.matches(REGEX_EMAIL)) {
            ToastUtils.showToast("投保人邮箱不符合规则");
            return false;
        }
        return true;
    }


    /**
     *  校验被保人姓名
     * @param insuredName
     * @return
     *          true: 表示被保人姓名正确
     *          false:表示被保人姓名不正确
     */
    public static boolean checkInsuredName(String insuredName) {
        if (TextUtils.isEmpty(insuredName)) {
            ToastUtils.showToast("请输入被保人姓名");
            return false;
        }
        if (! insuredName.matches(REGEX_CHINESE)) {
            ToastUtils.showToast("被保人姓名必须是中文");
            return false;
        }
        return true;
    }

//    /**
//     *  校验被保人身份证号
//     * @param insuredID
//     * @return
//     *          true: 表示被保人身份证号正确
//     *          false:表示被保人身份证号不正确
//     */
//    public static boolean checkInsuredID(String insuredID) {
//        if (TextUtils.isEmpty(insuredID)) {
//            ToastUtils.showToast("请输入被保人身份证号");
//            return false;
//        }
//        if (!insuredID.equals(IDCardValidate.validate_effective(insuredID))) {
//            ToastUtils.showToast(IDCardValidate.validate_effective(insuredID));
//            return false;
//        }
//        return true;
//    }

    /**
     *  校验被保人手机号
     * @param insuredMobile
     * @return
     *          true: 表示被保人手机号正确
     *          false:表示被保人手机号不正确
     */
    public static boolean checkInsuredMobile(String insuredMobile) {
        if (TextUtils.isEmpty(insuredMobile)) {
            ToastUtils.showToast("请输入被保人手机号");
            return false;
        }
        if (! insuredMobile.matches(REGEX_MOBILE)) {
            ToastUtils.showToast("被保人手机号不符合规则");
            return false;
        }
        return true;
    }


    /**
     *  校验被保人身份证地址
     * @param insuredAddressSimple
     * @return
     *          true: 表示被保人身份证地址正确
     *          false:表示被保人身份证地址不正确
     */
    public static boolean checkInsuredAddressSimple(String insuredAddressSimple) {
        if (TextUtils.isEmpty(insuredAddressSimple)) {
            ToastUtils.showToast("请输入被保人身份证地址");
            return false;
        }
        return true;
    }


    /**
     *  校验被保人身份证地址
     * @param insuredAddressDetail
     * @return
     *          true: 表示被保人身份证地址正确
     *          false:表示被保人身份证地址不正确
     */
    public static boolean checkInsuredAddressDetail(String insuredAddressDetail) {
        if (TextUtils.isEmpty(insuredAddressDetail)) {
            ToastUtils.showToast("请输入被保人身份证详细地址");
            return false;
        }
        return true;
    }


    /**
     *  校验被保人邮箱
     * @param insuredEmail
     * @return
     *          true: 表示被保人身份证地址正确
     *          false:表示被保人身份证地址不正确
     */
    public static boolean checkInsuredEmail(String insuredEmail) {
        if (TextUtils.isEmpty(insuredEmail)) {
            ToastUtils.showToast("请输入被保人邮箱");
            return false;
        }
        if (! insuredEmail.matches(REGEX_EMAIL)) {
            ToastUtils.showToast("被保人邮箱不符合规则");
            return false;
        }
        return true;
    }

    /**
     *  校验寄送信息姓名
     * @param sendName
     * @return
     *          true: 表示寄送信息姓名正确
     *          false:表示寄送信息姓名不正确
     */
    public static boolean checkSendName(String sendName) {
        if (TextUtils.isEmpty(sendName)) {
            ToastUtils.showToast("请输入寄送信息姓名");
            return false;
        }
        if (! sendName.matches(REGEX_CHINESE)) {
            ToastUtils.showToast("寄送信息姓名必须是中文");
            return false;
        }
        return true;
    }

    /**
     *  校验寄送信息手机号
     * @param sendMobile
     * @return
     *          true: 表示寄送信息手机号正确
     *          false:表示寄送信息手机号不正确
     */
    public static boolean checkSendMobile(String sendMobile) {
        if (TextUtils.isEmpty(sendMobile)) {
            ToastUtils.showToast("请输入寄送信息手机号");
            return false;
        }
        if (! sendMobile.matches(REGEX_MOBILE)) {
            ToastUtils.showToast("寄送信息手机号不符合规则");
            return false;
        }
        return true;
    }


    /**
     *  校验寄送信息身份证地址
     * @param sendAddressSimple
     * @return
     *          true: 表示寄送信息身份证地址正确
     *          false:表示被保人身份证地址不正确
     */
    public static boolean checkSendAddressSimple(String sendAddressSimple) {
        if (TextUtils.isEmpty(sendAddressSimple)) {
            ToastUtils.showToast("请输入寄送信息身份证地址");
            return false;
        }
        return true;
    }


    /**
     *  校验寄送信息身份证地址
     * @param sendAddressDetail
     * @return
     *          true: 表示寄送信息身份证地址正确
     *          false:表示寄送信息身份证地址不正确
     */
    public static boolean checkSendAddressDetail(String sendAddressDetail) {
        if (TextUtils.isEmpty(sendAddressDetail)) {
            ToastUtils.showToast("请输入寄送信息身份证详细地址");
            return false;
        }
        return true;
    }

    /**
     *  校验手机验证码
     * @param verificationCode
     * @return
     */
    public static boolean checkVerificationCode(String verificationCode) {
        if (TextUtils.isEmpty(verificationCode)) {
            ToastUtils.showToast("请输入您的验证码");
            return false;
        }
        if (! verificationCode.matches(REGEX_VERIFICATION_CODE)) {
            ToastUtils.showToast("验证码有误");
            return false;
        }
        return true;
    }

    /**
     *  校验密码
     * @param pwd
     * @return
     */
    public static boolean checkPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showToast("请输入您的密码");
            return false;
        }
        if (! pwd.matches(REGEX_PASSWORD)) {
            ToastUtils.showToast("密码只能为6-16位的数字、字母的组合");
            return false;
        }
        return true;
    }

    /**
     *  校验密码
     * @param pwd
     * @return
     */
    public static boolean checkPwdConfirm(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showToast("请再次输入您的密码");
            return false;
        }
        return true;
    }

    /**
     *  校验推广费率的起始时间
     * @param startTime
     * @param endTime
     */
    public static boolean checkPromotionFee(String startTime, String endTime) {
        if (TextUtils.isEmpty(startTime)) {
            ToastUtils.showToast("请选择起始日期");
            return false;
        }
        if (TextUtils.isEmpty(endTime)) {
            ToastUtils.showToast("请选择终止日期");
            return false;
        }
        if (date2millisecond(startTime)== 0) {
            ToastUtils.showToast("起始日期错误,请重新选择");
            return false;
        }
        if (date2millisecond(endTime)== 0) {
            ToastUtils.showToast("终止日期错误,请重新选择");
            return false;
        }
        if (date2millisecond(startTime) >= date2millisecond(endTime)) {
            ToastUtils.showToast("起始日期不应大于终止日期");
            return false;
        }
        return true;
    }

    /**
     *  将2017-01-01格式日期转换为毫秒值
     * @param date
     * @return
     */
    public static long date2millisecond(String date) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(date).getTime();//毫秒
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *  将毫秒值转换为2017-01-01格式日期
     * @param date
     * @return
     */
    public static String  millisecond2date(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(date));
    }

}
