<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'roles.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
    <script src="/naire/admin/jquery-easyui-1.5.1/jquery.min.js"></script>
    <script src="/naire/admin/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
    <script src="/naire/admin/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
    <link href="/naire/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="/naire/admin/jquery-easyui-1.5.1/themes/default/easyui.css" />
    <link rel="stylesheet" href="/naire/admin/jquery-easyui-1.5.1/themes/icon.css"/>
  </head>
  <script>
    $(function(){
       $("#ta").datagrid({
           url:"/naire/paper/find.do",
           singleSelect:true,
           fitColumns:true,
           columns:[[
						{field:'pid',title:'编号',width:100},    
						{field:'title',title:'标题',width:100},
						{field:'pubdate',title:'发布时间',width:100},
						{
							field:'status',title:'状态',width:100,
							formatter:function(a){
							    return a==0?"已冻结":"已解冻";
							}
						},
						{
							  field:'user',title:'人物',width:100,align:'right',
								formatter:function(a){
								return a.realname;
						    }
						 }
                     ]],
                     toolbar:[
                              {
                                  text:"添加",
                                  iconCls:"icon-add",
                                  handler:function(){
                                      $("#d1").window("open");
                                  }
                                  },
                              {
                                   text:"删除",
                                   iconCls:"icon-remove",
                                   handler:function(){
                                      var row = $("#ta").datagrid("getSelected");
                                      if(row==null){
                                          $.messager.show({
                                              title:"删除通知",
                                              msg:"瓜子一样，必须选择一行！！"
                                              });
                                      }else{
                                          $.ajax({
                                              type:"post",
                                              url:"/naire/paper/delete.do",
                                              data:{pid:row.pid},
                                              success:function(){
                                                  //这里没有真的发回数据，只是给了个空的json，触发了回调函数success，
                                                  //但是如果真的发回了数据，直接在function（）括号中写发回的数据就接住了
                                                  $("#ta").datagrid("reload");
                                              }
                                              });
                                          }
                                      
                                      }   
                                   },
                              {
                                       text:"修改",
                                       iconCls:"icon-edit",
                                       handler:function(){
                                           var row = $("#ta").datagrid("getSelected");
                                           if(row==null){
                                                   $.messager.show({
                                                       title:"修改通知",
                                                       msg:"瓜子一样，必须选择一行！！"
                                                       });
                                                    }else{
                                                    	   $("#d2").window("open");
                                                           $("#d2 form").form("load",row);
                                                           }
                                                       }
                                       },
                                       {
                                           text:"冻结",
                                           iconCls:"icon-no",
                                           handler:function(){
                                           var row = $("#ta").datagrid("getSelected");
                                           if(row==null){
                                               $.messager.show({
                                                   title:"冻结通知",
                                                   msg:"瓜子一样，必须选择一行！！"
                                                   });
                                               }else{
                                                   $.ajax({
                                                       type:"post",
                                                       url:"/naire/paper/frozen.do",
                                                       data:{pid:row.pid},
                                                       success:function(){
                                                           $("#ta").datagrid("reload");
                                                           }
                                                       });
                                                   }
                                           }
                                           },
                                       {
                                        	   text:"解冻",
                                               iconCls:"icon-ok",
                                               handler:function(){
                                               var row = $("#ta").datagrid("getSelected");
                                               if(row==null){
                                                   $.messager.show({
                                                       title:"解冻通知",
                                                       msg:"瓜子一样，必须选择一行！！"
                                                       });
                                                   }else{
                                                       $.ajax({
                                                           type:"post",
                                                           url:"/naire/paper/active.do",
                                                           data:{pid:row.pid},
                                                           success:function(){
                                                               $("#ta").datagrid("reload");
                                                               }
                                                           });
                                                       }
                                               }
                                               },
                                               {
                                                   text:"分配问题",
                                                   iconCls:"icon-more",
                                                   handler:function(){
                                                       var row = $("#ta").datagrid("getSelected");
                                                       if(row==null){
                                                           $.messager.show({
                                                               title:"分配问题通知",
                                                               msg:"瓜子一样，必须选择一行！！"
                                                               });
                                                           }else{
                                                       $("#d3").window("open");
                                                       $("#d3 #1").html("");
                                                       $("#d3 #1").append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                                                       $("#d3 #1").append("问卷标题:");
                                                       $("#d3 #1").append(row.title);
                                                       $("#d3 #1").append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                                                       $("#d3 #1").append("调查对象:");
                                                       $("#d3 #1").append(row.user.realname);



                                                      
                                                       $.ajax({
                                                           type:"post",
                                                           url:"/naire/paper/addQuestionToPaperUI.do",
                                                           data:{pid:row.pid},
                                                           dataType:"json",
                                                           success:function(json){
                                                               $("#2,#3").html("");
                                                               //第二部分：查询关于选定的一个的信息，准备所有问题
                                                               for(var i=0;i<json.two.length;i++){
                                                                   var checkbox = $("<input name='qid' value='"+json.two[i].qid+"' type='checkbox'/>");
                                                                   $("#2").append(checkbox);
                                                                   $("#2").append(json.two[i].content);
                                                                   $("#2").append($("<br>"));
                                                                   }
                                                               //第三部分：准备所有问题
                                                               //这里的加号是起到了连接的作用
                                                               for(var i=0;i<json.three.length;i++){
                                                                   var checkbox = $("<input name='qid' value='"+json.three[i].qid+"' type='checkbox'/>");
                                                                   $("#3").append(checkbox);
                                                                   $("#3").append(json.three[i].content);
                                                                   $("#3").append($("<br>"));
                                                                   }
                                                               }
                                                           });
                                                           }
                                                   }
                                                 }
                             ]
           });
       $("#d1").window({
       	title:"添加问卷",
		width:240,
		height:200,
		closed:true,
		modal:true
           });
       $("#d2").window({
          	title:"修改问卷",
	   		width:240,
	   		height:200,
	   		closed:true,
	   		modal:true
              });
       $("#d3").window({
           title:"分配问题",
           width:400,
           height:500,
           closed:true,
           modal:true
           });
       $("#c1").combobox({
    	   width:220,
           url:"/naire/user/findAll.do",
           textField:"realname",
           valueField:"uid"
           });
       $("#c2").combobox({
    	   width:220,
           url:"/naire/user/findAll.do",
           textField:"realname",
           valueField:"uid"
           });
       $("#btn1").click(function(){
           $.ajax({
               type:"post",
               url:"/naire/paper/save.do",
               data:$("#d1 form").serialize(),
               success:function(){
                   $("#d1").window("close");
                   $("#d1 form").form("reset");
                   $("#ta").datagrid("reload");
                   }
               });
           });
       $("#btn2").click(function(){
           $.ajax({
               type:"post",
               url:"/naire/paper/update.do",
               data:$("#d2 form").serialize(),
               success:function(){
                   $("#d2").window("close");
                   $("#d2 form").form("reset");
                   $("#ta").datagrid("reload");
                   }
               });
           });
       $("#btn3").click(function(){
           var row = $("#ta").datagrid("getSelected");
           $.ajax({
               type:"post",
               url:"/naire/paper/addQuestionToPaper.do",
               data:$("#qForm").serialize()+"&"+"pid="+row.pid,
               success:function(){
                   $("#d3").window("close");
                   }
               });
           });
       $("#btn4").click(function(){
           var row = $("#ta").datagrid("getSelected");
           $.ajax({
               type:"post",
               url:"/naire/paper/removeQuestionFromPaper.do",
               data:$("#qForm1").serialize()+"&"+"pid="+row.pid,
               success:function(){
                   $("#d3").window("close");
                   }
               });
           });
       });
  </script>
  <body>
    <div id="d1">
      <form>
			  <fieldset>
			          <h4 class="form-signin-heading">问卷</h4>
			          <input name="title" type="text" placeholder="title"><br/>
			          <input name="user.uid" type="text" id="c1"><br/>
			         <button id="btn1" type="button" class="btn btn-success">提交</button>
			  </fieldset>
        </form>
    </div>
    <div id="d2">
      <form>
			  <fieldset>
			          <input name="pid" type="hidden">
			          <h4 class="form-signin-heading">问卷</h4>
			          <input name="title" type="text" placeholder="title"><br/>
			          <input name="user.uid" type="text" id="c2"><br/>
			         <button id="btn2" type="button" class="btn btn-success">提交</button>
			  </fieldset>
        </form>
    </div>
    <div id="d3">
      <div id="1"></div>
      <hr/>
      <form id="qForm1">
      <div id="2"></div>
      </form>
      <button id="btn4" type="button" class="btn btn-success">移除</button>
      <hr/>
      <form id="qForm">
        <div id="3"></div>
      </form>
      <div>
        <button id="btn3" type="button" class="btn btn-success">分配</button>
      </div>
    </div>
    <table id="ta"></table>
  </body>
</html>
