<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

		<script type="text/javascript">
		
	$(function (){
		//***********按钮**************
		$('#closebtn').linkbutton({   
    		iconCls: 'icon-cancel'  
		});
		
		
	});
	
	
	</script>
 </HEAD>
<BODY>

<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" bgColor=#c4d8ed>
		<TBODY>
			<TR>
				<TD background=images/r_0.gif width="100%">
					<TABLE cellSpacing=0 cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;医院信息</TD>
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
								<TD height=30 width="15%" align=right >医院名称：</TD>
								<TD class=category width="35%">
								<div>
								${useryy.mc}
								</div>
								<div id="useryy_mcTip"></div>
								</TD>
								<TD height=30 width="15%" align=right>所属地区：</TD>
								<TD class=category width="35%">
								<div>
								${useryydqmc}
								</div>
								
								</TD>
							</TR>
							
							
							<TR>
								<TD height=30 width="15%" align=right >医院级别：</TD>
								<TD class=category width="35%">
								${useryyjbmc}
								</TD>
								<TD height=30 width="15%" align=right>是否非营利性医疗机构：</TD>
								<TD class=category width="35%">
								<c:if test="${useryy.fyljg==1}">是</c:if>
								<c:if test="${useryy.fyljg==0}">否</c:if>
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right >通讯地址：</TD>
								<TD class=category width="35%">
								${useryy.dz}
								</TD>
								<TD height=30 width="15%" align=right>邮政编码：</TD>
								<TD class=category width="35%">
								${useryy.yzbm}
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right >院办电话：</TD>
								<TD class=category width="35%">
								${useryy.dh}
								</TD>
								<TD height=30 width="15%" align=right>院办传真：</TD>
								<TD class=category width="35%">
								${useryy.cz}
								</TD>
							</TR>
							<TR>
								<TD height=30 width="15%" align=right >药剂科电话：</TD>
								<TD class=category width="35%">
								${useryy.yjkdh}
								</TD>
								<TD height=30 width="15%" align=right>医院类型：</TD>
								<TD class=category width="35%">
								${useryylbmc}
								</TD>
							</TR>
							<tr>
							  <td colspan=4 align=center class=category>
								<a id="closebtn" href="#" onclick="parent.closemodalwindow()">关闭</a>
							  </td>
							</tr>
						
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>

</BODY>
</HTML>

