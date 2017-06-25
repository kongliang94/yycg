<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>医院退货单维护</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">

var yythddelete = function(thdid){
	_confirm('您确定要删除该退货单吗?',null,
	  function(){
		if(thdid){
			$("#thddeleteid").val(thdid);
			jquerySubByFId('yythddeleteForm', yythddelete_callback, null);
		}else{
			
			alert_warn("请选择要删除的退货单");
		}
		
	  }
	)
	
};

function yythddelete_callback(data) {
	var result = getCallbackData(data);
	_alert(result);
	yythdquery();
}


function yythdinfo(id){
	var sendUrl = "${baseurl}/thd/yythdinfo.action?yythdid="+id;
	parent.opentabwindow(bm+'退货单查看',sendUrl);
}

function yythdedit(id,bm){
	var sendUrl = "${baseurl}/thd/yythdedit.action?yythdid="+id+'&year=${year}';
	//window.location=sendUrl;
	parent.opentabwindow(bm+'退货单修改',sendUrl);
}
function yythdadd(id){
	var sendUrl = "${baseurl}/thd/yythdedit.action";
	parent.opentabwindow('退货单创建',sendUrl);
}
//工具栏

var toolbar = [{
	id : 'yythdadd',
	text : '创建退货单',
	iconCls : 'icon-add',
	handler : yythdadd
	}];

var frozenColumns;

var columns = [ [
 {
	field : 'useryymc',
	title : '医院名称',
	width : 100
},{
	field : 'bm',
	title : '退货单编号',
	width : 80
},{
	field : 'mc',
	title : '退货单名称',
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
	field : 'thdztmc',
	title : '退货单<br>状态', 
	width : 60
},{
	field : 'opt4',
	title : '修改',
	width : 60,
	formatter:function(value, row, index){
		return '<a href=javascript:yythdedit(\''+row.id+'\',\''+row.bm+'\')>修改</a>';
	}
},{
	field : 'opt5',
	title : '删除',
	width : 60,
	formatter:function(value, row, index){
		return '<a href=javascript:yythddelete(\''+row.id+'\')>删除</a>';
	}
}]];

function initGrid(){
	$('#yythdlist').datagrid({
		title : '退货单列表',
		//nowrap : false,
		striped : true,
		//collapsible : true,
		url : '${baseurl}thd/yythdmanager_result.action',
		queryParams:{
			year:'${year}'
		},
		//sortName : 'code',
		//sortOrder : 'desc',
		//remoteSort : false,
		idField : 'id',//退货单id
		//frozenColumns : frozenColumns,
		columns : columns,
		autoRowHeight:true,
		pagination : true,
		rownumbers : true,
		toolbar : toolbar,
		loadMsg:"",
		pageList:[15,30,50,100],
		onClickRow : function(index, field, value) {
					$('#yythdlist').datagrid('unselectRow', index);
				}
		});

	}
	$(function() {
		initGrid();
		
	});

	function yythdquery() {
		var formdata = $("#yythdqueryForm").serializeJson();
		//alert(formdata);
		$('#yythdlist').datagrid('unselectAll');
		$('#yythdlist').datagrid('load', formdata);
	}
	$(function(){ 
			//加载年
			businessyearlist('businessyear');
			//加载采购状态
			//getDictinfoCodelist('013','yycgdCustom.thzt');
		});
</script>
</HEAD>
<BODY>
    <form id="yythdqueryForm" name="yythdqueryForm" method="post">
			<input type="hidden" name="indexs" id="indexs" />
			<TABLE  class="table_search">
				<TBODY>
					<TR>
						<TD class="left">年份(如2014)：</TD>
						<td >
						<select name="year" id="businessyear">
						</select>
						</td>
						<TD class="left">医院名称：</TD>
						<td ><INPUT type="text" name="useryyCustom.mc" /></td>
						<TD class="left">退货单编号：</td>
						<td><INPUT type="text"  name="yycgdCustom.yythdbm" /></TD>
						
					</TR>
					<TR> 
					<TD class="left">退货单名称：</TD>
						<td ><INPUT type="text" name="yycgdCustom.yythdmc" /></td>
					 
						
						<TD class="left">退货时间：</TD>
						<td >
						 <INPUT id="yycgdCustom.ksthdate"
							name="yycgdCustom.ksthdate" 
							 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" style="width:80px"/>--
					 <INPUT id="yycgdCustom.jsthdate" 
							name="yycgdCustom.jsthdate"
							 onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" style="width:80px"/>
							
						</td>
						 <TD class="left">退货单状态：</TD>
						<td >
							未提交
							<a id="btn" href="#" onclick="yythdquery()" class="easyui-linkbutton" iconCls='icon-search'>查询</a>
						</td>
						
					</tr>
					
				</TBODY>
			</TABLE>
	   
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
			<TBODY>
				<TR>
					<TD>
						<table id="yythdlist"></table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		 </form>

<form id="yythddeleteForm" name="yythddeleteForm" action="${baseurl}/thd/yythddelete.action">
<input type="hidden" id="thddeleteid" name="thddeleteid" />
<input type="hidden" id="year" name="year" value="${year}" />
</form>

</BODY>
</HTML>

