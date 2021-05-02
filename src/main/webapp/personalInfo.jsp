<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DaHa
  Date: 2021/3/22
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>设置我的资料</title>
	<link rel="stylesheet" href="/layui/css/layui.css" media="all">
</head>

<body>

<%@include file="window.jsp" %>
<jsp:useBean id="user" class="cn.edu.niit.javabean.User"
             scope="session"/>

<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">设置我的资料</div>
				<div class="layui-card-body" pad15>
					<div class="layui-row">
						<div class="layui-col-md2"
						     style="display: flex;justify-content: flex-end;">
							<img class="layui-header"
							     src="<jsp:getProperty name="user" property="header"/>"/>
						</div>
						<div class="layui-col-md10">
							<form action="/personal/upload"
							      method="post"
							      enctype="multipart/form-data">
								<div class="layui-form" lay-filter="">
									<div class="layui-form-item">
										<label class="layui-form-label">用户名</label>
										<div class="layui-input-inline">
											<input type="text"
											       name="username"
											       value="<jsp:getProperty name="user" property="username"/>"
											       readonly
											       class="layui-input">
										</div>
										<div class="layui-form-mid layui-word-aux">
											不可修改,用于后台登录名
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">昵称</label>
										<div class="layui-input-inline">
											<input type="text"
											       name="nickname"
											       value="<jsp:getProperty name="user" property="reader"/>"
											       lay-verify="nickname"
											       autocomplete="off"
											       placeholder="请输入昵称"
											       class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">性别</label>
										<div class="layui-input-block">
											<input type="radio"
											       name="sex"
											       value="男"
											       title="男"
													<%=user.isSex() ? "checked" : ""%>/>
											<input type="radio"
											       name="sex"
											       value="女"
											       title="女" <%=user.isSex() ? "" : "checked"%>/>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">头像</label>
										<%--										<div class="layui-input-inline">--%>
										<%--											<input name="avatar"--%>
										<%--												   lay-verify="required"--%>
										<%--												   id="LAY_avatarSrc"--%>
										<%--												   placeholder="图片地址"--%>
										<%--												   value=""--%>
										<%--												   class="layui-input">--%>
										<%--										</div>--%>
										<div class="layui-input-inline"
										     style="width: auto;">
											<input type="file"
											       class="layui-btn layui-btn-primary"
											       name="avatar">
											</input>
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">手机</label>
										<div class="layui-input-inline">
											<input type="text"
											       name="cellphone"
											       value="<jsp:getProperty name="user" property="cellphone"/>"
											       lay-verify="phone"
											       autocomplete="off"
											       class="layui-input">
										</div>
									</div>
									<div class="layui-form-item">
										<label class="layui-form-label">邮箱</label>
										<div class="layui-input-inline">
											<input type="text"
											       name="email"
											       value="<jsp:getProperty name="user" property="email"/>"
											       lay-verify="email"
											       autocomplete="off"
											       class="layui-input">
										</div>
									</div>
									<div class="layui-form-item layui-form-text">
										<label class="layui-form-label">备注</label>
										<div class="layui-input-block">
										<textarea name="remarks"
										          placeholder="请输入内容"
										          class="layui-textarea"><jsp:getProperty
												name="user"
												property="describe"/>
										</textarea>
										</div>
									</div>
									<div class="layui-form-item">
										<div class="layui-input-block">
											<input type="submit"
											       class="layui-btn"
											       value="确认修改"
											       lay-filter="setmyinfo">
											</input>
											<button type="reset"
											        class="layui-btn layui-btn-primary">
												重新填写
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="/layui/layui.js"></script>
<script>
    layui.use(['form', 'upload'], function () {
        var form = layui.form;
        var upload = layui.upload;
        var $ = layui.$;

        $("#head", parent.document).attr('src',
            '${sessionScope.user.header}');
        $("#reader", parent.document).html(
            '${sessionScope.user.reader}');

        //执行实例
        var uploadInst = upload.render({
            elem: '#LAY_avatarUpload' //绑定元素
            , url: '/upload/' //上传接口
            , done: function (res) {
                //上传完毕回调
            }
            , error: function () {
                //请求异常回调
            }
        });


        //各种基于事件的操作，下面会有进一步介绍
    });
</script>
</body>

</html>
