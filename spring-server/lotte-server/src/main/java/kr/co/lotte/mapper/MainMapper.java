package kr.co.lotte.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import kr.co.lotte.vo.Member;

public interface MainMapper {
	public List<Map<String, String>> selectProductsList(Map<String, String> param);
	
	public List<Map<String, Object>> selectRecipesByKeyword(String keyword);	//레시피 검색
	
	public List<Map<String, Object>> selectPopRecipes();	//인기 레시피 조회

	public List<Map<String, Object>> selectProductsByRecipeId(String REP_ID);	//레시피 아이디별 식자재 가져오기

	public Member selectUserByIdAndPassword(HashMap<String, String> param); //Map String과 Object 둘중에 뭐가 더 나을까 ..
	
	public void increaseRecipeInqCount(String REP_ID);//조회수증가
	
	public void increaseRecipeFvrCount(String REP_ID);//좋아요증가
	
	public List<Map<String, Object>> selectOrderByloginUser(String MBR_Id);

	public List<Map<String, Object>> selectRecipesByRecipeId(String REP_ID);

	public void insertOrderById(HashMap<String, String> param);
}
