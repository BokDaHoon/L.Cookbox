package com.boostcamp.mytwitter.mytwitter.orderprd.adapter.contract;

import java.sql.Date;

/**
 * Created by DaHoon on 2017-08-02.
 */

public class OrderDTO {
    private String mbrId;
    private String repId;
    private int cnt;
    private String devType;

    public String getMbrId() {
        return mbrId;
    }

    public OrderDTO setMbrId(String mbrId) {
        this.mbrId = mbrId;
        return this;
    }

    public String getRepId() {
        return repId;
    }

    public OrderDTO setRepId(String repId) {
        this.repId = repId;
        return this;
    }

    public int getCnt() {
        return cnt;
    }

    public OrderDTO setCnt(int cnt) {
        this.cnt = cnt;
        return this;
    }

    public String getDevType() {
        return devType;
    }

    public OrderDTO setDevType(String devType) {
        this.devType = devType;
        return this;
    }
}
