<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String cp = request.getContextPath(); %> <%--ContextPath 선언 --%>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ProBootstrap:Resto &mdash; Free Bootstrap Theme, Free Restaurant Responsive Bootstrap Website Template</title>
    <meta name="description" content="Free Bootstrap Theme by ProBootstrap.com">
    <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Pinyon+Script" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/styles-merged.css">
    <link rel="stylesheet" href="/resources/css/style.min.css">

    <!--[if lt IE 9]>
      <script src="js/vendor/html5shiv.min.js"></script>
      <script src="js/vendor/respond.min.js"></script>
    <![endif]-->
      <script type="text/javascript"
		src="http://code.jquery.com/jquery-3.1.1.js"></script>
   
   
    <!--  <jsp:include page="/WEB-INF/views/include/header.jsp" /> -->
    <!-- Fixed navbar -->
    
    <nav class="navbar navbar-default navbar-fixed-top probootstrap-navbar">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="index.html" title="ProBootstrap:FineOak">FineOak</a>
        </div>

        <div id="navbar-collapse" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="#" data-nav-section="welcome">Welcome</a></li>
            <li><a href="#" data-nav-section="events">Events</a></li>
            <li><a href="#" data-nav-section="specialties">Specialties</a></li>
            <li><a href="#" data-nav-section="menu">Menu</a></li>
            <li><a href="#" data-nav-section="reservation">Reservation</a></li>
            <li><a href="#" data-nav-section="contact">Contact</a></li>
          </ul>
        </div>
      </div>
    </nav>
