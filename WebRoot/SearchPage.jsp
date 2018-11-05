<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>search</title>
    
    <meta charset="utf-8"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	

  </head>
  
  <body>
    <div class="head_body" style="height:30px;">
    		<div class="head_top_left">
    			<ul>
    				
    				<li>
    					<c:choose>
    						<c:when test="${ not empty username }">尊敬的会员，欢迎回来</c:when>
    						<c:otherwise>欢迎来到云阅读</c:otherwise>
    					</c:choose>
    				</li>
    				<li><i class="iconfont icon-vertical_line"></i></li>
   					
   					<c:choose>
   						<c:when test="${ empty username }">
   							<li><a href="javascript:void(0);" id="login">登录</a></li>
		    				<li><i class="iconfont icon-vertical_line"></i></li>
		    				<li><a href="javascript:void(0);" id="register">注册</a></li>
   						</c:when>
   						<c:otherwise>
   							<li class='iconfont icon-yonghuming' id="user_icon">
		    					<a href="javascript:void(0);" id="user_login">${ username }</a>
		    					<div id="user_set">
		    						<span></span>
		    						<ul>
		    							<li>我的收藏</li>
		    							<li>我的订单</li>
		    							<li>历史纪录</li>
		    							<li id="login_out">退出登录</li>
		    						</ul>
		    					</div>
	    					</li>
   						</c:otherwise>
   					</c:choose>
    			</ul>
    		</div>
    		<div class="head_top_right">
    			<ul>
    				<li><a href="javascript:void(0)"><i class="iconfont icon-gouwuche" style="font-size:14px;"></i>购物车</a></li>
    				<li><i class="iconfont icon-vertical_line"></i></li>
    				<li><a href="javascript:void(0)">我的订单</a></li>
    				<li><i class="iconfont icon-vertical_line"></i></li>
    				<li><a href="javascript:void(0)">我的书库</a></li>
    				<li><i class="iconfont icon-vertical_line"></i></li>
    				<li><a href="javascript:void(0)">我的云阅</a></li>
    				<li><i class="iconfont icon-vertical_line"></i></li>
    				<li><a href="javascript:void(0)">我要出书</a></li>
    				<li><i class="iconfont icon-vertical_line"></i></li>
    				<li><a href="javascript:void(0)">联系我们</a></li>
    			</ul>
    		</div>
    	</div>
  </body>
</html>
