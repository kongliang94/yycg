<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>医院退货单受理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">

var yythddisposesubmit = function(){
	_confirm('您确定对选择的药品退货吗?',null,
	  function(){
		
		var indexs = [];//提交记录的序号
		var rows = $('#yythdmxlist').datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			var index=$('#yythdmxlist').datagrid('getRowIndex',rows[i]);
			indexs.push(index);
		}
		if(rows.length>0){
			$("#indexs").val(indexs.join(','));
			jquerySubByFId('yythddisposeForm', yythddispose_callback, null);
		}else{
			alert_warn("请选择要退货的药品");
		}
		
	  }
	)
	
};

function yythddispose_callback(data) {
	var result = getCallbackData(data);
	_alert(result);
	yythdmxquery();
}



//工具栏

var toolbar = [{
	id : 'yythddisposesubmit',
	text : '确认退货',
	iconCls : 'icon-add',
	handler : yythddisposesubmit
	}];

var frozenColumns;

var columns = [ [ {
	checkbox:true
},{
	field : 'ypxxid',
	hidden : true,
	formatter: function(value,row,index){
		return '<input type="hidden" name="yythdmxs['+index+'].ypxxid" value="'+row.ypxxid+'" />';
	}
},{
	field : 'yycgdid',
	hidden : true,
	formatter: function(value,row,index){
		return '<input type="hidden" name="yythdmxs['+index+'].yycgdid" value="'+row.yycgdid+'" />';
	}
},
 {
	field : 'useryymc',
	title : '医院名称',
	width : 100
},{
	field : 'yythdbm',
	title : '退货单编号',
	width : 80
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
	field : 'ypxxbm',
	title : '流水号',
	width : 50
},{
	field : 'ypxxmc',
	title : '通用名',
	width : 100
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
	field : 'zbjg',
	title : '中标价',
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
	field : 'thl',
	title : '退货量',
	width : 50
},{
	field : 'thje',
	title : '退货金额',
	width : 50
},{
	field : 'thyy',
	title : '退货原因',
	width : 50
},{
	field : 'thztmc',
	title : '退货状态',
	width : 80
}]];

function initGrid(){
	$('#yythdmxlist').datagrid({
		title : '退货单列表',
		//nowrap : false,
		striped : true,
		//collapsible : true,
		url : '${baseurl}/thd/yythddispose_result.action',
		queryParams:{
			year:'${year}'
		},
		//sortName : 'code',
		//sortOrder : 'desc',
		//remoteSort : false,
		//idField : 'id',//如果值不是主键则影响获取checkbox选中个数
		//frozenColumns : frozenColumns,
		columns : columns,
		autoRowHeight:true,
		pagination : true,
		rownumbers : true,
		toolbar : toolbar,
		loadMsg:"",
		pageList:[15,30,50,100],
		onClickRow : function(index, field, value) {
					$('#yythdmxlist').datagrid('unselectRow', index);
				}
		});

	}
	$(function() {
		initGrid();
		
	});

	function yythdmxquery() {
		var formdata = $("#yythddisposeForm").serializeJson();
		//alert(formdata);
		$('#yythdmxlist').datagrid('unselectAll');
		$('#yythdmxlist').datagrid('load', formdata);
	}
	$(function(){
		//加载年
		businessyearlist('businessyear');
	
	});
</script>
</HEAD>
<BODY>
    <form id="yythddisposeForm" name="yythddisposeForm" method="post" action="${baseurl}/thd/yythddispose_submit.action">
			<input type="hidden" name="indexs" id="indexs" />
			<TABLE  class="table_search">
				<TBODY>
					<TR>
						<TD class="left">年份(如2014)：</TD>
						<td >
						<select id="businessyear" name="year"></select>
						</td>
						<TD class="left">医院名称：</TD>
						<td ><INPUT type="text" name="useryyCustom.mc" /></td>
						<TD class="left">退货单编号：</td>
						<td><INPUT type="text"  name="yycgdCustom.yythdbm" /></TD>
						<TD class="left">退货单名称：</TD>
						<td ><INPUT type="text" name="yycgdCustom.yythdmc" /></td>
					</TR>
					<TR> 
						<TD class="left">流水号：</TD>
						<td ><INPUT type="text" name="ypxxCustom.bm" /></td>
						<TD class="left">通用名：</td>
					    <td><INPUT type="text"  name="ypxxCustom.mc" />
						    
						 </TD>
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
						<a id="btn" href="#" onclick="yythdmxquery()" class="easyui-linkbutton" iconCls='icon-search'>查询</a>
						</td>
					</tr>
					
					
				</TBODY>
			</TABLE>
	   
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
			<TBODY>
				<TR>
					<TD>
						<table id="yythdmxlist"></table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
		 </form>

</BODY>
</HTML>

