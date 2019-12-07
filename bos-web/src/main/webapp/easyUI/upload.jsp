<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/7
  Time: 18:43
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ocupload-1.1.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<input type="button" id="mybutton" value="上传">
<script type="text/javascript">
    $(function () {
        $("#mybutton").upload({
            action:"xxx.action",
            name:"myFile"
        });
    });
</script>
</body>
</html>
