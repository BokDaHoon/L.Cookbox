package com.boostcamp.mytwitter.mytwitter.myorderlist.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by DaHoon on 2017-02-23.
 */

public class OrderListDTO {
    @SerializedName("ORD_ID")
    private String ordId;
    @SerializedName("MBR_ID")
    private String mbrId;
    @SerializedName("REP_ID")
    private String repId;
    @SerializedName("REP_NM")
    private String repNm;
    @SerializedName("IMG_URL")
    private String imgUrl;
    @SerializedName("PRICE")
    private int price;
    @SerializedName("CNT")
    private int cnt;
    @SerializedName("ORD_DATE")
    private Date ordDate;
    @SerializedName("DEV_TYPE")
    private String devType;

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getMbrId() {
        return mbrId;
    }

    public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
    }

    public String getRepNm() {
        return repNm;
    }

    public void setRepNm(String repNm) {
        this.repNm = repNm;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Date getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(java.sql.Date ordDate) {
        this.ordDate = ordDate;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderListDTO{" +
                "ordId='" + ordId + '\'' +
                ", mbrId='" + mbrId + '\'' +
                ", repId='" + repId + '\'' +
                ", repNm='" + repNm + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", cnt=" + cnt +
                ", ordDate=" + ordDate +
                ", devType='" + devType + '\'' +
                '}';
    }
}
