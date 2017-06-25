<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>退货单查看</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">


//工具栏
var toolbar ;

var frozenColumns;

var columns = [ [{
	field : 'useryymc',
	title : '医院',
	width : 100
},{
	field : 'yycgdbm',
	title : '采购单号',
	width : 50
},{
	field : 'usergysmc',
	title : '供货商',
	width : 100
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
}
,{
	field : 'rkdh',
	title : '发票号或入库单号',
	width : 50
},{
	field : 'ypph',
	title : '药品批号',
	width : 50
},{
	field : 'ypyxq',
	title : '药品有效期(年)',
	width : 50
}]];

function initGrid(){
	$('#yythdmxlist').datagrid({
		title : '采购药品列表',
		//nowrap : false,
		striped : true,
		//collapsible : true,
		url : '${baseurl}/thd/yythdlist_thdmxresult.action',
		queryParams:{
			yythdid:'${yythd.id}'
		},
		//sortName : 'code',
		//sortOrder : 'desc',
		//remoteSort : false,
		//idField : 'yythdmxid',//列表的主键，如果设置不准备影响datagrid操作
		//frozenColumns : frozenColumns,
		columns : columns,
		pagination : true,
		rownumbers : true,
		toolbar : toolbar,
		loadMsg:"",
		pageList:[15,30,50,100]
		} );

	}
	$(function() {
		initGrid();
	});

	function yythdmxquery() {
		var formdata = $("#yythdmxForm").serializeJson();
		//alert(formdata);
		$('#yythdmxlist').datagrid('unselectAll');
		$('#yythdmxlist').datagrid('load', formdata);
	}
</script>
</HEAD>
<BODY>

<form id="yythdsaveForm" name="yythdsaveForm" action="${baseurl}/thd/yythdsave.action" method="post">
<input type="hidden" name="yythd.id" value="${yythd.id}"/>
<TABLE border=0 cellSpacing=0 cellPadding=0 width="70%" bgColor=#c4d8ed align=center>
		<TBODY>
			<TR>
				<TD background=images/r_0.gif width="100%">
					<TABLE cellSpacing=0 cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;药品退货单</TD>
								<TD align=right>&nbsp;</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD>
					<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
						align=center>
						<TBODY>
							
							<TR>
								<TD height=30 width="15%" align=right>采购药品年份：</TD>
								<TD class=category width="35%">
								<input type="hidden" name="year" value="${year}" />
								${year}
								</TD>
								<TD height=30 width="15%" align=right >退货单名称：</TD>
								<TD class=category width="35%">
								<div>
								<input type="text" id="yythd_mc" name="yythd.mc" value="${yythd.mc}"  style="width:260px" />
								</div>
								<div id="yythd_mcTip"></div>
								</TD>
							</TR>
							<TR>
							   <TD height=30 width="15%" align=right >建单时间：</TD>
								<TD class=category width="35%">
									<fmt:formatDate value="${yythd.cjtime}" pattern="yyyy-MM-dd"/>
								</TD>
								<TD height=30 width="15%" align=right >提交时间：</TD>
								<TD class=category width="35%">
								<fmt:formatDate value="${yythd.tjtime}" pattern="yyyy-MM-dd"/>
								</TD>
								
							</TR>
							<TR>
								<TD height=30 width="15%" align=right>联系人：</TD>
								<TD class=category width="35%">
								<input type="text" name="yythd.lxr" id="yythd.lxr" value="${yythd.lxr}"  style="width:260px" />
								</TD>
								<TD height=30 width="15%" align=right >联系电话：</TD>
								<TD class=category width="35%">
								<input type="text" name="yythd.lxdh" id="yythd.lxdh" value="${yythd.lxdh}"  style="width:260px" />
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right>退货单状态：</TD>
								<TD class=category width="35%">
								${yycgdCustom.thdztmc}
								</TD>
								<TD height=30 width="15%" align=right>备注：</TD>
								<TD colspan=3>
									<textarea rows="2" cols="30" name="yythd.bz">${yythd.bz}</textarea>
								</TD>
							</TR>
							
							
							<tr>
							  <td colspan=4 align=center class=category>
								<a  href="#" onclick="yythdsave()" class="easyui-linkbutton" iconCls='icon-save'>保存</a>
								<a  href="#" onclick="yythdsubmit()" class="easyui-linkbutton" iconCls='icon-ok'>提交</a>
							  </td>
							</tr>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</form>

<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" bgColor=#c4d8ed>
		<TBODY>
			<TR>
				<TD background=images/r_0.gif width="100%">
					<TABLE cellSpacing=0 cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;采购药品列表</TD>
								<TD align=right>&nbsp;</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
 <form id="yythdmxForm" name="yythdmxForm" method="post">
	<input type="hidden" name="indexs" id="indexs" />
	<input type="hidden" name="year" value="${year}"/>
	<input type="hidden" name="yycgdCustom.yythdid" value="${yythd.id}" />
			<TABLE class="table_search">
				<TBODY>
					<TR>
						<TD class="left">年份：</TD>
						<td>${year}</td>
						<TD class="left">采购单编号：</td>
						<td><INPUT type="text" name="yycgdCustom.bm" /></TD>
						<TD class="left">采购单名称：</TD>
						<td><INPUT type="text" name="yycgdCustom.mc" /></td>
						
						<TD class="left">采购单名称：</TD>
						<td><INPUT type="text" name="yycgdCustom.mc" /></td>
					</TR>

					<TR>
						<TD class="left">流水号：</TD>
						<td><INPUT type="text" name="ypxxCustom.bm" /></td>
						<TD class="left">通用名：</td>
						<td><INPUT type="text" name="ypxxCustom.mc" /></TD>
						<TD class="left">采购状态：</td>
						<td>已入库</TD>
						<TD class="left">结算状态：</td>
						<td>未结算</TD>
					</TR>


					<tr>
						<TD class="left">退货状态：</TD>
						<td><select id="yycgdCustom.thzt" name="yycgdCustom.thzt"
							style="width: 150px">
								<option value="">全部</option>
								<c:forEach items="${thztList}" var="value">
									<option value="${value.dictcode}">${value.info}</option>
								</c:forEach>
						</select></td>

						<td colspan=6><a id="btn" href="#" onclick="yythdmxquery()"
							class="easyui-linkbutton" iconCls='icon-search'>查询</a></td>
					</tr>



					</TR>

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

