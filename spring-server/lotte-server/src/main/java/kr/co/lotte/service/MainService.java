package kr.co.lotte.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.lotte.vo.Member;

public interface MainService {
	
	public List<Map<String, String>> selectProductList();
	
	public void pushSendCommand(Map<String, Object> param);
	
	public List<Map<String, Object>> selectRecipes(String keyword); //레시피검색
	
	public List<Map<String, Object>> selectPopRecipes(); //페이징기능 넣은 인기레시피

	public List<Map<String, Object>> selectProductsByRecipeId(String REP_ID);	//상세검색
	
	public Member selectUserByIdAndPassword(HashMap<String, String> param);		//로그인
	
	public void increaseRecipeInqCount(String REP_ID);	//조회수증가
	
	public void increaseRecipeFvrCount(String REP_ID);	//좋아요증가
	
	public List<Map<String, Object>> selectOrderByloginUser(String MBR_Id);

	public List<Map<String, Object>> selectRecipesByRecipeId(String REP_ID);

	public void insertOrderById(HashMap<String, String> param);
}
