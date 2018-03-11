package kr.co.lotte.vo;

public class REP_MST {
	private String REP_ID; //레시피ID
	
	//FK 할 것들. 
	private String PRD_LCLS_ID; //대분류 ID
	private String PRD_MCLS_ID;	//중분류 ID
	private String PRD_SCLS_ID; //소분류 ID
	// 아직 안정함
	
	private String REP_NM; //레시피명
	private String IMG_URL; //이미지URL
	private String MOV_URL;//동영상URL
	private String REP_DESC;//레시피설명
	private int FVR_COUNT; //좋아요수
	private int INQ_COUNT; //조회수
	
	private int price;
	
	
	public String getREP_ID() {
		return REP_ID;
	}
	public void setREP_ID(String rEP_ID) {
		REP_ID = rEP_ID;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	
	
}
