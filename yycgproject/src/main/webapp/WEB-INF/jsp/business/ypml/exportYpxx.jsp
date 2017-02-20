<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>药品目录导出</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">
//药品信息导出
function ypxxexport(){
	$("#ypxxlistFrom").attr("action",'${baseurl}ypml/exportYpxxSubmit.action');
	//调用ajax Form提交
	jquerySubByFId('ypxxlistFrom',ypxxExprot_callback,null,"json");
	
}
function ypxxExprot_callback(data){
	
	//在这里提示信息中有文件下载链接
	message_alert(data);
	
}

function ypxxfind(){
	var formdata = $("#ypxxlistFrom").serializeJson();
	//alert(formdata);
	$("#ypxxlistFrom").attr("action",'${baseurl}ypml/queryexportYpxx_result.action');
	//alert($("#ypxxlistFrom").attr("action"));
	//调用ajax Form提交
	//jquerySubByFId('ypxxlistFrom',ypxxFind_callback,null,"json");
	$('#exportYpxxlist').datagrid('load',formdata);
}
function ypxxFind_callback(data){
	
	//在这里提示信息中有文件下载链接
	message_alert(data);
	
}
var columns = [ [{
	field : 'id',
	title : 'id',
	width : 80
},{
	field : 'bm',
	title : '流水号',
	width : 80
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
	width : 180
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

var dataGrid_obj;//datagrid的对象


	$(function() {
		dataGrid_obj = $('#exportYpxxlist');
		
		dataGrid_obj.datagrid({
			title : '导出药品列表',
			//nowrap : false,
			striped : true,
			//collapsible : true,
			url : '${baseurl}ypml/queryexportYpxx_result.action',
			//sortName : 'code',
			//sortOrder : 'desc',
			//remoteSort : false,
			idField : 'id',//结果集主键,设置错误影响获取选中行的操作，比如getSelections执行
			//frozenColumns : frozenColumns,
			columns : columns,
			pagination : true,
			rownumbers : true,
			toolbar : toolbar,
			loadMsg:"由于您要导出的数据量较大，请等待片刻",
			pageList:[15,30,50,100],
			//点击行时取消选中行，因为jqueryeasyui在点击行的时候默认选中行
			 onClickRow : function(index, field, value) {
				dataGrid_obj.datagrid('unselectRow', index);
			}, 
			//当加载成功时：清除所有选中的行，解决分页数据传到action为-1问题
			onLoadSuccess:function(){
				dataGrid_obj.datagrid('clearSelections');
			} 
			});
		
	});

</script>

</head>
<body>
<!-- 导出条件 -->

<form id="ypxxlistFrom" name="ypxxlistFrom" action="${baseurl}ypml/exportYpxxSubmit.action" method="post">
<TABLE  class="table_search">
				<TBODY>
				
					<TR>
						<TD class="left">流水号：</TD>
						<td ><INPUT type="text" name="ypxxCustom.bm" /></td>
						<TD class="left">通用名：</td>
						<td><INPUT type="text"  name="ypxxCustom.mc" /></TD>
						
						<TD class="left">药品类别：</TD>
						<td >
							<select id="ypxxCustom.lb" name="ypxxCustom.lb" style="width:150px">
								<option value="">全部</option>
								<c:forEach items="${yplblist}" var="value">
									<option value="${value.id}">${value.info}</option>
								</c:forEach>
							</select>
						</td>
						<TD class="left">交易状态：</TD>
						<td >
							<select id="ypxxCustom.jyzt" name="ypxxCustom.jyzt" style="width:150px">
								<option value="">全部</option>
								<c:forEach items="${jyztlist}" var="value">
									<option value="${value.dictcode}">${value.info}</option>
								</c:forEach>
							</select>
							
						</td>
					</TR>
					<TR>
						
						<TD class="left">生产企业：</TD>
						<td ><INPUT type="text" name="ypxxCustom.scqymc" /></td>
						<TD class="left">剂型：</TD>
						<td ><INPUT type="text" name="ypxxCustom.jx" /></td>
						<TD class="left">规格：</TD>
						<td ><INPUT type="text" name="ypxxCustom.gg" /></td>
						<TD class="left">转换系数：</TD>
						<td ><INPUT type="text" name="ypxxCustom.zhxs" /></td>
					</TR>
					<tr>
					<td colspan="12" style="text-align:center"><a id="btn" href="#" onclick="ypxxfind()" class="easyui-linkbutton" iconCls='icon-search'>查询</a>
					  <a id="btn" href="#" onclick="ypxxexport()" class="easyui-linkbutton" iconCls='icon-search'>导出</a></td>
					</tr>
				</TBODY>
			</TABLE>
			<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
			<TBODY>
				<TR>
					<TD>
						<table id="exportYpxxlist"></table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
			
</form>


</body>
</html>