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

<table  class="layui-hide" id="test" lay-filter="test"></table>


<script src="./layui/layui.all.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">归还</a>
    <a class="layui-btn layui-btn-xs  layui-btn-disabled"  lay-event="del">删除</a>
</script>
<script>
    layui.use('table', function(){
        var $ = layui.$;
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
                ,{fixed: 'right', title:'操作',align:'center',toolbar: '#barDemo', width:150}
            ]]
        });
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var tabledata = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if(layEvent === 'detail'){ //查看
                layer.confirm('确认归还?', function(data){
                    $.ajax({
                        type: 'POST',
                        url: "/book/boorowtable",
                        data: JSON.stringify({
                            id:tabledata.id
                        }),
                        contentType: "application/json;charset=utf-8",
                        success: function (data) {
                            // $('#content').load(location.href + " #content");
                            //count从Servlet中得到
                            // count = data;
                            table.reload('test', {
                                url: '/book/borrowlist'
                                ,where: {} //设定异步数据接口的额外参数
                                //,height: 300
                            });

                            console.log(data)
                            layer.msg(data)
                        }
                    });
                     });

            }
        });
    });
</script>

</body>
</html>
