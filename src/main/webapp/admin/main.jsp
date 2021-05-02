<%@ page import="cn.edu.niit.javabean.User" %>
<%@ page import="cn.edu.niit.javabean.Admin" %><%--
  Created by IntelliJ IDEA.
  User: DaHa
  Date: 2021/3/15
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>图书馆管理端</title>
	<link rel="stylesheet" href="/layui/css/layui.css"/>
	<style>
		.layui-show {
			width: 100%;
			height: 100%;
		}
	</style>
</head>
<body class="layui-layout-body">

<%
	Admin user = (Admin) request.getSession().getAttribute("admin");
%>

<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo" style="font-size: 25px">图书馆管理端</div>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="<%=user.getUsername()%>"
						 class="layui-nav-img">
					管理员
				</a>
				<dl class="layui-nav-child">
					<dd><a href="javascript:;" name="borrow"
						   title="个人信息"
						   content="./personalInfo.jsp" id="4">个人信息
					</a></dd>
					<dd><a href="javascript:;" name="borrow"
						   title="系统设置"
						   content="./searchBooks.jsp" id="1">系统设置
					</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="">退出</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree" lay-filter="test">
				<li class="layui-nav-item layui-nav-itemed">
					<a href="javascript:;">图书服务</a>
					<dl class="layui-nav-child">
						<dd><a href="javascript:;" name="borrow"
							   title="查询图书"
							   content="./searchBooks.jsp" id="2"
						>查询图书
						</a></dd>
						<dd><a href="javascript:;" name="borrow"
							   title="借阅历史"
							   content="./borrowHistory.jsp" id="3">
							借阅历史</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;">读者服务</a>
					<dl class="layui-nav-child">
						<dd><a href="javascript:;" name="borrow"
							   title="个人信息"
							   content="./personalInfo.jsp" id="4">
							个人信息</a></dd>
						<dd><a href="javascript:;" name="borrow"
							   title="图书收藏"
							   content="./favoriteList.jsp" id="5">
							图书收藏</a></dd>
						<dd><a href="javascript:;" name="borrow"
							   title="在借图书"
							   content="./borrowList.jsp" id="6">
							在借图书</a></dd>
					</dl>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;" name="borrow"
					   title="留言板"
					   content="./messageBoard.jsp" id="7">留言板</a>
				</li>
				<li class="layui-nav-item">
					<a href="javascript:;" name="borrow"
					   title="通知中心"
					   content="./notification.jsp" id="8">通知中心</a>
				</li>
			</ul>
		</div>
	</div>

	<div class="layui-body">
		<div class="layui-tab layui-tab-brief" lay-filter="tabTemp"
			 lay-allowClose="true"
			 style="display: flex;flex-direction: column;height:
		     100%;margin: 0;padding: 0;">
			<ul class="layui-tab-title">

			</ul>
			<div class="layui-tab-content"
				 style="flex-grow: 1;">

			</div>
		</div>
	</div>

	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© 软件工程专业
	</div>
</div>
<script src="./layui/layui.js"></script>
<script>
	//JavaScript代码区域
	layui.use(['element'], function () {
		var element = layui.element;
		var $ = layui.$;
		$("[name=borrow]").click(function () {
			//获取当前项的id和content
			var id = $(this).attr("id");
			var content = $(this).attr("content");
			//判断标签是否存在
			if ($("li[lay-id=" + id + "]").length == 0) {
				//添加新标签
				element.tabAdd("tabTemp", {
					title: $(this).attr("title"),
					content:
							"<iframe style='height: 100%;width: 100%' src='" +
							content + "' class='frame' frameborder='0'></iframe>",
					id: id
				});
			}
			//切换标签
			element.tabChange("tabTemp", id);
		});
	});
</script>
</body>
</html>
