<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String cp = request.getContextPath(); %> <%--ContextPath ���� --%>
<aside class="sidebar sidebar-left">
    <div class="sidebar-profile">
        <div class="avatar">
            <img class="img-circle profile-image" src="<%=cp%>/resources/bootstrap/img/profile.jpg" alt="profile">
            <i class="on border-dark animated bounceIn"></i>
        </div>
        <div class="profile-body dropdown">
            <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><h4>Mike Adams <span class="caret"></span></h4></a>
            <small class="title">Front-end Developer</small>
            <ul class="dropdown-menu animated fadeInRight" role="menu">
                <li class="profile-progress">
                    <h5>
                        <span>80%</span>
                        <small>Profile complete</small>
                    </h5>
                    <div class="progress progress-xs">
                        <div class="progress-bar progress-bar-primary" style="width: 80%">
                        </div>
                    </div>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="javascript:void(0);">
                        <span class="icon"><i class="fa fa-user"></i>
                        </span>My Account</a>
                </li>
                <li>
                    <a href="javascript:void(0);">
                        <span class="icon"><i class="fa fa-envelope"></i>
                        </span>Messages</a>
                </li>
                <li>
                    <a href="javascript:void(0);">
                        <span class="icon"><i class="fa fa-cog"></i>
                        </span>Settings</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="javascript:void(0);">
                        <span class="icon"><i class="fa fa-sign-out"></i>
                        </span>Logout</a>
                </li>
            </ul>
        </div>
    </div>
    <nav>
        <h5 class="sidebar-header">MENU</h5>
        <ul class="nav nav-pills nav-stacked" id="menu">
            <li>
                <a href="/main" title="ShoppingMall">
                    <i class="fa fa-fw fa-shopping-cart"></i> Shopping Mall
                </a>
            </li>
            <li>
                <a href="/survey" title="Survey">
                    <i class="fa fa-fw icon-note"></i> Survey
                </a>
            </li>
            <li>
                <a href="/analysis" title="Analysis">
                    <i class="fa fa-fw icon-graph"></i> Analysis
                </a>
            </li>
        </ul>
    </nav>
</aside>