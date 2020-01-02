package com.cdzp.farmnet.bean;

/**
 * 作者：张人文
 * 时间：2019/11/19 10:02
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class CodeBean extends BaseEntity {
    private String strCode;

    public CodeBean(String strCode) {
        this.strCode = strCode;
    }

    public String getStrCode() {
        return strCode;
    }

    public void setStrCode(String strCode) {
        this.strCode = strCode;
    }

    @Override
    public String toString() {
        return "CodeBean{" +
                "strCode='" + strCode + '\'' +
                '}';
    }
}
