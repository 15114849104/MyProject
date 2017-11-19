<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>问卷页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <script src="/naire/admin/jquery-easyui-1.5.1/jquery.min.js"></script>   
    <link href="/naire/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script type="text/javascript">
       window.onload=function(){
           var aa = document.getElementsByClassName("aa");

           for(var i = 0;i<aa.length;i++){
               aa[i].oninput=function(){
                   //this代表当前对象，在这里也就是aa
                   if(isNaN(this.value) || this.value.indexOf('.') != -1){
                       this.value = "";
                       }else{
                           //从上面下来说明已经是有效的数字了
                           if(this.value < 0 || this.value > 10) {
							this.value = "";
                               }
                           }
                   }
               }
 
           }
    </script>
  </head>
  
  <body>
    <div>
      <h3 style="text-align:center">标题: ${p.title}</h3>
    </div>
    <div>
      <h4 style="text-align:right">----------${p.user.realname}</h4>
    </div>
    <div>
	   <form action="/naire/score/save.do" method="post">
	   <input type="hidden" name="pid" value="${p.pid}" />  
	     <table class="table table-hover">
	      <tr class="success">
	        <td>题号</td>
	        <td>问题</td>
	        <td>打分</td>
	      </tr>
	      <c:forEach items="${p.questions}" var="q">
	        <tr>
		        <td>${q.qid}</td>
		        <td>${q.content}</td>
		        <td>
		          <input type="hidden" name="qid" value="${q.qid}"/>
		          <input class="aa" name="score" size="2"/>
		        </td>
	        </tr>
	       </c:forEach>
	      </table>
	      <button class="btn btn-large btn-dangerous" type="submit">提交</button>
	    </form>  
    </div>
    <div style="text-align:center">
      <button class="btn btn-large btn-dangerous" type="button" onclick="history.back()">后退</button>
    </div>
  </body>
</html>
