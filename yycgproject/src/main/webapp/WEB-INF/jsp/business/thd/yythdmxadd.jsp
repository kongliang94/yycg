<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>退货药品添加</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">

var yythdmxaddsubmit = function(){
	_confirm('您确定要退货选择的药品吗?',null,
	  function(){
				
		var indexs = [];//删除记录的序号
		var rows = $('#yycgdmxlist').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			var index=$('#yycgdmxlist').datagrid('getRowIndex',rows[i]);
			indexs.push(index);
		}
		if(rows.length>0){
			$("#indexs").val(indexs.join(','));
			//alert($("#indexs").val());
			jquerySubByFId('yycgdmxForm', yythdmxaddsubmit_callback, null);
		}else{
			alert_warn("请选择要退货的药品");
		}
	  }
	)
	
};

function yythdmxaddsubmit_callback(data) {
	var result = getCallbackData(data);
	_alert(result);
	yycgdmxquery();//提交完成刷新本页面
}



//工具栏
var toolbar = [{
	id : 'yythdmxadd',
	text : '确认添加',
	iconCls : 'icon-add',
	handler : yythdmxaddsubmit
	}];

var frozenColumns;

var columns = [ [{
	checkbox:true
},{
	field : 'id',
	hidden : true,
	formatter: function(value,row,index){
		return '<input type="hidden" name="yythdmxs['+index+'].ypxxid" value="'+value+'" />';
	}
},{
	field : 'yycgdid',
	hidden : true,
	formatter: function(value,row,index){
		return '<input type="hidden" name="yythdmxs['+index+'].yycgdid" value="'+value+'" />';
	}
},{
	field : 'yycgdbm',
	title : '采购单号',
	width : 70
},{
	field : 'usergysmc',
	title : '供货商',
	width : 100
},{
	field : 'bm',
	title : '流水号',
	width : 50
},{
	field : 'mc',
	title : '通用名',
	width : 80
},{
	field : 'jx',
	title : '剂型',
	width : 70
},{
	field : 'gg',
	title : '规格',
	width : 70
},{
	field : 'zhxs',
	title : '转换系数',
	width : 50
},{
	field : 'jyjg',
	title : '交易价',
	width : 50
},{
	field : 'cgl',
	title : '采购量',
	width : 50
},{
	field : 'cgje',
	title : '采购金额',
	width : 50
},{
	field : 'rkl',
	title : '入库量',
	width : 50
},{
	field : 'rkje',
	title : '入库金额',
	width : 60
},{
	field : 'thl',
	title : '退货量',
	width : 50,
	formatter:function(value,row,index){
		   return '<input type="text" name="yythdmxs['+index+'].thl" />';

	}
},{
	field : 'thyy',
	title : '退货原因',
	width : 70,
	formatter:function(value,row,index){
		 return '<input type="text" name="yythdmxs['+index+'].thyy" />';
	}
}
,{
	field : 'rkdh',
	title : '发票号或入库单号',
	width : 90
},{
	field : 'ypph',
	title : '药品批号',
	width : 70
},{
	field : 'ypyxq',
	title : '药品有效期(年)',
	width : 70
},{
	field : 'cgztmc',
	title : '采购状态', 
	width : 60
}]];

function initGrid(){
	$('#yycgdmxlist').datagrid({
		title : '采购单列表',
		//nowrap : false,
		striped : true,
		//collapsible : true,
		url : '${baseurl}thd/yythdmxadd_result.action',
		queryParams:{
			year:'${year}',
			yythdid:'${yythdid}'
		},
		//sortName : 'code',
		//sortOrder : 'desc',
		//remoteSort : false,
		//idField : 'id',
		//frozenColumns : frozenColumns,
		autoRowHeight:true,
		columns : columns,
		pagination : true,
		rownumbers : true,
		toolbar : toolbar,
		loadMsg:"",
		pageList:[15,30,50,100],
		onClickRow : function(index, field, value) {
					$('#yycgdmxlist').datagrid('unselectRow', index);
				}
		});

	}
	$(function() {
		initGrid();

	});

	function yycgdmxquery() {
		
		var formdata = $("#yycgdmxForm").serializeJson();
		//alert(formdata);
		$('#yycgdmxlist').datagrid('unselectAll');
		$('#yycgdmxlist').datagrid('load', formdata);
	}
</script>
</HEAD>
<BODY>

 <form id="yycgdmxForm" name="yycgdmxForm" action="${baseurl}thd/yythdmxaddsubmit.action" method="post">
	<input type="hidden" name="indexs" id="indexs" />
	<input type="hidden" name="yythdid" value="${yythdid}" />
	<input type="hidden" name="year" value="${year}" />
			<TABLE  class="table_search">
				<TBODY>
				<TR>
					<TD class="left">年份(如2014)：</TD>
					<td>${year}</td>
					
					<TD class="left">采购单编号：</td>
					<td><INPUT type="text" name="yycgdCustom.bm" /></TD>
					<TD class="left">采购单名称：</TD>
					<td><INPUT type="text" name="yycgdCustom.mc" /></td>
					<TD class="left">采购时间：</TD>
					<td><INPUT id="yycgdCustom.kscgdate"
						name="yycgdCustom.kscgdate"
						onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						style="width: 80px" />-- <INPUT id="yycgdCustom.jscgdate"
						name="yycgdCustom.jscgdate"
						onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						style="width: 80px" /></td>
				</TR>
				
				<TR>
					<TD class="left">流水号：</TD>
					<td><INPUT type="text" name="ypxxCustom.bm" /></td>
					<TD class="left">通用名：</td>
					<td><INPUT type="text" name="ypxxCustom.mc" /></TD>
					 <TD class="left">入库时间：</TD>
					<td><INPUT id="yycgdCustom.startrktime"
						name="yycgdCustom.startrktime"
						onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						style="width: 80px" />-- <INPUT id="yycgdCustom.endrktime"
						name="yycgdCustom.endrktime"
						onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
						style="width: 80px" /></td>
					<TD class="left">药品批号：</td>
					<td><INPUT type="text" name="yycgdCustom.ypph" /></TD>
				</tr>
				<tr>

					<TD class="left">药品有效期(年)：</td>
					<td><INPUT type="text" name="yycgdCustom.ypyxq" /></TD>
					<TD class="left">发票号或入库单号：</td>
					<td><INPUT type="text" name="yycgdCustom.rkdh" /></TD>
					<TD class="left">采购状态：</TD>
					<td>已入库</td>
					<td ></TD>
					<td colspan=2><a id="btn" href="#" onclick="yycgdmxquery()"
						class="easyui-linkbutton" iconCls='icon-search'>查询</a></td>
				</tr>
			
			</TBODY>
			</TABLE>
	   
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
			<TBODY>
				<TR>
					<TD>
						<table id="yycgdmxlist"></table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</form>

</BODY>
</HTML>

