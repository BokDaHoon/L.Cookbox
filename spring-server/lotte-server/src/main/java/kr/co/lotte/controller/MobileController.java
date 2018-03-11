package kr.co.lotte.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.lotte.service.MobileService;
import kr.co.lotte.vo.REP_MST;

@Controller
public class MobileController {
	
	@Autowired
	MobileService service;
	
	final String serverApi = "AAAA39z0Mko:APA91bFAPAcYHnowoB7QTxzdQxqE6JTH5JWqQ0c34qJo_Dr4S4w3o0j5aGLuR1-p4rBi5we4FPdY1ITV3IhNn8OA1dfWnOoySYrWKlYG_6miWXXL4iSmUBKf9II2zME76PxDMlhpnuyl";
	
	@RequestMapping(value = "/lotte/push/", method = RequestMethod.GET)
	public @ResponseBody void pushNotifictation(@RequestParam String content) throws Exception {
		pushFCMNotification(content);
	}
	
	@RequestMapping(value="/recipe/list", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getRecipeList() {
		return service.selectRecipeList();
	}
	
	@RequestMapping(value="/recipe/search", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getSearchRecipeList(@RequestParam(value="ename") String ename) {
		return service.getSearchRecipeList(ename);
	}
	
	@RequestMapping(value="/product/list", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getRecipeListById(@RequestParam(value="id") String id) {
		return service.selectProductList(id);
	}
	
	@RequestMapping(value="/login/memberInfo", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getMemberInfo(@RequestParam(value="id") String id) {
		return service.selectMemberInfo(id);
	}
	
	@RequestMapping(value="/order/recipe", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> orderRecipe(@RequestBody Map<String, Object> param) {
		service.orderRecipe(param);
		return null;
	}
	
	@RequestMapping(value="/order/list", method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getOrderList(@RequestParam(value="id") String id) {
		return service.getOrderList(id);
	}
	
	private void pushFCMNotification(String content) throws Exception {

        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + serverApi);
        conn.setRequestProperty("Content-Type", "application/json");
        
        conn.setDoOutput(true);

        String input = "{\"data\" : {\"title\" : \"여기다 제목 넣기 \", \"body\" : \"" + content + "\"}, \"to\":\"/topics/ALL\"}";

        OutputStream os = conn.getOutputStream();
        
        // 서버에서 날려서 한글 깨지는 사람은 아래처럼  UTF-8로 인코딩해서 날려주자
        os.write(input.getBytes("UTF-8"));
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // print result
        System.out.println(response.toString());


    }


}
