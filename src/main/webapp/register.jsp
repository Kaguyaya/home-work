<%@ page import="com.mysql.cj.util.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: 86152
  Date: 2021/3/22
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>注册页面</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <style>
        .login-main {
            width: 50%;
            height: 50%;
            position: relative;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>
<body>

<%@ include file="window.jsp" %>

<div class="login-main">
    <header class="layui-elip"
            style="width: 82%; font-size: 30px;
            text-align: center;margin-bottom: 20px">注册页
    </header>

    <!-- 表单选项 -->
    <form class="layui-form" method="post" action="/register">
        <div class="layui-form-item">
            <!-- 昵称 -->
            <div class="layui-inline" style="width: 85%">
                <input type="text" id="user" name="reader" required
                       lay-verify="required" placeholder="请输入你的昵称"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <!-- 登录账号 -->
            <div class="layui-inline" style="width: 85%">
                <input type="text" id="username" name="username"
                       required lay-verify="required"
                       placeholder="请输入登录账号" autocomplete="off"
                       class="layui-input">
            </div>
            <!-- 对号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="rin"
                   style="color: green;font-weight: bolder;"
                   hidden></i>
            </div>
            <!-- 错号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="wrn"
                   style="color: red; font-weight: bolder;"
                   hidden>ဆ</i>
            </div>
        </div>
        <!-- 密码 -->
        <div class="layui-form-item">
            <div class="layui-inline" style="width: 85%">
                <input type="password" id="pwd" name="password"
                       required lay-verify="required"
                       placeholder="请输入密码" autocomplete="off"
                       class="layui-input">
            </div>
            <!-- 对号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="pri"
                   style="color: green;font-weight: bolder;"
                   hidden></i>
            </div>
            <!-- 错号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="pwr"
                   style="color: red; font-weight: bolder;"
                   hidden>ဆ</i>
            </div>
        </div>
        <!-- 确认密码 -->
        <div class="layui-form-item">
            <div class="layui-inline" style="width: 85%">
                <input type="password" id="rpwd" name="repassword"
                       required lay-verify="required"
                       placeholder="请确认密码" autocomplete="off"
                       class="layui-input">
            </div>
            <!-- 对号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="rpri"
                   style="color: green;font-weight: bolder;"
                   hidden></i>
            </div>
            <!-- 错号 -->
            <div class="layui-inline">
                <i class="layui-icon" id="rpwr"
                   style="color: red; font-weight: bolder;"
                   hidden>ဆ</i>
            </div>
        </div>


        <div class="layui-form-item">
            <button type="submit" lay-submit lay-filter="sub"
                    class="layui-btn" style="width: 85%">注册
            </button>
        </div>
        <hr style="width: 85%"/>
        <p style="width: 85%"><a href="index.jsp" class="fl">
            已有账号？立即登录</a><a href="javascript:;"
                            style="float: right">忘记密码？
        </a></p>
    </form>
</div>


<script src="./layui/layui.js"></script>
<script>
    $('#rpwd').blur(function () {
        if ($('#pwd').val() != $('#rpwd').val()) {
            $('#rpwr').removeAttr('hidden');
            $('#rpri').attr('hidden', 'hidden');
            layer.msg('两次输入密码不一致!');
        } else {
            $('#rpri').removeAttr('hidden');
            $('#rpwr').attr('hidden', 'hidden');
        }
        ;
    });

</script>

</body>
</html>
