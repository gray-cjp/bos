<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/3
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function(){
            //alert方法---提示框
            // $.messager.alert("标题","内容","question");

            //confirm方法---确认框
            /**
             $.messager.confirm("提示信息","你确定要删除当前记录吗？",function(r){
			alert(r);
		});
             **/

            //show方法---欢迎框
            $.messager.show({
                title:'欢迎信息',
                msg:'欢迎【admin】登录系统！',
                timeout:5000,
                showType:'slide'
            });
        });
    </script>
</head>
<body>
<br><br><br><br>
<a data-options="iconCls:'icon-help',menu:'#mm'" class="easyui-menubutton">控制面板</a>
<div id="mm">
    <div data-options="iconCls:'icon-edit'">修改密码</div>
    <div>联系管理员</div>
    <div class="menu-sep"></div>
    <div>退出系统</div>
</div>
</body>
</html>
