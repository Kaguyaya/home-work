<%--
  Created by IntelliJ IDEA.
  User: DaHa
  Date: 2021/3/22
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>评论列表页面</title>

	<link rel="stylesheet" type="text/css" href="common/layui/css/layui.css" media="all">



</head>
<style type="text/css">
	.comment_list {
		padding-top:40px;
		width:700px;
		margin:0 auto;
	}
	.comment_details {
		float:left;
	}
	.comment_content {
		margin-top:10px;
		font-size:16px;
	}
	.comment_add_or_last {

		margin:0 auto;
		clear: both;
		width:600px;
		height:40px;
		background: #F0F0F0;
		text-align: center;
		line-height: 40px;   //行高（与div保持同高，垂直居中写法）
	}
	.imgdiv{
		float:left;

	}
	.imgcss {
		width:50px;
		height:50px;
		border-radius: 50%;
	}
	.comment_name {
		margin-left:10px;
		color:#3D9EEA;
		font-size:15px;
		font-weight: bolder;
	}
	.layui-icon {
		font-size: 10px;
		color: grey;
	}
	.del {
		margin-left: 55px;
	}
</style>
<body >

<div class="comment_list">
	<h2 >全部评论</h2>
	<hr>
	<div class="comment">
		<div class="imgdiv"><img class="imgcss"  src="${sessionScope.user.header}"/></div>
		<div class="conmment_details">
			<span class="comment_name">大白 </span>     <span>22分钟前</span>
			<div class="comment_content" >  感觉林丹越来越帅了，好棒</div>
			<div class="del"> <i class="icon layui-icon"  >赞(164)</i>
				<a class="del_comment" data-id="1"> <i class="icon layui-icon" >删除</i></a>
			</div>
		</div>
		<hr>
	</div>
	<div class="comment">
		<div class="imgdiv"><img class="imgcss"  src="${sessionScope.user.header}"/></div>
		<div class="conmment_details">
			<span class="comment_name">大白 </span>     <span>22分钟前</span>
			<div class="comment_content" >  感觉林丹越来越帅了，好棒</div>
			<div class="del"> <i class="icon layui-icon"  >赞(164)</i>
				<a class="del_comment" data-id="1"> <i class="icon layui-icon" >删除</i></a>
			</div>
		</div>
		<hr>
	</div>
	<div class="comment">
		<div class="imgdiv"><img class="imgcss"  src="${sessionScope.user.header}"/></div>
		<div class="conmment_details">
			<span class="comment_name">大白 </span>     <span>22分钟前</span>
			<div class="comment_content" >  感觉林丹越来越帅了，好棒</div>
			<div class="del"> <i class="icon layui-icon"  >赞(164)</i>
				<a class="del_comment" data-id="1"> <i class="icon layui-icon" >删除</i></a>
			</div>
		</div>
		<hr>
	</div>
</div>
<div class="comment_add_or_last" >
	没有更多评论了
</div>
</body>
<script type="text/javascript" src="common/layui/layui.js"></script>
<script type="text/javascript">
	layui.use(['form','layer','jquery','element','laypage','form'],function(){
		var form = layui.form();
		window.layer = layui.layer;
		layedit = layui.layedit;
		window.jQuery = window.$ = layui.jquery;

		var element = layui.element(),
				form = layui.form(),
				laypage = layui.laypage;
		//删除用户
		$(document).on('click', '.del_comment', function() {
			var id = $(this).attr("data-id");
			alert(typeof id);
			$(this).closest('.comment').css("display", "none");
		});

		$(document).on('click', '.comment_add_or_last', function() {
			add();
			$(this).html( "点击加载更多");
		});

		function  delComment(id) {
			alert(id);
		}
		function  add() {
			var s = " ";
			s += '<div class="comment">';
			s += '<div class="imgdiv"><img class="imgcss"  src="./images/user.jpg"/></div>';
			s += '<div class="conmment_details">';
			s +=  '<span class="comment_name">大白 </span>     <span>22分钟前</span>';
			s += 	'<div class="comment_content" >  感觉林丹越来越帅了，好棒</div>';
			s += 	'<div class="del"> <i class="icon layui-icon"  >赞(164)</i>';
			s += 	'<a class="del_comment"  data-id="1"><i class="icon layui-icon" >删除</i></a></div></div><hr></div>';
			$('.comment_list').append(s);
		}
	});
</script>
</html>