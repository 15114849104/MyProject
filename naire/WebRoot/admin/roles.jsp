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
    <script>
		$(function() {
			$("#tt").datagrid({
				url:"/naire/roles/find.do",
				fitColumns:true,
				singleSelect:true,
				pagination:true,
				pageSize:[10,20,30,40],
				pageSize:10,
				columns:[[    
				          {field:'rid',title:'编号',width:100},    
				          {field:'rname',title:'角色名',width:100},    
				]],
				toolbar:[
					{
						text:"添加",
						iconCls:'icon-add',
						handler:function() {
							$("#d1").window('open');
						}
					},
					{
						text:"删除",
						iconCls:'icon-remove',
						handler:function() {
							// 获取选中的行
							var row = $("#tt").datagrid("getSelected");
							if(row == null) {
								$.messager.show({
									title:'删除角色',
									msg:'必须选择一行!!'
								});
							} else {
								$.ajax({
									type:"post",
									url:"/naire/roles/delete.do",
									data:{rid:row.rid},
									success:function() {
										$("#tt").datagrid('reload');
									}
								});
							}
						
						}
					},
					{
						text:"修改",
						iconCls:"icon-edit",
						handler:function(){
                            //获取选中行
                            var row = $("#tt").datagrid("getSelected");
                            if(row==null){
                                 $.messager.show({
                                     title:"修改通知",
                                     msg:"请选择要修改的角色！！！"
                                     });
                            }else{
                                $("#d2").window("open");

                                $("#d2 form").form("load",row);
                                
                            }
						}
					}
				]
				  				
			});

			$("#d1").window({
				title:"添加角色",
				width:240,
				height:170,
				closed:true,
				modal:true
			});

			$("#d2").window({
				title:"修改角色",
				width:240,
				height:170,
				closed:true,
				modal:true
				});

			$("#btn0").click(function(){
				$.ajax({
					type:"post",
					url:"/naire/roles/save.do",
					data:$("#d1 form").serialize(),
					success:function() {
						$("#d1").window('close');
						$("#d1 form").form('reset');
						$("#tt").datagrid('reload');
					}
				});
			});

			$("#btn1").click(function(){
				$.ajax({
					type:"post",
					url:"/naire/roles/update.do",
					data:$("#d2 form").serialize(),
					success:function(){
					    $("#d2").window("close");
					    $("#d1 form").form('reset');
					    $("#tt").datagrid("reload");	
					}
			    });
				
			});


		});
	</script>
  </head>
  
  <body>
  	<table id="tt">
  	</table>
  	<div id="d1">
  	    <form>
			  <fieldset>
			          <h4 class="form-signin-heading">角色名</h4>
			          <input name="rname" type="text" placeholder="roles"><br/>
			         <button id="btn0" type="button" class="btn btn-success">提交</button>
			  </fieldset>
        </form>
  	</div>
  	<div id="d2">
  	    <form>
			  <fieldset>
			          <input name=rid type="hidden">
			          <h4 class="form-signin-heading">角色名</h4>
			          <input name="rname" type="text" placeholder="roles"><br/>
			         <button id="btn1" type="button" class="btn btn-success">提交</button>
			  </fieldset>
        </form>
  	</div>
  </body>
</html>
