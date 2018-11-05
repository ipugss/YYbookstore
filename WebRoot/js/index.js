var fun_login=function(){
			var defer=$.Deferred();//定义deferred对象
		
			$.ajax({
				url:"/login",
				type:"post",
				data:$($("form")[1]).serialize(),
				async:true,
				dataType:"json",
				success:function(data){
					defer.resolve(data);
				}
			});
			return defer;
		};
		
var fun_register=function(){
		var defer=$.Deferred();
		
		$.ajax({
			url:"/register",
			type:"post",
			data:$($("form")[2]).serialize(),
			async:true,
			dataType:"json",
			success:function(data){
				defer.resolve(data);
			}
		});
		return defer;
	};
		
var Wait={
		i:0,
		func:"",
		start:function(obj){
				var setInt=setInterval(function(){
					if(Wait.i==-1){
						clearInterval(setInt);
					}else{
						Wait.i=Wait.i%4;
						if(Wait.i==0){
							$(obj).val(Wait.func);
						}else{
							$(obj).val($(obj).val()+".");
						}
						Wait.i++;
					}
					
				},500);
		},
		end:function(){
			Wait.i=-1;
		},
	};

var fun_login_out=function(){
	if($.cookie("username")!=null){
		$.removeCookie("username");
		$.removeCookie("password");
		$.ajax({
			url:"/LoginOut",
			type:"post",
			success:function(){
				window.location="/";
			}
		});
		
	}
}
		

//飞
function fly(e){
	//落脚点
	var offset=$(".car_button:first").offset();
	//获取图片
	var src=$(e.target).parent().find("a").children("img").attr("src");
	//创建飞的img标签
	var flyer=$("<img src='"+src+"' style='width:50px;height:50px;'/>")
	
	flyer.fly({
		start:{
			left:e.clientX,
			top:e.clientY
		},
		end:{
			left:offset.left,
			top:200,
			width:20,
			height:20,
		},
		onEnd:function(){
			flyer.fadeOut('slow',function(){
				$(this).remove();
			});
		}
	});
}

function addCar(carid){
	$.ajax({
		url:"/CarIn",
		type:"post",
		data:({
			"car":carid
		}),
		async:true,
		dataType:"json",
		
		success:function(data){
			$(".car_right_middle ul").html($(".car_right_middle ul").html()+
			'<li>'+
			'<div class="car_item_top">'+
			'	<div>'+
			'		<input type="checkbox"/>'+
			'	</div>'+
			'	<div>'+
			'		<i style="float:left">《'+data.book_name+'》</i>'+
			'		<i style="float:right">'+data.book_price+'</i>'+
			'	</div>'+
			'</div>'+
			'<div class="car_item_bottom">'+
			'	<div >'+
			'		<input type="checkbox" />'+
			'	</div>'+
			'	<div>'+
			'		<img alt="" src="'+data.book_img_url+'" style="width:60px;float:left;">'+
			'		'+
			'		<span>作者：'+data.book_author+'</span>'+
			'		<span>单价：'+data.book_price+'</span>'+
			'		<span class="car_item_num_count"><i>数量：1</i><i>共计：'+data.book_price+'</i></span>'+
			'	</div>'+
			'</div>'+
			'</li>'
			);
		}
	});
}

		
$(function(){
	//隐藏bar
	var $hidden=$(".hidden");
	//login面板
	var $login=$(".login");
	//register面板
	var $register=$(".register");
	//search输入框
	var $search=$("#search");
	//search_form表单
	var $search_form=$("#search_form");
	
	
	
	$(".exit").click(function(){
		$hidden.toggle(1000);
		$login.hide(1000);
		$register.hide(1000);
	});
	
	$("#login").click(function(){
		$login.css("display","block");
		$hidden.toggle(1000);
	});
	
	$("#register").click(function(){
		$register.css("display","block");
		$hidden.toggle(1000);
	});
	
	/*
	  
	 退出登录
	  
	 */
	
	$("#login_out").click(fun_login_out);
	
	/* 
	
		搜索功能接口
	
	 */
	$("#do_search").click(function(){
		var key=$search.val();
		if(key==null||key==""){
			//为空不提交
			return false;
		}else{
			//提交
			return true;
		}
	});
	
	/*
	 
	 为搜索框绑定回车事件
	 
	 */
	$("#search").bind('keyup',function(e){
		if(e.keyCode=="13"){
			$("#do_search").click();
		}
	});
	
	
	
	/*
	
		登录接口，检查
	
	*/
	$("#login_form").click(function(){
		
		var username=$("input[name='username']")[0].value;
		var password=$("input[name='password']")[0].value;
		if(username==null||username==""){
			$("#login_form").val("用户名不能为空哦");
			return false;
		}else if(password==null||password==""){
			$("#login_form").val("密码不能为空哦");
			return false;
		}
		else{
			Wait.i=0;
			Wait.func="登录中";
			//打开等待图标
			Wait.start(this);
			$.when(fun_login()).done(function(data){
				
				//解析返回数据
				var loginstatus=data[0].loginstatus;
				if(loginstatus=="false"){
					//登录失败
					Wait.end();
					$("#login_form").val("登录失败，请检查用户名密码");
					
				}else if(loginstatus=="true"){
					//登录成功
					Wait.end();
					$("#login_form").val("登录成功"); 
					//setTimeout(function(){$hidden.toggle(1000);$login.hide(1000);$register.hide(1000);},1000);
					setTimeout(function(){window.location='/';},1000);
					
					
					//删除登录注册
					//$(".head_top_left ul").html("<li>尊敬的会员，欢迎回来</li><li><i class='iconfont icon-vertical_line'></i></li><li class='iconfont icon-yonghuming'><a href='javascript:void(0)'>"+data[1].username+"</a>");
				}else if(loginstatus=="err"){
					//登录失败,服务器故障
					Wait.end();
					$("#login_form").val("服务器遇到了点麻烦，请稍后再试");
				}
			});
		}
	});
	
	/*
	 
	 为登录的用户名框绑定回车事件
	 
	 */
	$($("input[name='username']")[0]).bind("keyup",function(e){
		if(e.keyCode=="13"){
			$("#login_form").click();
		}
		
	});
	
	
	
	
	/*
	 
	 为登录的密码框绑定回车事件
	 
	 */
	$($("input[type='password']")[0]).bind("keyup",function(e){
		if(e.keyCode=="13"){
			$("#login_form").click();
		}
		
	});
	
	
	
	/*
	
		注册接口，检查
	
	*/

	$("#register_form").click(function(){
		var username=$("input[name='username']")[1].value;
		var password1=$("input[name='password']")[1].value;
		var password2=$("input[name='password']")[2].value;
		if(username==null||username==""){
			$("#register_form").val("用户名为空哦！");
			return false;
		}else if(password1==null||password1==""){
			$("#register_form").val("密码为空哦！");
			return false;
		}else if(password2==null||password2==""){
			$("#register_form").val("请确认密码好吧！");
			return false;
		}else if(password1!=password2){
			$("#register_form").val("密码不一样嘞！");
			return false;
		}else{
			//$("#register_form").val("正在注册，请稍等");
			Wait.i=0;
			Wait.func="正在注册，请稍等";
			//打开等待图标
			Wait.start(this);
			$.when(fun_register()).done(function(data){
				
				//解析返回数据
				var registerstatus=data[0].registerstatus;
				if(registerstatus=="false"){
					//注册失败
					Wait.end();
					$("#register_form").val("注册失败，请请稍后重试");
					
				}else if(registerstatus=="success"){
					//注册成功
					Wait.end();
					$("#register_form").val("注册成功");
					//setTimeout(function(){$hidden.toggle(1000);$login.hide(1000);$register.hide(1000);},1000);
					setTimeout(function(){window.location='/';},1000);
					
					
					//删除登录注册
					$(".head_top_left ul").html("<li>尊敬的会员，欢迎光临</li><li><i class='iconfont icon-vertical_line'></i></li><li class='iconfont icon-yonghuming'><a href='javascript:void(0)'>"+data[1].username+"</a>");
				}else if(registerstatus=="err"){
					//登录失败,服务器故障
					setTimeout(function(){Wait.end();$("#register_form").val("服务器遇到了点麻烦，请稍后再试");},2000);
				}
			});
		}
	});
	
	/*
	 
	 为注册的密码框绑定回车事件
	 
	 */
	$($("input[type='password']")[2]).bind("keyup",function(e){
		console.log(e.keyCode);
		if(e.keyCode=="13"){
			$("#register_form").click();
		}
		
	});
	
	
	
	
	
	
	/*
	
		购物车接口
	
	*/
	$(".page_body ul li input[type='button']").click(function(e){
		var username=$.cookie("username");
		if(typeof username!='undefined'&&username!=""){
			var car=$.cookie("car");
			if(typeof car=='undefined'||car==""){
				$.cookie("car",this.getAttribute("name"));
				addCar(this.getAttribute("name"));
				fly(e);
				
				
				
			}else{
				if(car.indexOf(this.getAttribute("name"))>=0){
					fly(e);
					return;
				}else{
					if(this.getAttribute("name")!=null){
						
						$.cookie("car",car+"&"+this.getAttribute("name"));
						
						addCar(this.getAttribute("name"));
						//添加到购物车
						fly(e);
					}
					
				}
			}
			
			
		}else{
			alert("请先登录");
			$("#login").click();
		}
	});
	
	
	
	/*
	
		购物车隐藏显示
	
	 */
	function car_bar(){
		$(".car_button:first").unbind('mousedown');
		$(".car_bar:first").animate({
			right:'0px'
		},"slow",function(){
			$("body").click(function(e){
				e=e||window.even;
				var target=e.target||e.srcElement;
				console.log(target);
				if(target!=$(".car_bar:first")){
					$(".car_bar:first").animate({
						right:'-288px'
					},"slow",function(){
						$("body").unbind("click");
						$(".car_button:first").mousedown(car_bar);
					});
				}
			});
		});
		
	}
	//阻止事件向上传递
	$(".car_bar:first").click(function(e){
		e=e||window.even;
		e.stopPropagation(); 
	});
	$(".car_button:first").mousedown(car_bar);
	
	
	/*
	
		轮播图
	
	*/
	
	
	var setInt=setInterval(function(){
				$(".webbody ul").animate({left:(parseInt($(".webbody ul").css("left"))>-2250?parseInt($(".webbody ul").css("left"))-750:0)+"px"},1000);
			},5000);

});