package kr.co.lotte.service;

import java.util.List;
import java.util.Map;

import kr.co.lotte.vo.BodyInfo;

public interface SurveyService {
	public List<Map<String, String>> selectBodyInfo();
	
	public List<Map<String, String>> selectBodyInfoItem();
	
	public List<Map<String, String>> selectPrdClass();
	
	public void putBodyInfo(BodyInfo[] bodyInfos);

	public void putSurveyData(String[] requestData);

	public List<Map<String, Object>> checkSurveyItem();
}
