package com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DaHoon on 2017-08-01.
 */

public class ProductDTO {
    @SerializedName("PRD_ID")
    private String prdId;
    @SerializedName("PRD_NM")
    private String prdNm;
    @SerializedName("PRD_IMG_URL")
    private String prdImgUrl;
    @SerializedName("ORIGIN")
    private String origin;

    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    public String getPrdNm() {
        return prdNm;
    }

    public void setPrdNm(String prdNm) {
        this.prdNm = prdNm;
    }

    public String getPrdImgUrl() {
        return prdImgUrl;
    }

    public void setPrdImgUrl(String prdImgUrl) {
        this.prdImgUrl = prdImgUrl;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "prdId='" + prdId + '\'' +
                ", prdNm='" + prdNm + '\'' +
                ", prdImgUrl='" + prdImgUrl + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
