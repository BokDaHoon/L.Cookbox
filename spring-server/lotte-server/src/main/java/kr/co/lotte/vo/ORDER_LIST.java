package kr.co.lotte.vo;

import java.sql.Date;

public class ORDER_LIST {
	private int ORD_ID;
	private String MBR_ID;
	private String REP_ID;
	private int CNT;
	private Date ORD_DATE;
	private String DEV_TYPE;
	
	
	public int getORD_ID() {
		return ORD_ID;
	}
	public void setORD_ID(int oRD_ID) {
		ORD_ID = oRD_ID;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getREP_ID() {
		return REP_ID;
	}
	public void setREP_ID(String rEP_ID) {
		REP_ID = rEP_ID;
	}
	public int getCNT() {
		return CNT;
	}
	public void setCNT(int cNT) {
		CNT = cNT;
	}
	
	public Date getORD_DATE() {
		return ORD_DATE;
	}
	public void setORD_DATE(Date oRD_DATE) {
		ORD_DATE = oRD_DATE;
	}
	public String getDEV_TYPE() {
		return DEV_TYPE;
	}
	public void setDEV_TYPE(String dEV_TYPE) {
		DEV_TYPE = dEV_TYPE;
	}
}
