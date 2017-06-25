<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>医院采购单维护</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">

var yycgddelete = function(cgdid){
	_confirm('您确定要删除该采购单吗?',null,
	  function(){
		if(cgdid){
			$("#cgddeleteid").val(cgdid);
			jquerySubByFId('yycgddeleteForm', yycgddelete_callback, null);
		}else{
			
			alert_warn("请选择要删除的采购单");
		}
		
	  }
	)
	
};

function yycgddelete_callback(data) {
	var result = getCallbackData(data);
	_alert(result);
	yycgdquery();
}


function yycgdinfo(id){
	var sendUrl = "${baseurl}cgd/yycgdinfo.action?yycgdid="+id;
	parent.opentabwindow(bm+'采购单查看',sendUrl);
}
function yycgdedit(bm){
	var sendUrl = "${baseurl}cgd/yycgdedit.action?id="+bm;
	parent.opentabwindow(bm+'采购单修改',sendUrl);//打开一个新标签
}
function yycgdadd(id){
	var sendUrl = "${baseurl}cgd/yycgdedit.action";
	parent.opentabwindow('采购单创建',sendUrl);
}
//工具栏

var toolbar = [{
	id : 'yycgdadd',
	text : '创建采购单',
	iconCls : 'icon-add',
	handler : yycgdadd
	}];

var frozenColumns;

var columns = [ [
 {
	field : 'useryymc',
	title : '医院名称',
	width : 100
},{
	field : 'bm',
	title : '采购单编号',
	width : 80
},{
	field : 'mc',
	title : '采购单名称',
	width : 150
},{
	field : 'cjtime',
	title : '建单时间',
	width : 80,
	formatter: function(value,row,index){
		if(value){
			try{
			var date = new Date(value);
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+"-"+m+"-"+d;
			}catch(e){
				alert(e);
			}
		}
		
	}
},{
	field : 'xgtime',
	title : '修改时间',
	width : 80,
	formatter: function(value,row,index){
		if(value){
			try{
			var date = new Date(value);
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+"-"+m+"-"+d;
			}catch(e){
				alert(e);
			}
		}
		
	}
},{
	field : 'tjtime',
	title : '提交时间',
	width : 80,
	formatter: function(value,row,index){
		if(value){
			try{
			var date = new Date(value);
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+"-"+m+"-"+d;
			}catch(e){
				alert(e);
			}
		}
		
	}
},{
	field : 'yycgdztmc',
	title : '采购单<br>状态', 
	width : 60
},{
	field : 'opt4',
	title : '修改',
	width : 60,
	formatter:function(value, row, index){
		return '<a href=javascript:yycgdedit(\''+row.bm+'\')>修改</a>';
	}
},{
	field : 'opt5',
	title : '删除',
	width : 60,
	formatter:function(value, row, index){
		return '<a href=javascript:yycgddelete(\''+row.id+'\')>删除</a>';
	}
}]];

function initGrid(){
	$('#yycgdlist').datagrid({
		title : '采购单列表',
		//nowrap : false,
		striped : true,
		//collapsible : true,
		url : '${baseurl}cgd/yycgdmanager_result.action',
		queryParams:{
			year:'${year}'
		},
		//sortName : 'code',
		//sortOrder : 'desc',
		//remoteSort : false,
		idField : 'id',//采购单id
		//frozenColumns : frozenColumns,
		columns : columns,
		autoRowHeight:true,
		pagination : true,
		rownumbers : true,
		toolbar : toolbar,
		loadMsg:"",
		pageList:[15,30,50,100],
		onClickRow : function(index, field, value) {
					$('#yycgdlist').datagrid('unselectRow', index);
				}
		});
		/* onLoadSuccess: function(data){
			if (data.resultInfo.type==TYPE_RESULT_FAIL){
				var result = getCallbackData(data);
				_alert(result);
			} 
		}

		}); */

	}
	$(function() {
		initGrid();
		
	});

	function yycgdquery() {
		var formdata = $("#yycgdqueryForm").serializeJson();
		//alert(formdata);
		$('#yycgdlist').datagrid('unselectAll');
		$('#yycgdlist').datagrid('load', formdata);
	}
	$(function(){
		//加载年
		businessyearlist('businessyear');
		//加载采购状态
		getDictinfoCodelist('010','yycgdCustom_zt');
	});
</script>
</HEAD>
<BODY>
    <form id="yycgdqueryForm" name="yycgdqueryForm" method="post">
			<input type="hidden" name="indexs" id="indexs" />
			<TABLE  class="table_search">
				<TBODY>
					<TR>
						<TD class="left">年度：</td>
						<td><select id="businessyear" name="year" ></select></TD>
						<TD class="left">采购单编号：</td>
						<td><INPUT type="text"  name="yycgdCustom.bm" /></TD>
						<TD class="left">采购单名称：</TD>
						<td ><INPUT type="text" name="yycgdCustom.mc" /></td>
						
						
					</TR>
					<TR> 
					   <TD class="left">采购单状态：</TD>
						<td >
							<select id="yycgdCustom_zt" name="yycgdCustom.zt" style="width:150px">
								<option value="">全部</option>
								<%-- <c:forEach items="${cgdztList}" var="value">
									<option value="${value.dictcode}">${value.info}</option>
								</c:forEach> --%>
							</select>
						</td>
						<TD class="left">医院名称：</TD>
						<td ><INPUT type="text" name="yycgdCustom.useryymc" /></td>
						<TD class="left">采购时间：</TD>
						<td >
						 <INPUT id="yycgdCustom.kscgdate"
							name="yycgdCustom.kscgdate" 
							 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" style="width:80px"/>--
					 <INPUT id="yycgdCustom.jscgdate" 
							name="yycgdCustom.jscgdate"
							 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" style="width:80px"/>
							
						</td>
						<TD colspan=2>
							<a id="btn" href="#" onclick="yycgdquery()" class="easyui-linkbutton" iconCls='icon-search'>查询</a>
						</TD>
						
					</tr>
					
				</TBODY>
			</TABLE>
	   
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
			<TBODY>
				<TR>
					<TD>
						<table id="yycgdlist"></table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		 </form>

<form id="yycgddeleteForm" name="yycgddeleteForm" action="${baseurl}cgd/yycgddelete.action">
<input type="hidden" id="cgddeleteid" name="cgddeleteid" />
</form>

</BODY>
</HTML>

