package com.cdzp.farmnet.bean;

/**
 * 作者：张人文
 * 时间：2019/10/25 11:41
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class UserInfo extends BaseEntity {
    private String company;
    private String name;

    public UserInfo() {
    }

    public UserInfo(String company, String name) {
        this.company = company;
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
