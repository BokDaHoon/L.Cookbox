
<%@ page import="kr.co.lotte.vo.Product"%>
<%@ page import="kr.co.lotte.vo.RepMst"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String cp = request.getContextPath();
%>
<%--ContextPath 선언 --%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>ProBootstrap:Resto &mdash; Free Bootstrap Theme, Free
	Restaurant Responsive Bootstrap Website Template</title>
<meta name="description"
	content="Free Bootstrap Theme by ProBootstrap.com">
<meta name="keywords"
	content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">

<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Pinyon+Script" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/styles-merged.css">
    <link rel="stylesheet" href="/resources/css/style.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    
	<script src="<%=cp%>/resources/bootstrap/js/src/stringbuilder.js"></script>


<!--[if lt IE 9]>
      <script src="js/vendor/html5shiv.min.js"></script>
      <script src="js/vendor/respond.min.js"></script>
    <![endif]-->

 
 
 
 </head>
<body>
 	<nav class="navbar navbar-default navbar-fixed-top probootstrap-navbar">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar-collapse"
					aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/main" ></a>
			</div>

        <div id="navbar-collapse" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="#" data-nav-section="welcome">L.cook</a></li>
            <li><a href="#" data-nav-section="events">SEARCH</a></li>
            <li><a href="#" data-nav-section="contact">CONTACT</a></li>
            <c:choose>
                <c:when test="${ empty sessionScope.loginuser }">
                <li><button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">login</button></li>
                <li><button type="button" class="btn btn-default" id="join">joinus</button></li>
         	    </c:when>
               <c:otherwise>
  	           <li><a href="#" data-nav-section="mypageTest">ORDER</a></li>
  	           <li><a>${ loginuser.MBR_ID }님 환영합니다.</a></li>
               <li><button type="button" class="btn btn-default" data-toggle="modal" data-target="#logoutModal">log out</button></li>
               </c:otherwise>
              </c:choose>
              
          </ul>
        </div>
      </div>	
      </nav>
      
	<section class="probootstrap-section-bg overlay" style="background-image: url(${recipe.IMG_URL});
		data-stellar-background-ratio="0.5" data-section="specialties">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center probootstrap-animate">
					<div class="probootstrap-heading">
						<h2 class="primary-heading">Enjoy</h2>
						<h3 class="secondary-heading">${recipe.REP_NM}</h3>
					</div>
				</div>
			</div>
		</div>
	</section>
	

	<section class="probootstrap-section probootstrap-bg-white">
		<div class="container">
			<div class="row">

				<div class="col-md-5 text-center probootstrap-animate">
					<%-- <img src = '${recipes}.IMG_URL' class =""> --%>
					<c:forEach var="r" items="${recipes}">
						
							<iframe width="550" height="450" src="${r.MOV_URL}" frameborder="0" allowfullscreen></iframe>
<%-- 						<figure class="image">
								<img src="${r.IMG_URL}"
									alt="Free Bootstrap Template by ProBootstrap.com" class="">
							</figure> --%>
						
					</c:forEach>
				</div>

				<div class="col-md-6 col-md-push-1 probootstrap-animate">
					<style type="text/css">
					.tg {
						border-collapse: collapse;
						border-spacing: 0;
					}
					
					.tg td {
						font-family: Arial, sans-serif;
						font-size: 14px;
						padding: 10px 10px;
						border-style: solid;
						border-width: 1px;
						overflow: hidden;
						word-break: normal;
					}
					
					.tg th {
						font-family: Arial, sans-serif;
						font-size: 14px;
						font-weight: normal;
						padding: 10px 10px;
						border-style: solid;
						border-width: 1px;
						overflow: hidden;
						word-break: normal;
					}
					
					.tg .tg-s6z2 {
						text-align: center
					}
					
					.tg .tg-lqy6 {
						text-align: right;
						vertical-align: top
					}
					
					.tg .tg-yw4l {
						vertical-align: top
					}
					</style>

					<form name='form0' action="order.action" method="POST">
					 <div class='form-group'><input type="hidden" name="MBR_ID" value='${ sessionScope.loginuser.MBR_ID }' /></div>
					 <div class='form-group'> <input type="hidden" name="REP_ID" value='${recipe.REP_ID}' /></div>
					 <div class='form-group'> <input type="hidden" name="price" value='${recipe.PRICE}' /></div>
					 
					 
						<c:forEach var="r" items="${recipes}">
						<table class="table" style="table-layout:fixed; width: 60%">
							<colgroup>
								<col style="width: 88px">
								<col style="width: 319px">
								<col style="width: 129px">
							</colgroup>
							<tr>
								<th class="tg-yw4l">음식</th>
								<th class="tg-yw4l" colspan="2">${r.REP_NM} ${r.REP_ENM}</th>
								<input type="hidden" name="repNm" value="${r.REP_NM}" />
								<input type="hidden" name="repEnm" value="${r.REP_ENM}" />
							</tr>
							<tr>
								<td class="tg-yw4l">설명</td>
								<td class="tg-yw4l" colspan="2">${r.REP_DESC}</td>
								<input type="hidden" name="repDesc" value="${r.REP_DESC}" />
							</tr>
							<tr>
								<td class="tg-yw4l">가격</td>
								<td id="price" class="tg-yw4l" colspan="2">${r.PRICE}</td>
								<input type="hidden" name="repPrice" value="${r.PRICE}" />
							</tr>
							<tr>
								<td class="tg-yw4l">배송방법</td>
								
								<td class="tg-yw4l" colspan="2">
								<div class="form-group">
									<div id="radioset">
										<input type="radio" id="radio1" name="devType" value="DEV002" checked="checked"><label for="radio1">택배</label>
										<input type="radio" id="radio2" name="devType" value="DEV001"><label for="radio2">스마트픽서비스</label>
									</div>
								</div>
								</td>
							</tr>
							<tr>
								<td class="tg-yw4l">수량</td>
								<td class="tg-yw4l" colspan="2">
									<div class="form-group">
										<table>
											<tr>
												<td>
													<div class="form-group">
														<input id="spinner" name="CNT">
													</div>
												<td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td class="tg-yw4l">주문금액</td>
								<td id="result_price" class="tg-yw4l" colspan="2"></td>
							</tr>
							
							<tr>
								<td class="tg-s6z2" colspan="3">
									<div class="form-group">
									
									<c:choose>
                					<c:when test="${ empty sessionScope.loginuser }">
                					</c:when>
                					<c:otherwise>
                						<input type="submit" name="c_submit" id="c_submit" value="구매하기"
											class="btn btn-primary btn-lg" style="background-color: black;">
                
                					</c:otherwise>
                					</c:choose>
				
									</div>
								</td>
							</tr>
						</table>
						</c:forEach>
					</form>
				</div>
			</div>
		</div>
	</section>



	<section class="probootstrap-section probootstrap-bg-white">
		<div class="container">
			<div class="row">

				<div class="col-md-6">
					<ul class="menus">
						<c:forEach var="p" items="${products}" begin="0"
							end="${fn:length(products) / 2 - 1}">
							<li>
								<figure class="image">
									<img src="${p.PRD_IMG_URL}"
										alt="Free Bootstrap Template by ProBootstrap.com">
								</figure>
								<div class="text">
									<span class="price">원산지 : ${p.ORIGIN}</span>
									<h3>재료명 : ${p.PRD_NM}</h3>
									<p>　</p>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="col-md-6">
					<ul class="menus">
						<c:forEach var="p" items="${products}"
							begin="${fn:length(products) / 2}" end="${fn:length(products)}">
							<li>
								<figure class="image">
									<img src="${p.PRD_IMG_URL}"
										alt="Free Bootstrap Template by ProBootstrap.com">
								</figure>
								<div class="text">
									<span class="price">원산지 : ${p.ORIGIN}</span>
									<h3>재료명 : ${p.PRD_NM}</h3>
									<p>　</p>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>

			</div>
		</div>
	</section>




<section class="probootstrap-footer">
		<div class="container">
			<div class="row">
				<div class="col-md-6 probootstrap-animate">
					<div class="probootstrap-footer-widget">
						<h3>Business &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Business hours</h3>
						<div class="row">
							<div class="col-md-6">
								<p>
									&nbsp;Available Catering <br>
									&nbsp;Wellbeing Food
								</p>
							</div>
 							<div class="col-md-6">
								<p>
									Monday - Friday <br> 9:00am - 6:00pm
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6 probootstrap-animate">
					<div class="probootstrap-footer-widget">
						<h3>Locations &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  &nbsp;Contact Us</h3>
						<div class="row">
							<div class="col-md-6">
								<p>
									&nbsp;서울시 금천구 가산디지털2로 170 <br> &nbsp;(가산동 533-2)
								</p>
							</div>
<!-- 						<div class="col-md-4">
								<p>
									Friday - Sunday <br> 5:30pm - 10:00pm
								</p>
							</div> -->
 						<div class="col-md-6">
								<p>
									Email) help@cookbox.co.kr<br>
									TEL) 02-2626-4000 <br>
									FAX) 02-2626-4099 	
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="probootstrap-copyright">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<p class="copyright-text">
						&copy; 2017 <a href="#">ldcc.롯데정보통신</a><a
							href="#"></a> &amp; <a
							href="https://unsplash.com/">Unsplash</a>
					</p>
				</div>
				<div class="col-md-4">
					<ul class="probootstrap-footer-social right">
						<li><a href="#"><i class="icon-twitter"></i></a></li>
						<li><a href="#"><i class="icon-facebook"></i></a></li>
						<li><a href="#"><i class="icon-instagram"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>

	<section class="probootstrap-copyright">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<p class="copyright-text">
						&copy; 2017 <a href="https://probootstrap.com/">ProBootstrap:Resto</a>.
						All Rights Reserved. Images by <a
							href="https://graphicburger.com/">GraphicBurger</a> &amp; <a
							href="https://unsplash.com/">Unsplash</a>
					</p>
				</div>
				<div class="col-md-4">
					<ul class="probootstrap-footer-social right">
						<li><a href="#"><i class="icon-twitter"></i></a></li>
						<li><a href="#"><i class="icon-facebook"></i></a></li>
						<li><a href="#"><i class="icon-instagram"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	
	  <!-- Modal log-in start -->
  
    <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">LOG IN</h4>
        </div>
        <div class="modal-body">
   			<form class="probootstrap-form" action="login.action" method="post" id="loginform">
	              <div class="form-group">
	                <label for="memberId">Your ID</label>
	                <div class="form-field">
	                  <input type="text" name="memberId" id="memberId" class="form-control">
	                </div>
	              </div>
	              <div class="form-group">
	                <label for="password">password</label>
	                <div class="form-field">
	                  <input type="password" name="password" id="password" class="form-control">
	                </div>
	              </div>
	          
	              <div class="form-group">
	               <button type="submit" class="btn btn-primary" id="loginButton" style="background-color: black;">Login</button>	                
	              </div>
            </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- Modal log-in end -->
    
    
<!-- Modal log-out start -->
  
    <div class="modal fade" id="logoutModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
	        <h4>로그 아웃하시겠습니까?</h4>    
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" id="loginButton" style="background-color: black;"><a href="logout.action" >Logout</a></button>	                
	      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- Modal log-out end -->

    <script src="/resources/js/vendor/html5shiv.min.js"></script>
    <script src="/resources/js/vendor/respond.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-3.1.1.js"></script>
   
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="/resources/js/scripts.min.js"></script>
	<script src="/resources/js/custom.min.js"></script>
	
	<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script>	 -->
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
	<script>

 	$(document).ready(function () {
 		
 		$( "#radioset" ).buttonset();
		var spinner = $( "#spinner" ).spinner({
			min:1
		});
		spinner.on("spinstop", function() {
			var price = $("#price").text();
			var count = spinner.spinner("value");
			$("#result_price").text(price * count);
			$("#input_cnt").val(count);
		});
		
		spinner.spinner("value", 1);
		spinner.width(50);
		$("#result_price").text($("#price").text());

	});
		
</script>

</body>
</html>