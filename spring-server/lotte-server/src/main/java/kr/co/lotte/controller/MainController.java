package kr.co.lotte.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.lotte.service.MainService;
import kr.co.lotte.service.MobileService;
import kr.co.lotte.vo.Member;

@Controller
public class MainController {
	
	@Autowired
	MainService service;
	@Autowired
	MobileService mobileService;
	
	/*메인화면(인기레시피조회기능/ 구매목록) By 미란 허	 * */
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest req, HttpSession session) {
		ModelAndView mv = new ModelAndView();
	
		//페이저 끝
	    List<Map<String, Object>> popRecipes = service.selectPopRecipes();
		
	    //마이페이지에 넣을 구매목록
	    Member member = (Member) session.getAttribute("loginuser");
	    if (member != null) {
	    	String MBR_ID = member.getMBR_ID();
	    	List<Map<String, Object>> orderList = service.selectOrderByloginUser(MBR_ID);
	    	mv.addObject("orderList", orderList);
	    }
	    	
	    //MODEL에 담기
		mv.setViewName("main");
		mv.addObject("popRecipes",popRecipes);
		return mv;
	}

	
	/* 레시피 검색 By 미란 허	 **/
	@RequestMapping(value="/main/search.action",method=RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody List<Map<String, Object>> searchResult(@RequestParam("keyword") String keyword) {
		List<Map<String, Object>> result = service.selectRecipes(keyword);
		return result;
//		Json이 필요하다면
//		Gson gson = new Gson();
//	    gson.toJson(result); 
	}
	
	
	
	//	bigTiger 해당 레시피의 식품이름 찾기
	@RequestMapping(value="/main1", method=RequestMethod.GET)
	public ModelAndView selectProductByRecipeId(@RequestParam("REP_ID") String REP_ID) {
//		System.out.println("start");
		List<Map<String, Object>> products = service.selectProductsByRecipeId(REP_ID);
//		System.out.println(products);
		
		
		List<Map<String, Object>> recipes = service.selectRecipesByRecipeId(REP_ID);
		Map<String, Object> recipe = recipes.get(0);
//		System.out.println("start2");
//		System.out.println(recipes);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("products", products);
		mv.addObject("recipes", recipes);
		mv.addObject("recipe", recipe);
		mv.setViewName("productList");
		//mv.addObject("productList" , returnParam);
		return mv;
	}
	

	/* 로그인기능 By 미란 허 **/
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView selectUserByIdAndPassword(HttpServletRequest req, String memberId, String password, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("MBR_ID", memberId);
		params.put("password", password);
		
//		String url = req.getRequestURI();
		
		Member member = service.selectUserByIdAndPassword(params); 
		session.setAttribute("loginuser", member);
		mv.addObject("member", member);
		mv.addObject(session);
		mv.setViewName("redirect:/main");
		
		//리다이렉트 할 수 있게 리턴 URL 넣기 //get에 Return URL 받기 
 		return mv;
	}
	
	
	/* 로그아웃기능 By 미란 허 **/
	@RequestMapping(value = "/logout.action", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {		
		ModelAndView mv = new ModelAndView();	
		session.removeAttribute("loginuser");//로그아웃
		mv.addObject(session);
		mv.setViewName("redirect:/main");
		return mv;
	}
	
	/* 구매하기 **/
	@RequestMapping(value = "/order.action", method = RequestMethod.POST)
	public ModelAndView order(HttpSession session,String REP_ID, String CNT, String devType) {		
		ModelAndView mv = new ModelAndView();	
		Member member = (Member) session.getAttribute("loginuser");
		String MBR_ID = member.getMBR_ID();
		
		HashMap<String, String> param = new HashMap<>();
		HashMap<String, Object> param2 = new HashMap<>();
		
		param.put("MBR_ID", MBR_ID);
		param.put("REP_ID", REP_ID);
		param.put("CNT", CNT);
		param.put("devType", devType);
		
		
		
		service.insertOrderById(param);
		
		param2.put("ID", REP_ID);
		mobileService.putRecipeDataAndPush(param2);
		mv.addObject("goOrder", "goOrder");
		mv.setViewName("redirect:/main");
		return mv;
	}
	
    @SendTo("/topic/greetings")
    public @ResponseBody String greeting() throws Exception {
        Thread.sleep(1000); // simulated delay
        return "Hello";
    }
    
    @RequestMapping(value="/main/hello")
    public @ResponseBody void greeting2(@RequestBody Map<String, Object> param) throws Exception {
        service.pushSendCommand(param);
    }
}
