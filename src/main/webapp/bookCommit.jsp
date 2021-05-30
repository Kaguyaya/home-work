<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/30 0030
  Time: 01:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="./layui/css/layui.css"  media="all">

  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div id="content" style="width: 700px; text-align: center; margin:0 auto">
<c:forEach var="bookcommit" items="${sessionScope.commits}" varStatus="status">
  <div>
    <fieldset  class="layui-elem-field layui-field-title " style="margin-top: 20px;">
      <legend>用户${bookcommit.id}</legend>
    </fieldset>
  </div>
  <div class="layui-bg-gray" style="padding: 30px;">
    <div class="layui-row layui-col-space15" style="margin-bottom: 10px">
      <div class="layui-col-md6">
        <div class="layui-panel">
          <div style="padding: 50px 30px;text-align: left">${bookcommit.commit}</div>
        </div>
      </div>
    </div>
  </div>
</c:forEach>
</div>
<div style="width: 700px; text-align: center; margin:0 auto">
  <form id="form" class="layui-form layui-form-pane" action="#" method="get">
    <div class="layui-form-item layui-form-text">
      <label class="layui-form-label">评论框</label>
      <div class="layui-input-block">
        <textarea name="textname" placeholder="请输入内容" class="layui-textarea"></textarea>
      </div>
      <button id="comit" type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
    </div>
  </form>
</div>

  <script src="./layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->
<script>
  layui.use(['layer','element'], function(){
    var element = layui.element;
    var layer = layui.layer;
    var $ = layui.$;
    //监听折叠


    $(document).ready(function () {
      //进入页面先加载数据
      getContent();
      //得到数量count后，渲染表格
    });

    function getContent() {
      $.ajax({
        type: 'POST',
        url: "/book/commit",
        data:JSON.stringify({
          name:"123"
        }),
        contentType: "application/json;charset=utf-8"
        , success: function () {

        }
      });
    }
    $(document).on('click',"#comit",function(){
      login();
    });
    function login(){
      $.ajax({
        type:'POST',
        url:"/book/comit",
        data:$('#form').serialize(),
        success:function (data){
          $('#content').load(location.href+"content");
        }
      });
    }
  });

</script>
</body>
</html>