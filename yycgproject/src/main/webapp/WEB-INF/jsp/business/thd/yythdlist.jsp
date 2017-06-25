<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>医院退货单查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">


function yythdinfo(bm,id,year){
	var sendUrl = "${baseurl}/thd/yythdinfo.action?yythdid="+id;
	parent.opentabwindow(bm+'退货单查看',sendUrl);
}


//工具栏

var toolbar = [];

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
	field : 'thdztmc',
	title : '退货单<br>状态', 
	width : 100
},{
	field : 'opt3',
	title : '查看',
	width : 60,
	formatter:function(value, row, index){
		var date = new Date(row.cjtime);
		var y = date.getFullYear();
		return '<a href=javascript:yythdinfo(\''+row.bm+'\',\''+row.id+'\','+y+')>查看</a>';
	}
}]];

function initGrid(){
	$('#yythdlist').datagrid({
		title : '退货单列表',
		//nowrap : false,
		striped : true,
		//collapsible : true,
		url : '${baseurl}/thd/yythdlist_result.action',
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
		});
</script>
</HEAD>
<BODY>
    <form id="yythdqueryForm" name="yythdqueryForm" method="post" >
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
							已提交至供货商
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


</BODY>
</HTML>

