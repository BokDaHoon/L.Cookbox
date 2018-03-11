package kr.co.lotte.service;

import java.util.List;
import java.util.Map;

import kr.co.lotte.vo.REP_MST;

public interface MobileService {

	List<Map<String, Object>> selectRecipeList();

	List<Map<String, Object>> selectProductList(String id);

	Map<String, Object> selectMemberInfo(String id);

	void orderRecipe(Map<String, Object> param);

	List<Map<String, Object>> getOrderList(String id);

	List<Map<String, Object>> getSearchRecipeList(String ename);
	
	void putRecipeDataAndPush(Map<String, Object> param);

	void pushKakaoTalk(Map<String, Object> param);
	
}
