<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html> 
<head>
<title>退货单创建</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<script type="text/javascript">

//退货单保存
function yythdsave(){
	$("#yythdsaveForm").attr("action","${baseurl}thd/yythdsave.action");
	jquerySubByFId('yythdsaveForm', yythdsave_callback, null);
}

function yythdsave_callback(data) {
	var result = getCallbackData(data);
	_alert(result);
	//如果成功刷新页面，重新跳转至修改页面
	if(result.type==TYPE_RESULT_SUCCESS){
		alert(result.sysdata.yythdid);
		var yythdid=result.sysdata.yythdid;
		var year=result.sysdata.year;
		window.location='${baseurl}thd/yythdedit.action?yythdid='+yythdid+'&year='+year;
	}
	
}
$(function(){
	//加载年
	businessyearlist('businessyear');
});

</script>
</HEAD>
<BODY>

<form id="yythdsaveForm" name="yythdsaveForm" action="${baseurl}thd/yythdsave.action" method="post">
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
								<TD height=30 width="15%" align=right>药品采购年份<br>(如2014)：</TD>
								<TD class=category width="35%">
								<select id="businessyear" name="year"></select>
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
							  </td>
							</tr>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</form>

</BODY>
</HTML>

