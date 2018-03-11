package kr.co.lotte.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.lotte.mapper.AnalysisMapper;
import kr.co.lotte.service.AnalysisService;

@Service
public class AnalysisServiceImpl implements AnalysisService {
	
	@Autowired
	AnalysisMapper mapper;

	@Override
	public List<Map<String, Object>> selectOrderCountData() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("ID", "potter7050");
		return mapper.selectOrderCountData(param);
	}

}
