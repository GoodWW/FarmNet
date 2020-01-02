package com.cdzp.farmnet.bean;

/**
 * 作者：张人文
 * 时间：2019/12/20 16:58
 * 邮箱：479696877@QQ.COM
 * 描述：
 */
public class MapBean extends BaseEntity {
    private double mCurrentLat ;
    private double mCurrentLon ;
    private String name ;
    private String adress ;

    public double getmCurrentLat() {
        return mCurrentLat;
    }

    public void setmCurrentLat(double mCurrentLat) {
        this.mCurrentLat = mCurrentLat;
    }

    public double getmCurrentLon() {
        return mCurrentLon;
    }

    public void setmCurrentLon(double mCurrentLon) {
        this.mCurrentLon = mCurrentLon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "MapBean{" +
                "mCurrentLat=" + mCurrentLat +
                ", mCurrentLon=" + mCurrentLon +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
