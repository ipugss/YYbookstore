<%@page import="com.tedu.been.HotBook"%>
<%@ page import="com.tedu.been.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>云阅</title>
	<link rel="stylesheet" type="text/css" href="font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">	
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.fly.js"></script>
	<script type="text/javascript" src="js/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
  </head>
  
  <body>
  	<%
  		
  		String username=(String)session.getAttribute("username");
  	
  	 %>
  
    <div class="head">
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
    	<div class="head_body">
    		<!-- left -->
    		<div class="head_left">
	    		<i class="iconfont icon-book" id="logo">云阅</i>
    		</div>
    		
    		<!-- center -->
    		<div class="head_center">
    			<form action="/search" method="get" id="search_form">
	    			<input id="search" type="text" name="key" placeholder="请输入要搜索的内容..."/>
	    			<input id="do_search" type="submit" value="search"/>
	    			<i class="iconfont icon-sousuo"></i>
    			</form>
    		</div>
    		
    		<!-- right -->
    		<div class="head_right">
				<i class="iconfont icon-erweima1" id="ercode"></i>
				<i>扫码下载客户端</i> 			
    		</div>
    	</div>
    </div>
    
    <!-- head end -->
    <!-- webbody start -->
    <div class="webbody">
    	<div class="webbody_left">
    		<ul>
    			<li class="webbody_left_li">
	    			<div>
	    				<span style="float:left;color:#999;margin-left:24px;line-height:60px;font-size:13px;">文学/历史</span>
	    				<i class="iconfont icon-fanhui2" style="float:right;color:#999;line-height:60px;margin-right:18px;font-size:10px"></i>
	    			</div>
	    			<div class="webbody_left_li_hidden">
	    				
	    			</div>
	    		</li>
    			<li class="webbody_left_li">
    				<div>
	    				<span style="float:left;color:#999;margin-left:24px;line-height:60px;font-size:13px;">玄幻/科幻</span>
	    				<i class="iconfont icon-fanhui2" style="float:right;color:#999;line-height:60px;margin-right:18px;font-size:10px"></i>
	    			</div>
	    			<div class="webbody_left_li_hidden">
	    				
	    			</div>
    			</li>
    			<li class="webbody_left_li">
    				<div>
	    				<span style="float:left;color:#999;margin-left:24px;line-height:60px;font-size:13px;">游戏/体育</span>
	    				<i class="iconfont icon-fanhui2" style="float:right;color:#999;line-height:60px;margin-right:18px;font-size:10px"></i>
	    			</div>
	    			<div class="webbody_left_li_hidden">
	    				
	    			</div>
    			</li>
    			<li class="webbody_left_li">
    				<div>
	    				<span style="float:left;color:#999;margin-left:24px;line-height:60px;font-size:13px;">都市/现实</span>
	    				<i class="iconfont icon-fanhui2" style="float:right;color:#999;line-height:60px;margin-right:18px;font-size:10px"></i>
	    			</div>
	    			<div class="webbody_left_li_hidden">
	    				
	    			</div>
    			</li>
    			<li class="webbody_left_li">
    				<div>
	    				<span style="float:left;color:#999;margin-left:24px;line-height:60px;font-size:13px;">女生/男生</span>
	    				<i class="iconfont icon-fanhui2" style="float:right;color:#999;line-height:60px;margin-right:18px;font-size:10px"></i>
	    			</div>
	    			<div class="webbody_left_li_hidden">
	    				
	    			</div>
    			</li>
    			<li class="webbody_left_li">
    				<div>
	    				<span style="float:left;color:#999;margin-left:24px;line-height:60px;font-size:13px;">教育/励志</span>
	    				<i class="iconfont icon-fanhui2" style="float:right;color:#999;line-height:60px;margin-right:18px;font-size:10px"></i>
	    			</div>
	    			<div class="webbody_left_li_hidden">
	    				
	    			</div>
    			</li>
    			<li class="webbody_left_li">
    				<div>
	    				<span style="float:left;color:#999;margin-left:24px;line-height:60px;font-size:13px;">其它</span>
	    				<i class="iconfont icon-fanhui2" style="float:right;color:#999;line-height:60px;margin-right:18px;font-size:10px"></i>
	    			</div>
	    			<div class="webbody_left_li_hidden">
	    				
	    			</div>
    			</li>
    		</ul>
    	</div>
    	<div class="webbody_right_body">
	    	<div class="webbody_right_ad">
				<p style="font-size:20px;">昨日热销：</p>
				<ul>
					<li><a href="javascript:void(0)">《在深渊里仰望星空》--北冥鱼</a></li>
					<li><a href="javascript:void(0)">《五代十国：最乱的乱》--于迈</a></li>
					<li><a href="javascript:void(0)">《易中天品读中国》--易中天</a></li>
					<li><a href="javascript:void(0)">《黑白镜头里的世界史》--美国不列颠百科全书</a></li>
					<li><a href="javascript:void(0)">《不仅是铁血》--德意志</a></li>
				</ul>
			</div>
			<div class="webbody_right">
				<ul>
					<%
					ArrayList<HotBook> hot_book=(ArrayList<HotBook>)application.getAttribute("hot_book");
					 %>
					<c:forEach items="${ hot_book }" var="item">
						<li class="webbody_right_li"><a href="javascript:void(0)<%-- ${ item.href } --%>"><img src="${ item.src }" alt="${ item.alt }"></a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="webbody_bottom">
				<ul>
					<li><a href="javascript:void(0)"><i class="iconfont icon-shu5"></i><span>我的书库</span></a></li>
					<li><a href="javascript:void(0)"><i class="iconfont icon-dianpu"></i><span>进入店铺</span></a></li>
					<li><a href="javascript:void(0)"><i class="iconfont icon-yiguanzhu"></i><span>我的关注</span></a></li>
					<li><a href="javascript:void(0)"><i class="iconfont icon-shu"></i><span>最近观看</span></a></li>
				</ul>
			</div>
    	</div>
    </div>
    <!-- webbody end -->
    <!-- page start -->
    <ul class="page_root">
    	<%
    	HashMap<String, ArrayList<Book>> hot_map=(HashMap<String, ArrayList<Book>>)application.getAttribute("hot_map");
    	%>
    
    	<c:forEach items="${ hot_map }" var="maping">
	    	<li>
		    	<div class="page">
			    	<div class="page_head">
			    		<span class="iconfont " style="float:left;">${ maping.key }</span>
			    		<span class="page_head_right" style="float:right;"><a href="/hot">更多</a></span>
			    	</div>
			    	<div class="page_body">
			    		<ul>
			    			<c:forEach items="${ maping.value }" var="item">
				    			<li>
				    				<a href="javascript:void(0)"><img alt="" src="${ item.book_img_url }"></a>
				    				<div>
				    					<p>月销${ item.book_out_num }</p>
				    					<p>促销价：￥${ item.book_price }</p>
				    				</div>
				    				<input type="button" value="加入购物车" name="${ item.book_id }"/>
				    			</li>
				    		</c:forEach>
			    		</ul>
			    	</div>
		    	</div>
	    	</li>
	    </c:forEach>
    </ul>
    
    
    	<%
    	HashMap<String, ArrayList<Book>> title_map=(HashMap<String, ArrayList<Book>>)application.getAttribute("title_map");
    	%>
    
    	<c:forEach items="${ title_map }" var="maping">
	    	<li>
		    	<div class="page">
			    	<div class="page_head">
			    		<span class="iconfont " style="float:left;">${ maping.key }</span>
			    		<span class="page_head_right" style="float:right;"><a href="/hot">更多</a></span>
			    	</div>
			    	<div class="page_body">
			    		<ul>
			    			<c:forEach items="${ maping.value }" var="item">
				    			<li>
				    				<a href="javascript:void(0)"><img alt="" src="${ item.book_img_url }"></a>
				    				<div>
				    					<p>月销${ item.book_out_num }</p>
				    					<p>促销价：￥${ item.book_price }</p>
				    				</div>
				    				<input type="button" value="加入购物车"  name="${ item.book_id }"/>
				    			</li>
				    		</c:forEach>
			    		</ul>
			    	</div>
		    	</div>
	    	</li>
	    </c:forEach>
    </ul>
    <!-- page end -->
    
    <!-- footer start -->
    <div class="footer">
			<ul class="footer_ul">
				<li><a href="javascript:void(0)">关于我们</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">联系我们</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">人才招聘</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">商家入驻</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">营销中心</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">手机云阅</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">友情连接</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">销售联盟</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">云阅社区</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">云阅公益</a></li>
				<li>|</li>
				<li><a href="javascript:void(0)">English Site</a></li>
			</ul>
			<p>Copyright © 2012 - 2018 云阅yunyue.com 版权所有</p>
		</div>
	<!-- footer end -->
	
	<!-- 隐藏层  start -->
	<div class="hidden">
		<div id="hidden_bar"></div>
		<!-- 登录 -->
		<div class="login">
			<form action="#" method="post" onsubmit="return false">
				
				<a href="javascript:void(0)" class="exit"><i class="iconfont icon-fenxiangshanchu"></i></a>
				<span class="iconfont icon-book login_top">登录</span>
				<div class="input_content">
					<i class="iconfont icon-wo" style="position:absolute;color:#f09999;font-size:30px;margin:12px;"></i>
					<input type="text" name="username" placeholder="用户名/手机号/邮箱">
				</div>
				<div class="input_content">
					<i class="iconfont icon-mima" style="position:absolute;color:#f09999;font-size:30px;margin:12px;"></i>
					<input type="password" name="password"  placeholder="密码">
				</div>
				<div class="input_content" style="height:40px;margin:0 auto 10px;">
					<input type="button" value="登录" id="login_form"/>
				</div>
				<div class="forget">
					<a href="javascript:void(0)" style="float:left;">没有账号？去注册</a>
					<a href="javascript:void(0)" style="float:right;">忘记密码</a>
				</div>
				<p>快速登录</p>
				<div class="fast_login_icon">
					<ul>
						<li><i class="iconfont icon-qq"></i></li>
						<li><i class="iconfont icon-weixin"></i></li>
						<li><i class="iconfont icon-xinlang"></i></li>
					</ul>
				</div>
			</form>
		</div>
		<!-- 登录  end -->
		
		<!-- 注册  start-->
		<div class="register">
			<form action="/RegisterServlet" method="post">
				<a href="javascript:void(0)" class="exit"><i class="iconfont icon-fenxiangshanchu"></i></a>
				<span class="iconfont icon-book login_top">注册</span>
				<div class="input_content">
					<i class="iconfont icon-wo" style="position:absolute;color:#f09999;font-size:30px;margin:12px;"></i>
					<input type="text" name="username" placeholder="用户名/手机号/邮箱">
				</div>
				<div class="input_content">
					<i class="iconfont icon-mima" style="position:absolute;color:#f09999;font-size:30px;margin:12px;"></i>
					<input type="password" name="password"  placeholder="密码">
				</div>
				<div class="input_content">
					<i class="iconfont icon-mima" style="position:absolute;color:#f09999;font-size:30px;margin:12px;"></i>
					<input type="password" name="password"  placeholder="请再次输入密码">
				</div>
				<div class="input_content" style="height:40px;margin:0 auto 10px;">
					<input type="button" value="注册" id="register_form"/>
				</div>
				<div class="forget">
					<a href="javascript:void(0)" style="float:left;">已有账号？去登录</a>
					<!--  <a href="#" style="float:right;">忘记密码</a>    -->
				</div>
				<p>快速登录</p>
				<div class="fast_login_icon">
					<ul>
						<li><i class="iconfont icon-qq"></i></li>
						<li><i class="iconfont icon-weixin"></i></li>
						<li><i class="iconfont icon-xinlang"></i></li>
					</ul>
				</div>
			</form>
		</div>
		<!-- 注册  end-->
	</div>
	
	
	<!--  购物车  -->
		<div class="car_bar">
			<div class="car_bar_left">
				<div class="car_bar_left_top">
					<span style="width:16px;height:20px;padding-left:8px;font-size:12px;color:#fff;line-height:20px;text-align:center;display:block;padding-top:80px;" class="iconfont icon-book">云阅</span>
					<ul>
						<li class="car_button" style="padding:24px 0;">
							<span class="iconfont icon-31gouwuchexuanzhong"><div>购物车</div></span>
							<span id="car_item_num">0</span>
						</li>
						<li class="iconfont icon-yonghuming"></li>
						<li class="iconfont icon-yiguanzhu"></li>
						<li class="iconfont icon-fenxiang"></li>
						<li class="iconfont icon-weibo1"></li>
					</ul>
				</div>
				<div class="car_bar_left_bottom">
					<ul>
						<li class="iconfont icon-weixin3"></li>
						<li class="iconfont icon-zanxuanzhong"></li>
						<li class="iconfont icon-fanhui7"></li>
					</ul>
				</div>
			</div>
			<div class="car_bar_right">
				<div class="car_right_top">
					<div>
						<input type="checkbox" checked="checked" name="checked_all">
						选择所有	
					</div>
					
					<a href="javascript:void(0)">查看全部</a>
				</div>
				
				<div class="car_right_middle">
					<ul>
						<%
							HttpSession car=request.getSession();
						 %>
						<c:forEach items="${ car }" var="car_item">
							<li>
								<div class="car_item_top">
									<div>
										<input type="checkbox"/>
									</div>
									<div>
										<i style="float:left">《${ car_item.book_name }》</i>
										<i style="float:right">${ car_item.book_price }</i>
									</div>
								</div>
								<div class="car_item_bottom">
									<div >
										<input type="checkbox" />
									</div>
									<div>
										<img alt="" src="images/24175371-1_w_70.jpg" style="width:60px;float:left;">
										
										<span>作者：${ car_item.book_author }</span>
										<span>单价：${ car_item.book_price }</span>
										<span class="car_item_num_count"><i>数量：1</i><i>共计：${ car_item.book_price }</i></span>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="car_right_bottom">
					<div>
						<i>已选中1件</i>
						<i id="count_num">￥230.0</i>
					</div>
					<div>
						<input type="button" value="结算"/>
					</div>
				</div>
			</div>
		</div>
  </body>
</html>
