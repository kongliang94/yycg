<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>医院药品目录添加查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">

var yyypmladd = function(){
	_confirm('您确定要將选择药品添加至药品目录吗?',null,
	  function(){
		var indexs = [];
		var ypxxids =[];
		var rows = dataGrid_obj.datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			indexs.push(dataGrid_obj.datagrid('getRowIndex',rows[i]));
		}
		if(rows.length>0){
			$("#indexs").val(indexs.join(','));
			/* $("input[name^=ypxxid]").each(function() {
				ypxxids.push($(this).val());
				    				}); */
			jquerySubByFId('yyypmladdqueryForm',yyypmladd_callback, null);
		}else{
			alert_warn("请选择要添加的药品");
		}
		
	  }
	)
	
};

function yyypmladd_callback(data) {
	var result = getCallbackData(data);
	_alert(result);
	yyypmladdquery();
}


//工具栏

var toolbar = [ {
	id : 'yyypmladd',
	text : '确认添加',
	iconCls : 'icon-add',
	handler : yyypmladd
	}];

var frozenColumns;

var columns = [ [{
	field : 'check',
	title : '选择',
	checkbox : true
},{
	field : 'ypxxid',
	hidden : true,
	formatter: function(value,row,index){
		return '<input type="hidden" name="yyypmls['+index+'].ypxxid" value="'+row.ypxxid+'" />';
	}
},{
	field : 'usergysid',
	hidden : true,
	formatter: function(value,row,index){
		return '<input type="hidden" name="yyypmls['+index+'].usergysid" value="'+row.usergysid+'" />';
	}
},{
	field : 'usergysmc',
	title : '供货商',
	width : 80
},{
	field : 'bm',
	title : '流水号',
	width : 60
},{
	field : 'mc',
	title : '通用名',
	width : 130
},{
	field : 'jx',
	title : '剂型',
	width : 80
},{
	field : 'gg',
	title : '规格',
	width : 100
},{
	field : 'zhxs',
	title : '转换系数',
	width : 50
},{
	field : 'scqymc',
	title : '生产企业',
	width : 160
},{
	field : 'spmc',
	title : '商品名称',
	width : 150
},{
	field : 'zbjg',
	title : '中标价',
	width : 50
},{
	field : 'jyztmc',
	title : '交易状态',
	width : 60
}
]];

var dataGrid_obj;

function initGrid(){
	dataGrid_obj = $('#yyypmladdlist');
	
	dataGrid_obj.datagrid({
		title : '医院采购目录药品添加',
		//nowrap : false,
		striped : true,
		//collapsible : true,
		url : '${baseurl}/ypml/yyypmladdquery_result.action',
		//sortName : 'code',
		//sortOrder : 'desc',
		//remoteSort : false,
		//idField : 'ypxxid',
		//frozenColumns : frozenColumns,
		columns : columns,
		pagination : true,
		rownumbers : true,
		toolbar : toolbar,
		loadMsg:"",
		pageList:[15,30,50,100],
		onClickRow : function(index, field, value) {
			dataGrid_obj.datagrid('unselectRow', index);
		}
		});

	}
	$(function() {
		initGrid();
		
	});

	function yyypmladdquery() {
 
		var formdata = $("#yyypmladdqueryForm").serializeJson();
		//alert(formdata);
		dataGrid_obj.datagrid('unselectAll');
		dataGrid_obj.datagrid('load', formdata);
	}
</script>
</HEAD>
<BODY>
<div id="ypxxquery_div">
    <form id="yyypmladdqueryForm" name="yyypmladdqueryForm" action="${baseurl}/ypml/yyypmladd.action" method="post">
			<input type="hidden" name="indexs" id="indexs" />
			<TABLE  class="table_search">
				<TBODY>
					<TR>
						
						<TD class="left">通用名：</td>
						<td><INPUT type="text"  name="ypxxCustom.mc" /></TD>
						<TD class="left">剂型：</TD>
						<td ><INPUT type="text" name="ypxxCustom.jx" /></td>
						<TD class="left">规格：</TD>
						<td ><INPUT type="text" name="ypxxCustom.gg" /></td>
						<TD class="left">转换系数：</TD>
						<td ><INPUT type="text" name="ypxxCustom.zhxs" /></td>
					</TR>
					<TR>
						<TD class="left">流水号：</TD>
						<td ><INPUT type="text" name="ypxxCustom.bm" /></td>
						<TD class="left">生产企业：</TD>
						<td ><INPUT type="text" name="ypxxCustom.scqymc" /></td>
						<TD class="left">商品名称：</TD>
						<td ><INPUT type="text" name="ypxxCustom.spmc" /></td>
						 <td class="left">价格范围：</td>
				  		<td>
				      		<INPUT id="ypxxCustom.zbjglower" name="ypxxCustom.zbjglower" style="width:70px"/>
							至
							<INPUT id="ypxxCustom.zbjgupper" name="ypxxCustom.zbjgupper" style="width:70px"/>
							
				 		 </td>
					</tr>
					<tr>
					  
						<TD class="left">药品类别：</TD>
						<td >
							<select id="ypxxCustom.lb" name="ypxxCustom.lb" style="width:150px">
								<option value="">全部</option>
								<c:forEach items="${yplbList}" var="value">
									<option value="${value.id}">${value.info}</option>
								</c:forEach>
							</select>
						</td>
						<TD class="left">交易状态：</TD>
						<td >
							<select id="ypxxCustom.jyzt" name="ypxxCustom.jyzt" style="width:150px">
								<option value="">全部</option>
								<c:forEach items="${ypjyztList}" var="value">
									<option value="${value.dictcode}">${value.info}</option>
								</c:forEach>
							</select>
							
						</td>
						
				 		 <td class="left" height="25">供货商：</td>
				  		<td>
				  		<input type="text" name="usergys.mc" id="usergys.mc" />
					
				  		</td>
				  		<td colspan=2>
						<a id="btn" href="#" onclick="yyypmladdquery()" class="easyui-linkbutton" iconCls='icon-search'>查询</a>
				  		</td>
						
					</TR>
					
				</TBODY>
			</TABLE>
	    
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
			<TBODY>
				<TR>
					<TD>
						<table id="yyypmladdlist"></table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		</form>
	</div>

</BODY>
</HTML>

