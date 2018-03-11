package kr.co.lotte.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.lotte.mapper.MobileMapper;
import kr.co.lotte.service.MobileService;
import kr.co.lotte.vo.REP_MST;

@Service
public class MobileServiceImpl implements MobileService {
	
	@Autowired
	MobileMapper mapper;

	@Override
	public List<Map<String, Object>> selectRecipeList() {
		return mapper.selectRecipeList();
	}

	@Override
	public List<Map<String, Object>> selectProductList(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID", id);
		mapper.updateInquery(param);
		return mapper.selectProductList(param);
	}
	
	@Override
	public Map<String, Object> selectMemberInfo(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID", id);
		return mapper.selectMemberInfo(param);
	}

	@Override
	public void orderRecipe(Map<String, Object> param) {
		mapper.orderRecipe(param);
		param.put("ID", param.get("repId"));
		putRecipeDataAndPush(param);
	}
	
	@Override
	public void putRecipeDataAndPush(Map<String, Object> param) {
		pushKakaoTalk(mapper.selectRecipeListById(param));
	}

	@Override
	public List<Map<String, Object>> getOrderList(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID", id);
		return mapper.selectOrderList(param);
	}

	@Override
	public List<Map<String, Object>> getSearchRecipeList(String ename) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ENAME", ename.toLowerCase());
		return mapper.selectSearchRecipeList(param);
	}

	@Override
	public void pushKakaoTalk(Map<String, Object> param) {
		try {
	        CloseableHttpClient httpclient = HttpClients.createDefault();
	        HttpPost httpPost = new HttpPost("http://210.93.181.133:9090/v1/send/kakao-friend");
	        httpPost.addHeader("cache-control", "no-cache");
	        httpPost.addHeader("Authorization", "Basic " + "Y2xhc3M6c2VjcmV0MTIhQA==");
	        //전달하고자 하는 PARAMETER를 List객체에 담는다

	        String msgBody = "[L.cookBox] 쿠킹박스 주문이 완료되었습니다. \\r\\n"
	        			   + "택배사에서 상품을 접수하면,\\r\\n"
	        			   + "직접배송조회를 하실 수 있습니다.\\r\\n"
	        			   + "(3-4시간 소요예상)\\r\\n"
	        			   + "조금만 기다려 주세요.^^\\r\\n\\r\\n"
	        			   + "쿡박스명 : " + param.get("REP_ID");
	        String paramJson = "{ \"msg_id\" : \"20170721_WONJANGCHOI\", "
	        			   + "\"send_time\" : \"\", "
	        			   + "\"dest_phone\" : \"01023637050\", "
	        			   + "\"send_phone\" : \"01023637050\", "
	        			   + "\"sender_key\" : \"d6b73318d4927aa80df1022e07fecf06c55b44bf\", "
	        			   + "\"msg_body\" : \""
	        			   + msgBody
	        			   + "\", "
	        			   + "\"ad_flag\" : \"Y\", "
	        			   + "\"button\" :[ { \"name\":\"앱 링크\", \"type\":\"AL\", \"scheme_android\":\"daumapps://open\", \"scheme_ios\":\"daumapps://open\" }, "
	        			   + "{ \"name\":\"레시피 링크\", "
	        			   + "\"type\":\"WL\", "
	        			   + "\"url_pc\":\""
	        			   + param.get("IMG_URL")
	        			   + "\", "
	        			   + "\"url_mobile\":\""
	        			   + param.get("IMG_URL")
	        			   + "\" } ], "
	        			   + "\"image\":{ "
	        			   + "\"img_url\":\""
	        			   + param.get("IMG_URL")
	        			   + "\", "
	        			   + "\"img_link\":\""
	        			   + param.get("MOV_URL")
	        			   + "\" } }";
	        
	        StringEntity params = new StringEntity(paramJson, "UTF-8");
	        params.setContentEncoding("UTF-8");
	        params.setContentType("application/json");
	        httpPost.setEntity(params);
	        
	        //UTF-8은 한글
	        httpPost.setEntity(params);
	        CloseableHttpResponse response = httpclient.execute(httpPost);
	        try {
	            System.out.println(response.getStatusLine());
	            //API서버로부터 받은 JSON 문자열 데이터
	            System.out.println(EntityUtils.toString(response.getEntity()));
	            HttpEntity entity = response.getEntity();
	            EntityUtils.consume(entity);
	            
	        } finally {
	            response.close();
	        }  
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
