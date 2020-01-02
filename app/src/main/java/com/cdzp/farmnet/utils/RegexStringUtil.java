package com.cdzp.farmnet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* 作者：张人文
* 日期：2019/11/5 9:33
* 邮箱：479696877@QQ.COM
* 描述：验证邮箱，手机号，座机号是否正确  是否包含中英文数字
*/
public class RegexStringUtil {

    /**
     * 手机号码的验证、座机号码验证
     *
     * @param phoneNum
     * @return
     */
    public static boolean MatchTelNum(String phoneNum) {
        String telRegex = "[1][34578]\\d{9}";//手机号码
        String zjRegex = "^(0[0-9]{2,3}\\-)?([1-9][0-9]{6,7})$";
        return phoneNum.matches(telRegex) || (phoneNum.length()<16&&phoneNum.matches(zjRegex));
    }

    public static boolean MatchPhoneNum(String phoneNum) {
        String telRegex = "[1][34578]\\d{9}";//手机号码
        return phoneNum.matches(telRegex);
    }
    /**
     * 验证邮箱格式是否正确
     */
    public boolean emailValidation(String email) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }
    /**
     * 用于验证是否只包含中英文、数字
     * @return
     */
    public static boolean filterSpecWord(String source) {
        if (source == null || source.equals("")) return true;
        String regext = "^[a-zA-Z0-9\u4E00-\u9FA5]+$";
        Pattern pattern = Pattern.compile(regext);
        Matcher matcher = pattern.matcher(source);
        if (matcher.matches()) return true;
        return false;
    }
}
