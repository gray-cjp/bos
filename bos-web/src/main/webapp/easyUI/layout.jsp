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
<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>
    <div data-options="region:'west',title:'West',split:true" style="width:100px;">
        <div class="easyui-accordion" data-options="fit:true">
          <div title="one" data-options="iconCls:'icon-save',select:true">
              <ul id="ztree1" class="ztree"></ul>
              <script type="text/javascript">
                  $(function () {
                      var setting = {};
                      var zNodes = [
                          {"name":"one","children":[
                                  {"name":"one-1"},
                                  {"name":"one-2"},
                                  {"name":"one-3"},
                              ]},
                          {"name":"two"},
                          {"name":"three"}
                      ];
                      $.fn.zTree.init($("#ztree1"),setting,zNodes);
                  });
              </script>
          </div>
          <div title="two">
              <ul id="ztree2" class="ztree"></ul>
              <script type="text/javascript">
                  $(function () {
                      var setting1 = {
                          data:{
                              simpleData:{
                                  enable:true
                              }
                          }
                      };
                      var zNodes1 =[
                          {"id":"1","pId":"0","name":"two"},  //pId是固定关键字
                          {"id":"2","pId":"1","name":"two-1"},
                          {"id":"3","pId":"1","name":"two-2"},
                          {"id":"4","pId":"1","name":"two-3"}
                      ];
                      $.fn.zTree.init($("#ztree2"),setting1,zNodes1);
                  });
              </script>
          </div>
          <div title="three">
              <ul id="ztree3" class="ztree">
                  <script>
                      $(function () {
                          var setting3 ={
                              data:{
                                  simpleData:{
                                      enable:true
                                  }
                              }
                          };
                          $.ajax({
                              async:true,
                              type:"POST",
                              url:"${pageContext.request.contextPath}/json/menu.json",
                              success:function (data) {
                                  $.fn.zTree.init($("#ztree3"),setting3,data);
                              },
                              dataType:"json"
                          });
                      });
                  </script>
              </ul>
          </div>
          <div title="four" class="ztree">
              <ul id="ztree4" class="ztree"></ul>
              <script>
                  $(function () {
                      var setting4 = {
                          data:{
                              simpleData:{
                                  enable:true
                              }
                          },
                          callback:{
                              onClick:function (event,treeId,treeNode) {
                                  if (treeNode.page != undefined){
                                      var flag = $("#tt").tabs("exists",treeNode.name);
                                      if (flag) {
                                          $("#tt").tabs("select",treeNode.name);
                                      }else {
                                          $("#tt").tabs('add',{
                                              title:treeNode.name,
                                              context:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>',
                                              closeable:true
                                          })
                                      }
                                  }
                              }
                          }
                      };
                      $.ajax({
                          async:true,
                          type:"POST",
                          url:"${pageContext.request.contextPath}/json/menu.json",
                          success:function (data) {
                              $.fn.zTree.init($("#ztree4"),setting4,data);
                          }
                      });

                  });
              </script>
          </div>
        </div>
    </div>
    <div  data-options="region:'center',title:''" style="padding:5px;background:#eee;">
        <div id="tt" class="easyui-tabs" style="width:500px;height:250px;" data-options="fit:true">
        </div>
    </div>
</div>
</body>
</html>
