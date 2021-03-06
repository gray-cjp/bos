<%--
  Created by IntelliJ IDEA.
  com.cjp.domain.User: lenovo
  Date: 2019/7/1
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>layout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <%--这条需要放在js下方不然失效--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">--%>
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>--%>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>--%>
</head>
<body>
<table id="mytable"></table>
<!-- 方式三：使用easyUI提供的API创建datagrid -->
<script type="text/javascript">
    $(function(){
        var myIndex = -1;//全局变量，值为正在编辑行的索引
        //页面加载完成后，创建数据表格datagrid
        $("#mytable").datagrid({
            //定义标题行所有的列
            columns:[[
                {title:'编号',field:'id',checkbox:true},
                {width:150,title:'姓名',field:'name',editor:{
                        type:'validatebox',
                        options:{}
                    }},
                {width:150,title:'年龄',field:'age',editor:{
                        type:'numberbox',
                        options:{}
                    }},
                {width:150,title:'日期',field:'data',editor:{
                        type:'datebox',
                        options:{}
                    }}
            ]],
            //指定数据表格发送ajax请求的地址
            url:'${pageContext.request.contextPath }/json/datagrid_data.json',
            rownumbers:true,
            singleSelect:true,
            //定义工具栏
            toolbar:[
                {text:'添加',iconCls:'icon-add',
                    //为按钮绑定单击事件
                    handler:function(){
                        $("#mytable").datagrid("insertRow",{
                            index:0,//在第一行插入数据
                            row:{}//空行
                        });
                        $("#mytable").datagrid("beginEdit",0);
                        myIndex = 0;
                    }
                },
                {text:'删除',iconCls:'icon-remove',handler:function(){
                        //获得选中的行对象
                        var rows = $("#mytable").datagrid("getSelections");
                        if(rows.length == 1){
                            var row = rows[0];
                            //获得指定行对象的索引
                            myIndex = $("#mytable").datagrid("getRowIndex",row);
                        }
                        $("#mytable").datagrid("deleteRow",myIndex);
                        //$.post();
                    }},
                {text:'修改',iconCls:'icon-edit',handler:function(){
                        //获得选中的行对象
                        var rows = $("#mytable").datagrid("getSelections");
                        if(rows.length == 1){
                            var row = rows[0];
                            //获得指定行对象的索引
                            myIndex = $("#mytable").datagrid("getRowIndex",row);
                        }
                        $("#mytable").datagrid("beginEdit",myIndex);
                    }},
                {text:'保存',iconCls:'icon-save',handler:function(){
                        $("#mytable").datagrid("endEdit",myIndex);
                    }}
            ],
            //显示分页条
            pagination:true,
            pageList:[3,5,7,10],
            //数据表格提供的用于监听结束编辑事件
            onAfterEdit:function(index,data,changes){
                console.info(data);
                $.post();
            }
        });
    });
</script>
</body>
</html>
