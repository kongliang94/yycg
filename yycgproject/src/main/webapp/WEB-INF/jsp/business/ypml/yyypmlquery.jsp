<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>医院药品目录查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">

var yyypmldel = function(){
	_confirm('您确定要执行删除选中的药品吗?',null,
	  function(){
		var indexs = [];//删除记录的序号
		var rows = $('#yyypmllist').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			var index=$('#yyypmllist').datagrid('getRowIndex',rows[i]);
			indexs.push(index);
		}
		if(rows.length>0){
			$("#indexs").val(indexs.join(','));
			jquerySubByFId('yyypmldeleteForm', yyypmldel_callback, null);
		}else{
			alert_warn("请选择要删除的药品");
		}
		
	  }
	)
	
};

function yyypmldel_callback(data) {
	var result = getCallbackData(data);
	_alert(result);
	yyypmlquery();
}


/* function ypxxedit_callback(redata){
	$('#ypxxquery_div').css("display","none");
	$("#ypxxedit_div").css("display","block");
	$("#ypxxedit_div").html(redata);
} */
function ypxxinfo(id){
	var sendUrl = "${baseurl}/ypml/ypxxview.action?id="+id;
	createmodalwindow("药品信息查看", 900, 500, sendUrl);
}
var yyypmladd = function (){
	//alert(id);
	var sendUrl = "${baseurl}/ypml/yyypmladdquery.action";
	createmodalwindow("供货药品添加", 1000, 500, sendUrl);
	/* var ajaxOption = new AjaxOption();
			ajaxOption._initPostRequest(true,sendUrl,"json","html");
			_ajaxPostRequest(ajaxOption, '', ypxxedit_callback);  */
};


//工具栏

var toolbar = [ {
	id : 'yyypmladd',
	text : '采购目录药品添加',
	iconCls : 'icon-add',
	handler : yyypmladd
	},{
	id : 'yyypmldel',
	text : '采购目录药品删除',
	iconCls : 'icon-remove',
	handler : yyypmldel
	}];

var frozenColumns;

var columns = [ [{
	checkbox:true
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
	field : 'useryymc',
	title : '医院名称',
	width : 100
},{
	field : 'usergysmc',
	title : '供货企业',
	width : 100
},{
	field : 'control',
	title : '供货状态',
	width : 60,
	formatter: function(value,row,index){
		if(value == '2'){
			return '无法供货';
		}else{
			return '正常';
		}
	}
},{
	field : 'bm',
	title : '流水号',
	width : 50
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
	width : 100
},{
	field : 'spmc',
	title : '商品名称',
	width : 100
},{
	field : 'zbjg',
	title : '中标价',
	width : 50
},{
	field : 'jyztmc',
	title : '交易状态',
	width : 60
},{
	field : 'opt3',
	title : '详细',
	width : 60,
	formatter:function(value, row, index){
		return '<a href=javascript:ypxxinfo(\''+row.ypxxid+'\')>查看</a>';
	}
}]];

function initGrid(){
	$('#yyypmllist').datagrid({
		title : '医院采购目录列表',
		//nowrap : false,
		striped : true,
		//collapsible : true,
		url : '${baseurl}/ypml/yyypmlquery_result.action',
		//sortName : 'code',
		//sortOrder : 'desc',
		//remoteSort : false,
		idField : 'id',
		//frozenColumns : frozenColumns,
		columns : columns,
		pagination : true,
		rownumbers : true,
		toolbar : toolbar,
		loadMsg:"",
		pageList:[15,30,50,100],
		onClickRow : function(index, field, value) {
					$('#yyypmllist').datagrid('unselectRow', index);
				}
		});

	}
	$(function() {
		initGrid();
		
	});

	function yyypmlquery() {
 
		var formdata = $("#yyypmldeleteForm").serializeJson();
		//alert(formdata);
		$('#yyypmllist').datagrid('unselectAll');
		$('#yyypmllist').datagrid('load', formdata);
	}
</script>
</HEAD>
<BODY>
<div id="ypxxquery_div">
    <form id="yyypmldeleteForm" name="yyypmldeleteForm" action="${baseurl}/ypml/yyypmldelete.action" method="post">
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
						
				 		 <td class="left" height="25">供货状态：</td>
				  		<td>
				  		<select id="control" name="control" style="width:80px">
								<option value="">全部</option>
								<option value="1">正常</option>
								<option value="2">无法供货</option>
						</select>
					
				  		</td>

				  		<td colspan=2>
				  		
						<a id="btn" href="#" onclick="yyypmlquery()" class="easyui-linkbutton" iconCls='icon-search'>查询</a>
				  		</td>
						
					</TR>
					
				</TBODY>
			</TABLE>
	   
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
			<TBODY>
				<TR>
					<TD>
						<table id="yyypmllist"></table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		 </form>
	</div>


</BODY>
</HTML>

