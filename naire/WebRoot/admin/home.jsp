<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'home.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <script src="/naire/admin/jquery-easyui-1.5.1/jquery.min.js"></script>
    <script src="/naire/admin/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
    <script src="/naire/admin/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="/naire/admin/jquery-easyui-1.5.1/themes/default/easyui.css" />
    <link rel="stylesheet" href="/naire/admin/jquery-easyui-1.5.1/themes/icon.css"/>
    <script>
       $(function(){
           $("a[title]").click(function(){
               
               var src = $(this).attr("title");//jQuer里面atter是取属性的，这里取得title属性
               var title = $(this).html();//指的是标签之间的内容
               if($("#tt").tabs("exists",title)){
                   $("#tt").tabs("select",title);
                   }else{
                 $("#tt").tabs("add",{
                     title:title,
                     closable:true,
                     content:"<iframe width='99%' height='98%' src='"+src+"'></iframe>"
                     });
                       }
               });
           });
    </script>
    
    
    
  </head>
  
  <body class="easyui-layout">   
    <div data-options="region:'west',title:'菜单',split:true" style="width:150px;">
			<ul class="easyui-tree">   
			    <li>   
			        <span>数据管理</span>
			        <ul>
			           <li><a title="roles.jsp">角色管理</a></li>
			           <li><a title="question.jsp">问题管理</a></li>
			           <li><a title="user.jsp">用户管理</a></li>
			           <li><a title="paper.jsp">问卷管理</a></li>
			           <li><a title="score.jsp">分数管理</a></li>
			        </ul>     
			    </li>   
			    
			</ul>  
    </div>   
    <div data-options="region:'center',title:'中部'" style="padding:5px;background:#eee;">
        <div id="tt" class="easyui-tabs" style="width:100%;height:100%;">   
        </div>   
</div>  
        
        
        
    </div>   
</body>  

</html>
