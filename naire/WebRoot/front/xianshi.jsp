<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    
    <title>学生页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<script src="/naire/admin/jquery-easyui-1.5.1/jquery.min.js"></script>   
    <link href="/naire/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
  </head>
  
  <body>
  <c:if test="${user == null}">
     <c:redirect url="/front/index.jsp"></c:redirect>
  </c:if>
  <h2  style="text-align:center">问卷列表</h2>
  <c:if test="${user != null}">
     <p>欢迎[<span style="color:red"><strong>${user.roles.rname}</strong></span>   <span style="color:red"><strong>${user.realname}</strong></span>]登录</p>
  </c:if>
  <table class="table table-hover table-bordered">
    <tr class="success">
      <td>问卷序号</td>
      <td>问卷标题</td>
      <td>发布日期</td>
      <td>调查对象</td>
      <td>操作</td>
    </tr>
    <c:forEach items="${list1}" var="u">
      <tr>
	      <td>${u.pid}</td>
	      <td>${u.title}</td>
	      <td>${u.pubdate}</td>
	      <td>${u.user.roles.rname} ${u.user.realname}</td>
	      <td>
	        <a href="/naire/paper/showOnePaperUI.do?pid=${u.pid}">我要打分</a>
	      </td>
    </tr>
    </c:forEach>
  </table>
  </body>
</html>








