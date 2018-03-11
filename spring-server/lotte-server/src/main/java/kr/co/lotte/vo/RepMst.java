package kr.co.lotte.vo;

public class RepMst {

	String REP_ID;
	String REP_NM;
	String REP_LCLS_ID;
	String REP_MCLS_ID;
	String REP_SCLS_ID;
	String IMG_URL;
	String MOV_URL;
	String REP_DESC;
	int FVR_COUNT;

	@Override
	public String toString() {
		return "RepMst [REP_ID=" + REP_ID + ", REP_NM=" + REP_NM + ", REP_LCLS_ID=" + REP_LCLS_ID + ", REP_MCLS_ID="
				+ REP_MCLS_ID + ", REP_SCLS_ID=" + REP_SCLS_ID + ", IMG_URL=" + IMG_URL + ", MOV_URL=" + MOV_URL
				+ ", REP_DESC=" + REP_DESC + ", FVR_COUNT=" + FVR_COUNT + ", INQ_COUNT=" + INQ_COUNT + "]";
	}

	int INQ_COUNT;

	public String getREP_ID() {
		return REP_ID;
	}

	public void setREP_ID(String rEP_ID) {
		REP_ID = rEP_ID;
	}

	public String getREP_NM() {
		return REP_NM;
	}

	public void setREP_NM(String rEP_NM) {
		REP_NM = rEP_NM;
	}

	public String getREP_LCLS_ID() {
		return REP_LCLS_ID;
	}

	public void setREP_LCLS_ID(String rEP_LCLS_ID) {
		REP_LCLS_ID = rEP_LCLS_ID;
	}

	public String getREP_MCLS_ID() {
		return REP_MCLS_ID;
	}

	public void setREP_MCLS_ID(String rEP_MCLS_ID) {
		REP_MCLS_ID = rEP_MCLS_ID;
	}

	public String getREP_SCLS_ID() {
		return REP_SCLS_ID;
	}

	public void setREP_SCLS_ID(String rEP_SCLS_ID) {
		REP_SCLS_ID = rEP_SCLS_ID;
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

	public int getFVR_COUNT() {
		return FVR_COUNT;
	}

	public void setFVR_COUNT(int fVR_COUNT) {
		FVR_COUNT = fVR_COUNT;
	}

	public int getINQ_COUNT() {
		return INQ_COUNT;
	}

	public void setINQ_COUNT(int iNQ_COUNT) {
		INQ_COUNT = iNQ_COUNT;
	}

}
