<%@ page import= "kr.co.lotte.vo.REP_MST"  %>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form" %>
<% String cp = request.getContextPath(); %> <%--ContextPath 선언 --%>
<!DOCTYPE html>

<!-- 
  Theme Name: Resto
  Theme URL: https://probootstrap.com/resto-free-restaurant-responsive-bootstrap-website-template
  Author: ProBootstrap.com
  Author URL: https://probootstrap.com
  License: Released for free under the Creative Commons Attribution 3.0 license (probootstrap.com/license)
-->
<html lang="en">
  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>L.cook, Enjoy </title>
    <meta name="description" content="Free Bootstrap Theme by ProBootstrap.com">
    <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Pinyon+Script" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/styles-merged.css">
    <link rel="stylesheet" href="/resources/css/style.min.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    
	<script src="<%=cp%>/resources/bootstrap/js/src/stringbuilder.js"></script>
    
      <script src="/resources/js/vendor/html5shiv.min.js"></script>
      <script src="/resources/js/vendor/respond.min.js"></script>
      <script type="text/javascript" src="http://code.jquery.com/jquery-3.1.1.js"></script>
   
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   
 <script type="text/javascript">

 
 $(document).ready(function () {
	 
	 /* var isGoOrder = $("#isGoOrder").val();
	 console.log(isGoOrder);
	 if (isGoOrder != null || isGoOrder != "") {
		 $("#goOrder").trigger("click");	 
	 } */
	 
	   $(function() {
		  
		   $('#join').on("click",function(event){
			   alert("추후 멤버십 고객과 연동할 수 있도록 준비하겠습니다.");
		   });
		   
		   $('#search_button').on("click",function(event){
			   var keyword = $('#search_bar').val();
			   event.preventDefault();
		
			   searchRecipe(keyword);			   
		   }); 
			   
		   var searchRecipe = function(keyword) {
			   $.ajax({
	               "url" : "/main/search.action?keyword=" + keyword ,
	               method : "get",
	               dataType : 'json',
	               success : function (result){
	            	   $("#recipeBox").remove();
	            	    var text;
	            	    var temp;
	            	    var map = new Map();
	            	    var tag = new StringBuilderEx();
	            	    
	            	    console.log("진입부분");
	            	    console.log(result);
	            	    
	            	    $.each(result,function(index,result){
	            	    	
	            		  tag.append("<div class='col-md-4 col-sm-4'>");
		            	  tag.append("<div class='probootstrap-block-image'><figure><img src='" + result.IMG_URL + "'/></figure>");
						  tag.append("<div class='text'>");
			              tag.append("<h3><a href='/main1?REP_ID="+ result.REP_ID + "'>" + result.REP_NM + "</a></h3>");
 			              tag.append("<p class='rep_desc'>" + result.REP_DESC + "</p>");
			              tag.append("<p class=''><a href='/main1?REP_ID="+ result.REP_ID + "' class='probootstrap-custom-link link-sm'>Read More</a></p>");
						  tag.append("</div>");
						  tag.append("</div>");
						  tag.append("</div>");
						 	
	            	    });
	            	    
	            	    console.log("진입 후 부분");
	            	    
	            		/* console.log(tag.toString()); */
	            		
           				$("#recipeBoxChild").html(tag.toString());
           				
	               },
	               error : function(result) {
	                  alert("죄송합니다. 자료가 없습니다.");
	               } 
		   });
			  
		}  
	   
   });
	   
  });
    </script>

    
    
  </head>
  <body>
     <%-- <jsp:include page="include/header.jsp" />  --%>
    <!-- Fixed navbar -->
 
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
            <li><a id="anc_search" href="#" data-nav-section="events">SEARCH</a></li>
            <li><a href="#" data-nav-section="contact">CONTACT</a></li>
            <c:choose>
                <c:when test="${ empty sessionScope.loginuser }">
                <li><button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">LOG IN</button></li>
                <li><button type="button" class="btn btn-default" id="join">joinus</button></li>
         	    </c:when>
               <c:otherwise>
  	           <li><a href="#" data-nav-section="mypageTest" id="goOrder">ORDER</a>
  	           	<input type="hidden" id="isGoOrder" value="${goOrder }"/></li>
  	           <li><a>${ loginuser.MBR_ID }님 환영합니다.</a></li>
               <li><button type="button" class="btn btn-default" data-toggle="modal" data-target="#logoutModal">log out</button></li>
               </c:otherwise>
              </c:choose>
              
          </ul>
        </div>
      </div>
      
      
      
               
    
    </nav>
    
    
    
    
    <section class="flexslider" data-section="welcome">
      <ul class="slides">
        <li style="background-image: url(/resources/img/1.jpg)" class="overlay" data-stellar-background-ratio="0.5">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center probootstrap-animate probootstrap-heading">
                  <h1 class="primary-heading">welcome</h1>
                  <h3 class="secondary-heading">L.cookBox</h3>
                  <p class="sub-heading">Enjoy cooking</p>
                </div>
              </div>
            </div>
          </div>
        </li>
        <li style="background-image: url(/resources/img/2.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center probootstrap-animate probootstrap-heading">
                  <h1 class="primary-heading">Our service</h1>
                  <h3 class="secondary-heading">Cooking Box</h3>
                  <p class="sub-heading">with fresh food, Convenience, pleasure </a></p>
                </div>
              </div>
            </div>
          </div>
          
        </li>
        <li style="background-image: url(/resources/img/35.jpg)" class="overlay">
          <div class="container">
            <div class="row">
              <div class="col-md-8 col-md-offset-2">
                <div class="probootstrap-slider-text text-center probootstrap-animate probootstrap-heading">
                  <h1 class="primary-heading">Deliver Service</h1>
                  <h3 class="secondary-heading">With Us</h3>
                  <p class="sub-heading">we deliver joyful time as well as good recipe. </a></p>
                </div>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </section>


    <section class="probootstrap-section-bg overlay" style="background-image: url(/resources/img/sec_back.jpeg);"
      data-stellar-background-ratio="0.5" data-section="events">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center probootstrap-animate">
            <div class="probootstrap-heading">
              <h2 class="primary-heading" style="height : 110px">Upcoming</h2>
              <h3 class="secondary-heading">Our Events</h3>
            <!-- 검색바 -->  
              	
              <!--bootstrap searchbar  이거 좀따 고치기 -->
              <input type="text" id="search_bar" class="form-control"
							placeholder="searching the recipes" name="keyword"
							style="margin: 10px auto; width: 70%">
              
              <input		type="button" name="search" id="search_button" class="btn btn-lg btn-primary btn-block" a
							href="main/search.action?keyword=" style="margin: 0 auto; width: 30%; height:45px; 
							background-image: url(/resources/img/search.png); background-position: center; 
							background-repeat: no-repeat;">
              <!--bootstrap searchbar  이거 좀따 고치기 끝-->
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
    
    
	<section class="probootstrap-section"
		style="background-image: url(/resources/img/th_back.jpeg); background-repeat: round;">
      	<div class="container">
      	
      	<div class="row">
      		
      	<div id="recipeBox">
		  	<c:forEach var="popRecipe" items="${ popRecipes }" varStatus="status">	
		          <div class="col-md-4 col-sm-4 probootstrap-animate" id="recipeCard" name="card${status.index}">
		            <div class="probootstrap-block-image">
		              <figure><img src="${popRecipe.IMG_URL}" onerror="this.src='/resources/img/img_square_4.jpg'" alt="Free Bootstrap Template by ProBootstrap.com"></figure>
		              <div class="text">
		                <h3><a href="main1.action?REP_ID=${popRecipe.REP_ID}">${popRecipe.REP_NM}</a></h3>
		                <p class="rep_desc">${popRecipe.REP_DESC}</p>
		                <p class=""><a href="main1.action?REP_ID=${popRecipe.REP_ID}" class="probootstrap-custom-link link-sm">Read More</a>
		                <a href="" class="probootstrap-custom-link link-sm">like</a></p>
		              </div>
		            </div>
		          </div>
		     </c:forEach> 
		     
		 </div> <!-- recipeBox --> 
      
      	<div id="recipeBoxChild"></div>      
      
      	</div> <!-- id row -->
      </div> <!--id container  -->
      
    </section>
    
    
    
    
<!--마이페이지테스트  -->
<c:choose>
<c:when test="${sessionScope.loginuser != null}">


        <section class="probootstrap-section-bg overlay" style="background-image: url(/resources/img/order.jpeg);" data-stellar-background-ratio="0.5" data-section="mypageTest">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center probootstrap-animate">
            <div class="probootstrap-heading">
              <h2 class="primary-heading">fresh </h2>
              <h3 class="secondary-heading">Order List</h3>
            </div>
          </div>
        </div>
      </div>
      
    </section>
  
  
  <section class="probootstrap-section">
      <div class="container">
        <div class="row">
        <br><br><br><br>
          <div class="probootstrap-cell-retro">
          
		   
		          
		 <table class="table table-hover">
		    <thead>
		      <tr>
		        <th class="col-md-4">레시피</th>
		        <th>날짜</th>
		        <th>수량</th>
		        <th>가격</th>
		        <th>배송</th>
		      </tr>
		    </thead>
		    <tbody>
		     <c:forEach var="order" items="${ orderList }" varStatus="status">
		      <tr>
				<td>
				<div class="col-md-2"><div class="thumbnail"><img class="image" src="${order.IMG_URL}" alt="Free Bootstrap Template by ProBootstrap.com"></div></div>
				${order.REP_NM}
				</td>
		        <td>${order.ORD_DATE}</td>
		        <td>${order.CNT}</td>
		        <td>${order.PRICE}</td>
		        
		        <c:choose>
		        	<c:when test="${order.DEV_TYPE eq 'DEV002'}">
		        	<td>일반택배</td>
		        	</c:when>
		        	
		        	<c:when test="${order.DEV_TYPE eq 'DEV001'}">
		        	<td>스마트픽</td>
		        	</c:when>
		        
		        </c:choose>
		        
		              
		        
		      </tr>
		      </c:forEach>
		    </tbody>
		  </table>
  
  
  
            </div>
          </div>
        </div>
      
      
    </section>    
    
    
    
</c:when>
</c:choose>
 
 <!-- probootstrap-bg-white -->
<!-- 
   <section class="probootstrap-section probootstrap-bg-white">
      <div class="container">
        <div class="row">
          <div class="col-md-5 text-center probootstrap-animate">
            <div class="probootstrap-heading dark">
              <h1 class="primary-heading">Discover</h1>
              <h3 class="secondary-heading">Our Store</h3>
              <span class="seperator">* * *</span>
            </div>
            <p>Voluptatibus quaerat laboriosam fugit non Ut consequatur animi est molestiae enim a voluptate totam natus modi debitis dicta impedit voluptatum quod sapiente illo saepe explicabo quisquam perferendis labore et illum suscipit</p>
            <p><a href="#" class="probootstrap-custom-link">About Us</a></p>
          </div>
          <div class="col-md-6 col-md-push-1 probootstrap-animate">
            <p><img src="/resources/img/img_1.jpg" alt="Free Bootstrap Template by ProBootstrap.com" class="img-responsive"></p>
          </div>
        </div>
        END row
      </div>
    </section> 
 -->
 
    <!-- <section class="probootstrap-section-bg overlay" style="background-image: url(/resources/img/hero_bg_4.jpg);"  data-stellar-background-ratio="0.5"  data-section="menu">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center probootstrap-animate">
            <div class="probootstrap-heading">
              <h2 class="primary-heading">Discover</h2>
              <h3 class="secondary-heading">Our Menu</h3>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="probootstrap-section probootstrap-bg-white">
      <div class="container">
        <div class="row">
          <div class="col-md-6">
            <ul class="menus">
              <li>
                <figure class="image"><img src="/resources/img/img_square_1.jpg" alt="Free Bootstrap Template by ProBootstrap.com"></figure>
                <div class="text">
                  <span class="price">$22.99</span>
                  <h3>Fried Potatoes with Garlic</h3>
                  <p>Crab / Potatoes / Rice</p>
                </div>
              </li>
              <li>
                <figure class="image"><img src="/resources/img/img_square_2.jpg" alt="Free Bootstrap Template by ProBootstrap.com"></figure>
                <div class="text">
                  <span class="price">$22.99</span>
                  <h3>Tuna Roast Source</h3>
                  <p>Crab / Potatoes / Rice</p>
                </div>
              </li>
              <li>
                <figure class="image"><img src="/resources/img/img_square_3.jpg" alt="Free Bootstrap Template by ProBootstrap.com"></figure>
                <div class="text">
                  <span class="price">$22.99</span>
                  <h3>Roast Beef (4 sticks)</h3>
                  <p>Crab / Potatoes / Rice</p>
                </div>
              </li>
              <li>
                <figure class="image"><img src="/resources/img/img_square_4.jpg" alt="Free Bootstrap Template by ProBootstrap.com"></figure>
                <div class="text">
                  <span class="price">$22.99</span>
                  <h3>Salted Fried Chicken</h3>
                  <p>Crab / Potatoes / Rice</p>
                </div>
              </li>
            </ul>
          </div>
          <div class="col-md-6">
            <ul class="menus">
              <li>
                <figure class="image"><img src="/resources/img/img_square_5.jpg" alt="Free Bootstrap Template by ProBootstrap.com"></figure>
                <div class="text">
                  <span class="price">$22.99</span>
                  <h3>Baked Potato Pizza</h3>
                  <p>Crab / Potatoes / Rice</p>
                </div>
              </li>
              <li>
                <figure class="image"><img src="/resources/img/img_square_1.jpg" alt="Free Bootstrap Template by ProBootstrap.com"></figure>
                <div class="text">
                  <span class="price">$22.99</span>
                  <h3>Fried Potatoes with Garlic</h3>
                  <p>Crab / Potatoes / Rice</p>
                </div>
              </li>
              <li>
                <figure class="image"><img src="/resources/img/img_square_2.jpg" alt="Free Bootstrap Template by ProBootstrap.com"></figure>
                <div class="text">
                  <span class="price">$22.99</span>
                  <h3>Salted Fried Chicken</h3>
                  <p>Crab / Potatoes / Rice</p>
                </div>
              </li>
              <li>
                <figure class="image"><img src="/resources/img/img_square_3.jpg" alt="Free Bootstrap Template by ProBootstrap.com"></figure>
                <div class="text">
                  <span class="price">$22.99</span>
                  <h3>Tuna Roast Source</h3>
                  <p>Crab / Potatoes / Rice</p>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </section>
 
<%--     <section class="probootstrap-section-bg overlay" style="background-image: url(/resources/img/hero_bg_5.jpg);"  data-stellar-background-ratio="0.5" data-section="reservation">
      <div class="container">
        <div class="row">
          <div class="col-md-12 text-center probootstrap-animate">
            <div class="probootstrap-heading">
              <h2 class="primary-heading">Booking</h2>
              <h3 class="secondary-heading">Reserve A Table</h3>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="probootstrap-section probootstrap-bg-white">
      <div class="container">
        <div class="row">
          <div class="col-md-12 probootstrap-animate">
            <form method="post" class="probootstrap-form">
              <div class="row">
                <div class="col-md-4">
                  <div class="form-group">
                    <label for="people">How Many People</label>
                    <div class="form-field">
                      <i class="icon icon-chevron-down"></i>
                      <select name="people" id="people" class="form-control">
                        <option value="#">1 people</option>
                        <option value="#">2 people</option>
                        <option value="#">3 people</option>
                        <option value="#">4+ people</option>
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label for="date">Date</label>
                    <div class="form-field">
                      <i class="icon icon-calendar"></i>
                      <input type="text" id="date" class="form-control">
                    </div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label for="time">Time</label>
                    <div class="form-field">
                      <i class="icon icon-clock"></i>
                      <input type="text" id="time" class="form-control">
                    </div>
                  </div>
                </div>
              </div>
              <!-- END row -->
              <div class="row">
                <div class="col-md-4">
                  <div class="form-group">
                    <label for="name">Name</label>
                    <div class="form-field">
                      <i class="icon icon-user2"></i>
                      <input type="text" id="name" class="form-control" placeholder="Your full name">
                    </div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label for="email">Email</label>
                    <div class="form-field">
                      <i class="icon icon-mail"></i>
                      <input type="text" id="email" class="form-control" placeholder="Your email address">
                    </div>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label for="phone">Phone</label>
                    <div class="form-field">
                      <i class="icon icon-phone"></i>
                      <input type="text" id="phone" class="form-control" placeholder="Your phone">
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-md-4 col-md-offset-4">
                  <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-lg btn-primary btn-block">
                </div>
              </div>
              
            </form>
          </div>
        </div>
      </div>
    </section>
     --%>
    
    
    
    
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
    
    
    
    <section class="probootstrap-section probootstrap-bg-white" data-section="contact">
      <div class="container">
        <div class="row">
          <div class="col-md-5 text-center probootstrap-animate">
            <div class="probootstrap-heading dark">
              <h1 class="primary-heading">Contact</h1>
              <h3 class="secondary-heading">Let's Chat</h3>
            </div>
            <p>Voluptatibus quaerat laboriosam fugit non Ut consequatur animi est molestiae enim a voluptate totam natus modi debitis dicta impedit voluptatum quod sapiente illo saepe explicabo quisquam perferendis labore et illum suscipit</p>
          </div>
          <div class="col-md-6 col-md-push-1 probootstrap-animate">
            <form method="post" class="probootstrap-form">
              <div class="form-group">
                <label for="c_name">Your Name</label>
                <div class="form-field">
                  <input type="text" id="c_name" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label for="c_email">Your Email</label>
                <div class="form-field">
                  <input type="text" id="c_email" class="form-control">
                </div>
              </div>
              <div class="form-group">
                <label for="c_message">Your Message</label>
                <div class="form-field">
                  <textarea name="c_message" id="c_message" cols="30" rows="10" class="form-control"></textarea>
                </div>
              </div>
              <div class="form-group">
                <input type="submit" name="c_submit" id="c_submit" value="Send Message" class="btn btn-primary btn-lg" style="background-color: black;">
              </div>
            </form>
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


    <script src="/resources/js/scripts.min.js"></script>
    <script src="/resources/js/custom.min.js"></script>
    <script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script> 
    <script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script> 
    <script src="/resources/bootstrap/bootstrap/js/src/socket.js"></script> 

  </body>
</html>
