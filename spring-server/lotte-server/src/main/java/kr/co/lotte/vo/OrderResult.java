package kr.co.lotte.vo;

import java.sql.Date;

public class OrderResult {
	private int ORD_ID;
	private String MBR_ID;
	private String REP_ID;
	private int CNT;
	private Date ORD_DATE;
	private String DEV_TYPE;
	
	private String REP_NM; //레시피명
	private String IMG_URL; //이미지URL
	private String MOV_URL;//동영상URL
	private String REP_DESC;//레시피설명
	private int price;
	
	
	
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
	public String getREP_NM() {
		return REP_NM;
	}
	public void setREP_NM(String rEP_NM) {
		REP_NM = rEP_NM;
	}
	public String getIMG_URL() {
		return IMG_URL;
	}
	public void setIMG_URL(String iMG_URL) {
		IMG_URL = iMG_URL;
	}
	public String getMOV_URL() {
		return MOV_URL;
	}
	public void setMOV_URL(String mOV_URL) {
		MOV_URL = mOV_URL;
	}
	public String getREP_DESC() {
		return REP_DESC;
	}
	public void setREP_DESC(String rEP_DESC) {
		REP_DESC = rEP_DESC;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
