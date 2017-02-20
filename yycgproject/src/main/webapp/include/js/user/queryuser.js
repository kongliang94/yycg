/**
 * 
 */
//datagrid列定义
	var columns_v = [ [ {
		field : 'id',//对应json中的key
		title : 'id',
		width : 120
	},{
		field : 'userid',//对应json中的key
		title : '账号',
		width : 120
	}, {
		field : 'username',//对应json中的key
		title : '名称 ',
		width : 180
	}, {
		field : 'groupid',//对应json中的key
		title : '用户类型',
		width : 120,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			if(value =='1'){
				return "卫生局";
			}else if(value =='2'){
				return "卫生院";
			}else if(value =='3'){
				return "卫生室";
			}else if(value =='4'){
				return "供货商";
			}else if(value =='0'){
				return "系统管理员";
			}
		}

	}, {
		field : 'sysmc',//对应json中的key
		title : '所属单位',
		width : 120
	}, {
		field : 'userstate',//对应json中的key
		title : '状态',
		width : 120,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			if(value =='1'){
				return "正常";
			}else if(value =='0'){
				return "暂停";
			}
		}
	},{
		field : 'deleteopt',
		title : '操作',
		width : 120,
		formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
			return "删除";
		}
	} ] ];

	//定义 datagird工具
	var toolbar_v = [ {//工具栏
		id : 'btnadd',
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			//打开一个窗口，用户添加页面
			//参数：窗口的title、宽、高、url地址
			createmodalwindow("添加用户信息", 800, 300, 'addsysuser.action');
		}
	} ];

	//加载datagrid

	$(function() {
		$('#sysuserlist').datagrid({
			title : '用户查询',//数据列表标题
			nowrap : true,//单元格中的数据不换行，如果为true表示不换行，不换行情况下数据加载性能高，如果为false就是换行，换行数据加载性能不高
			striped : true,//条纹显示效果
			url : 'queryuser_result.action',//加载数据的连接，引连接请求过来是json数据
			idField : 'id',//此字段很重要，数据结果集的唯一约束(重要)，如果写错影响 获取当前选中行的方法执行
			loadMsg : '',
			columns : columns_v,
			pagination : true,//是否显示分页
			rownumbers : true,//是否显示行号
			pageList:[15,30,50],
			toolbar : toolbar_v,
			width:function(){return document.body.clientWidth*0.9;},
	        height:400
		});
	});
	
	//查询方法
	function queryuser(){
		//datagrid的方法load方法要求传入json数据，最终将 json转成key/value数据传入action
		//将form表单数据提取出来，组成一个json
		var formdata = $("#sysuserqueryForm").serializeJson();
		$('#sysuserlist').datagrid('load',formdata);
	}
	//保存方法
	function sysusersave(){
		//准备使用jquery 提供的ajax Form提交方式
		  //将form的id传入，方法自动将form中的数据组成成key/value数据，通过ajax提交，提交方法类型为form中定义的method，
		  //使用ajax form提交时，不用指定url，url就是form中定义的action
		  //此种方式和原始的post方式差不多，只不过使用了ajax方式
		  
		  //第一个参数：form的id
		  //第二个参数：sysusersave_callback是回调函数，sysusersave_callback当成一个方法的指针
		  //第三个参数：传入的参数， 可以为空
		  //第四个参数：dataType预期服务器返回的数据类型,这里action返回json
		  //根据form的id找到该form的action地址
		  jquerySubByFId('userform',sysusersave_callback,null,"json");	  
	}
	function sysusersave_callback(data){
		message_alert(data);//被封装在common_js.js中
	}
	
	
	
	
//	/**
//	 * 新增用户之前的验证
//	 */
//	function sysusersave(){
//		var userid = $("#sysuser_userid").val() ;
//		var username = $("#sysuser_username").val() ;
//		var password = $("#sysuser_password").val() ;
//		var groupid = $("#sysuser_groupid").val() ;		
//		if(username =="" || userid == "" || groupid =="" || password == ""){
//			$("#sysuser_groupidTip").html("请将数据填写完整");
//			return ;
//		}else{
//			$("#sysuser_groupidTip").html("");
//		}
//		
//		var json = {
//			"username": username,
//			"userid":userid,
//			"groupid":groupid,
//			"password":password,
//		};
//
//	    $.ajax({            
//	        type:"POST",   //post提交方式默认是get
//	        url:"addsysusersubmit.action", 
//	        data:json, 
//	        error:function(request) {      // 设置表单提交出错                 
//	            $("#sysuser_groupidTip").html(request);  //登录错误提示信息
//	        },
//	        success:function(data) {
//	        	  if(data=="false"){
//	        	  	  $("#sysuser_groupidTip").html("系统错误");
//	        	  	  return ;
//	        	  }else{
//	        		  	
//	        			$.messager.show( {
//	        				msg : '新增成功',
//	        				title : '提示'
//	        			});
//	        	  }
//	        }            
//	  }); 
//	}
