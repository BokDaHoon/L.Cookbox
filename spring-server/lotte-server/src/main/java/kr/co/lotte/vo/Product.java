package kr.co.lotte.vo;

public class Product {

	String PRO_ID;
	String PRO_NM;
	String PRO_IMG_URL;
	int PRICE;
	String PRD_LCLS_ID;
	String PRD_MCLS_ID;
	String PRD_SCLS_ID;

	public String getPRO_ID() {
		return PRO_ID;
	}

	public void setPRO_ID(String pRO_ID) {
		PRO_ID = pRO_ID;
	}

	public String getPRO_NM() {
		return PRO_NM;
	}

	public void setPRO_NM(String pRO_NM) {
		PRO_NM = pRO_NM;
	}

	public String getPRO_IMG_URL() {
		return PRO_IMG_URL;
	}

	public void setPRO_IMG_URL(String pRO_IMG_URL) {
		PRO_IMG_URL = pRO_IMG_URL;
	}

	public int getPRICE() {
		return PRICE;
	}

	public void setPRICE(int pRICE) {
		PRICE = pRICE;
	}

	public String getPRD_LCLS_ID() {
		return PRD_LCLS_ID;
	}

	public void setPRD_LCLS_ID(String pRD_LCLS_ID) {
		PRD_LCLS_ID = pRD_LCLS_ID;
	}

	public String getPRD_MCLS_ID() {
		return PRD_MCLS_ID;
	}

	public void setPRD_MCLS_ID(String pRD_MCLS_ID) {
		PRD_MCLS_ID = pRD_MCLS_ID;
	}

	public String getPRD_SCLS_ID() {
		return PRD_SCLS_ID;
	}

	public void setPRD_SCLS_ID(String pRD_SCLS_ID) {
		PRD_SCLS_ID = pRD_SCLS_ID;
	}

	@Override
	public String toString() {
		return "Product [PRO_ID=" + PRO_ID + ", PRO_NM=" + PRO_NM + ", PRO_IMG_URL=" + PRO_IMG_URL + ", PRICE=" + PRICE
				+ ", PRD_LCLS_ID=" + PRD_LCLS_ID + ", PRD_MCLS_ID=" + PRD_MCLS_ID + ", PRD_SCLS_ID=" + PRD_SCLS_ID
				+ "]";
	}

}
