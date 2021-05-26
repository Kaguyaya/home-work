<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/23 0023
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<table class="layui-hide" id="test"></table>


<script src="./layui/layui.all.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->

<script>
    layui.use('table', function(){

        var table = layui.table;
        table.render({
            elem: '#test'
            ,method:'post'
            ,url:'/book/borrowlist'
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'card_id', width:80, title: '卡号'}
                ,{field:'book_id', width:80, title: '书号', sort: true}
                ,{field:'borrow_date', minwidth:100, title: '借书日期'}
                ,{field:'end_date', title: '截止时间', minWidth: 100}
                ,{field:'return_date', minwidth:100, title: '还书日期', sort: true}
                ,{field:'illegal', width:80, title: '违约', sort: true}
                ,{field:'manager_id', width:80, title: '管理员'}
            ]]
            ,page: true
        });
    });
</script>

</body>
</html>
