package kr.co.lotte.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MobileMapper {
	List<Map<String, Object>> selectRecipeList();
	
	List<Map<String, Object>> selectSearchRecipeList(Map<String, Object> param);

	List<Map<String, Object>> selectProductList(Map<String, Object> param);

	Map<String, Object> selectMemberInfo(Map<String, Object> param);

	void orderRecipe(Map<String, Object> param);

	void updateInquery(Map<String, Object> param);

	List<Map<String, Object>> selectOrderList(Map<String, Object> param);
	
	Map<String, Object> selectRecipeListById(Map<String, Object> param);

}
