package com.cdzp.farmnet.bean;

/**
 * 作者：张人文
 * 时间：2019/10/25 11:41
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class UserInfo  {


    /**
     * Id : 0
     * Account : string
     * Phone : string
     * NickName : string
     * Password : string
     * PlainCode : string
     * RoleID : 0
     * WeChatOpenID : string
     * AuthCode : string
     * HeadPath : string
     * CreateTime : string
     * Status : 0
     */

    private int Id;
    private String Account;
    private String Phone;
    private String NickName;
    private String Password;
    private String PlainCode;
    private int RoleID;
    private String WeChatOpenID;
    private String AuthCode;
    private String HeadPath;
    private String CreateTime;
    private int Status;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String Account) {
        this.Account = Account;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPlainCode() {
        return PlainCode;
    }

    public void setPlainCode(String PlainCode) {
        this.PlainCode = PlainCode;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getWeChatOpenID() {
        return WeChatOpenID;
    }

    public void setWeChatOpenID(String WeChatOpenID) {
        this.WeChatOpenID = WeChatOpenID;
    }

    public String getAuthCode() {
        return AuthCode;
    }

    public void setAuthCode(String AuthCode) {
        this.AuthCode = AuthCode;
    }

    public String getHeadPath() {
        return HeadPath;
    }

    public void setHeadPath(String HeadPath) {
        this.HeadPath = HeadPath;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "Id=" + Id +
                ", Account='" + Account + '\'' +
                ", Phone='" + Phone + '\'' +
                ", NickName='" + NickName + '\'' +
                ", Password='" + Password + '\'' +
                ", PlainCode='" + PlainCode + '\'' +
                ", RoleID=" + RoleID +
                ", WeChatOpenID='" + WeChatOpenID + '\'' +
                ", AuthCode='" + AuthCode + '\'' +
                ", HeadPath='" + HeadPath + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", Status=" + Status +
                '}';
    }
}
