package kr.co.lotte.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import kr.co.lotte.mapper.MainMapper;
import kr.co.lotte.service.MainService;
import kr.co.lotte.vo.Member;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	MainMapper mainMapper;
	SimpMessagingTemplate messaging;
	
	@Autowired
	public MainServiceImpl(SimpMessagingTemplate messaging) {
		this.messaging = messaging;
	}

	@Override
	public List<Map<String, String>> selectProductList() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("ID", "potter7050");
		return mainMapper.selectProductsList(param);
	}
	
	@Override
	public void pushSendCommand(Map<String, Object> param) {
		messaging.convertAndSend("/topic/greetings", param);
	}

	
	@Override
	public List<Map<String, Object>> selectRecipes(String keyword) {
		List<Map<String, Object>> selectRecipes = mainMapper.selectRecipesByKeyword(keyword);
		return selectRecipes;
	}


	@Override
	public List<Map<String, Object>> selectPopRecipes() {
		List<Map<String, Object>> PopRecipes = mainMapper.selectPopRecipes();
		return PopRecipes;
	}


	@Override
	public List<Map<String, Object>> selectProductsByRecipeId(String REP_ID) {
		List<Map<String, Object>> products = mainMapper.selectProductsByRecipeId(REP_ID);
		return products;
	}


	@Override
	public Member selectUserByIdAndPassword(HashMap<String, String> param) {
		Member user = mainMapper.selectUserByIdAndPassword(param);
		return user;
	}



	@Override
	public void increaseRecipeInqCount(String REP_ID) {
		mainMapper.increaseRecipeInqCount(REP_ID);		
	}



	@Override
	public void increaseRecipeFvrCount(String REP_ID) {
		mainMapper.increaseRecipeFvrCount(REP_ID);
	}



	@Override
	public List<Map<String, Object>> selectOrderByloginUser(String MBR_Id) {
		List<Map<String, Object>> orderList = mainMapper.selectOrderByloginUser(MBR_Id);
		return orderList;
	}



	@Override
	public List<Map<String, Object>> selectRecipesByRecipeId(String REP_ID) {
		List<Map<String, Object>> Recipes = mainMapper.selectRecipesByRecipeId(REP_ID);
		return Recipes;
	}



	@Override
	public void insertOrderById(HashMap<String, String> param) {
		mainMapper.insertOrderById(param);
	}
}
