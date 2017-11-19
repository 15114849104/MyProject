<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
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
            url:"/naire/user/find.do",
            singleSelect:true,
            fitColumns:true,
            pagination:true,
			pageSize:[10,20,30,40],
			pageSize:10,
            columns:[[    
			          {field:'uid',title:'编号',width:100},    
			          {field:'account',title:'账号',width:100},
			          {field:'password',title:'密码',width:100},
			          {field:'realname',title:'真实名字',width:100},
			          {
			        	  field:'roles',title:'所属角色',width:100,align:'right',
							formatter:function(a){
							return a.rname;
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
                                     title:"删除通知！",
                                     msg:"瓜子一样，必须选择一行！"
                                     });
                                 }else{
                                     $.ajax({
                                         type:"post",
                                         url:"/naire/user/delete.do",
                                         data:{uid:row.uid},
                                         success:function(){
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
                                        title:"修改通知！",
                                        msg:"瓜子一样，必须选一行！"
                                        });
                                    }else{
                                        $("#d2").window("open");
                                        $("#d2 form").form("load",row);                                        }
                              }   
                         }
            ]
         });

        $("#d1").window({
        	title:"添加用户",
			width:240,
			height:280,
			closed:true,
			modal:true
            });
        $("#d2").window({
        	title:"修改用户",
			width:240,
			height:280,
			closed:true,
			modal:true
            });
        $("#c1").combobox({
            width:220,
            url:"/naire/roles/findAll.do",
            textField:"rname",
            valueField:"rid"
            });
        $("#c2").combobox({
            width:220,
            url:"/naire/roles/findAll.do",
            textField:"rname",
            valueField:"rid"
            });
        $("#btn1").click(function(){
            $.ajax({
            	type:"post",
                url:"/naire/user/save.do",
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
                url:"/naire/user/update.do",
                data:$("#d2 form").serialize(),
                success:function(){
                    $("#d2").window("close");
                    $("#d2 form").form("reset");
                    $("#ta").datagrid("reload");
                }    
                });
            });
     });
  </script>
  <body>
    <div id="d2">
      <form>
			  <fieldset>
			          <input name="uid" type="hidden">
			          <h4 class="form-signin-heading">用户</h4>
			          <input name="account" type="text" placeholder="user"><br/>
			          <input name="password" type="text" placeholder="password"><br/>
			          <input name="realname" type="text" placeholder="realname"><br/>
			          <input name="roles.rid" type="text" id="c2"><br/>
			         <button id="btn2" type="button" class="btn btn-success">提交</button>
			  </fieldset>
        </form>
    </div>
    
    <div id="d1">
      <form>
			  <fieldset>
			          <h4 class="form-signin-heading">用户</h4>
			          <input name="account" type="text" placeholder="user"><br/>
			          <input name="password" type="text" placeholder="password"><br/>
			          <input name="realname" type="text" placeholder="realname"><br/>
			          <input name="roles.rid" type="text" id="c1"><br/>
			         <button id="btn1" type="button" class="btn btn-success">提交</button>
			  </fieldset>
        </form>
    </div>
    <table id="ta"></table>
  </body>
</html>





