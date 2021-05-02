<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cn.edu.niit.javabean.Book" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Layui</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="./layui/css/layui.css"
		  media="all">
	<!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
	<style>
		.wrap-div {
			display: -webkit-box;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 3;
			overflow: hidden;
			float: left;
			width: 100%;
			word-break: break-all;
			text-overflow: ellipsis;
		}
	</style>
</head>
<body>

<div class="layui-nav-item demoTable"
	 style="display: flex;justify-content: flex-end;">
	<input id="keyword" type="text" class="layui-input"
		   style="padding: 0;display: inline;width: 300px;"
		   placeholder="请输入搜索信息..."/>
	<button id="search" class="layui-btn" data-type="getCheckLength"
			style="margin-left: 20px;">搜索
	</button>
</div>

<div class="layui-form" id="content">
	<table class="layui-table" style="table-layout:fixed">
		<colgroup>
			<col width="150">
			<col width="150">
			<col width="200">
			<col>
			<col width="180">
		</colgroup>
		<thead>
		<tr>
			<th>书名</th>
			<th>作者</th>
			<th>分类</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="book" items="${sessionScope.books}"
				   varStatus="status">
			<tr>
				<td>${book.name}</td>
				<td>${book.author}</td>
				<td>${book.sort}</td>
				<td class="wrap-td">
					<div class="wrap-div">${book.description}</div>
				</td>
				<td>
					<button
							data-method="setTop"
							class="layui-btn layui-btn-primary layui-btn-xs detail"
							id="info">查看
					</button>
					<button class="layui-btn layui-btn-xs borrow"
							id="borrow" index="${status.index}">借阅
					</button>
					<button class="layui-btn layui-btn-xs borrow"
							id="store" index="${book.id}">
							${book.store?"已收藏":"收藏"}
					</button>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>

<div id="page" style="display: flex;justify-content: center;">
</div>

<script src="./layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
	layui.use(['laypage', 'layer', 'element'], function () {
		var laypage = layui.laypage
				, layer = layui.layer, element =
				layui.element;
		var $ = layui.$;
		var count = 0, current = 1, limit = 5;

		var active = {
			setTop: function(){
				var that = this;
				//多窗口模式，层叠置顶
				layer.open({
					type: 2 //此处以iframe举例
					,title: '当你选择该窗体时，即会在最顶端'
					,area: ['390px', '260px']
					,shade: 0
					,maxmin: true
					,offset: [ //为了演示，随机坐标
						Math.random()*($(window).height()-300)
						,Math.random()*($(window).width()-390)
					]
					,content: '//layer.layui.com/test/settop.html'
					,btn: ['继续弹出', '全部关闭'] //只是为了演示
					,yes: function(){
						$(that).click();
					}
					,btn2: function(){
						layer.closeAll();
					}

					,zIndex: layer.zIndex //重点1
					,success: function(layero){
						layer.setTop(layero); //重点2
					}
				});
			}};



		$(document).on('click', '#store', function () {
			//可以获取第一列的内容，也就是name的值
			var name = $(this).parents("tr").find("td").eq(0).text();
			var bookid = $(this).attr("index");
			$.ajax({
				type: 'POST',
				url: "/book/store",
				data: JSON.stringify({
					user: ${sessionScope.id}+"",
					book: bookid
				}),
				contentType: "application/json;charset=utf-8",
				success: function (data) {
					// $('#content').load(location.href + " #content");
					//count从Servlet中得到
					// count = data;
					layer.msg(data)
					if (data == '借阅成功') {
						$('#store').text("已收藏")
					}

				}
			});
		})

		//借阅按钮的点击事件
		$(document).on('click', '#borrow', function () {
			//可以获取第一列的内容，也就是name的值
			var name = $(this).parents("tr").find("td").eq(0).text();
			//也可以获取属性中的值
			console.log($(this).attr("index"))
			layer.msg(name)
		})


		$('#search').click(function () {
			var keyword = $('#keyword').val();
			alert(keyword)
		});

		$(document).ready(function () {
			//进入页面先加载数据
			getContent(1, limit);
			//得到数量count后，渲染表格
			laypage.render({
				elem: 'page',
				count: count,
				curr: current,
				limits: [5, 10, 15, 20],
				limit: limit,
				layout: ['count', 'prev', 'page', 'next', 'limit'],
				jump: function (obj, first) {
					if (!first) {
						getContent(obj.curr, obj.limit);
						//更新当前页码和当前每页显示条数
						current = obj.curr;
						limit = obj.limit;
					}
				}
			});
		});

		function getContent(page, size) {
			$.ajax({
				type: 'POST',
				url: "/book/search",
				async: false, //开启同步请求，为了保证先得到count再渲染表格
				data: JSON.stringify({
					pageNum: page,
					pageSize: size
				}),
				contentType: "application/json;charset=utf-8",
				success: function (data) {
					$('#content').load(location.href + " #content");
					//count从Servlet中得到
					count = data;
						}
					});
				}
		//查看按钮的点击事件
		$(document).on('click','#info', function(){
			var othis = $(this), method = othis.data('method');
			active[method] ? active[method].call(this, othis) : '';
		});
			}
	);
</script>

</body>
</html>
