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
								<TD>&nbsp;药品信息</TD>
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
								<TD height=30 width="15%" align=right>流水号：</TD>
								<TD class=category width="35%">
								${ypxx.bm}
								</TD>
								<TD height=30 width="15%" align=right >通用名：</TD>
								<TD class=category width="35%">
								<div>
								${ypxx.mc}
								</div>
								<div id="ypxx_mcTip"></div>
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>剂型：</TD>
								<TD class=category>
								<div>
								${ypxx.jx}
								</div>
								<div id="ypxx_jxTip"></div>
								</TD>
								<TD height=30 align=right>规格：</TD>
								<TD class=category>
								<div>
								${ypxx.gg}
								</div>
								<div id="ypxx_ggTip"></div>
								</TD>
							</TR>
							
							<TR>
								
								<TD height=30 align=right>转换系数：</TD>
								<TD class=category>
								<div>
								${ypxx.zhxs}
								</div>
								<div id="ypxx_zhxsTip"></div>
								</TD>
								<TD height=30 align=right>生产企业名称：</TD>
								<TD class=category>
								<div>
								${ypxx.scqymc}
								</div>
								<div id="ypxx_scqymcTip"></div>
								</TD>
							</TR>
							
							<TR>
								
								<TD height=30 align=right>商品名称：</TD>
								<TD class=category>
								<div>
								${ypxx.spmc}
								</div>
								<div id="ypxx_spmcTip"></div>
								</TD>
								<TD height=30 align=right>单位：</TD>
								<TD class=category>
								${ypxx.dw}
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>中标价格：</TD>
								<TD class=category>
								<div>
								${ypxx.zbjg}
								</div>
								<div id="ypxx_zbjgTip"></div>
								</TD>
								<TD height=30 align=right>批准文号：</TD>
								<TD class=category>
								${ypxx.pzwh}
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>批准文号有效期：</TD>
								<TD class=category>
								<fmt:formatDate value="${ypxx.pzwhyxq}" pattern="yyyy-MM-dd"/>
								</TD>
								<TD height=30 align=right>是否进口药：</TD>
								<TD class=category>
									<c:if test="${ypxx.jky == '1'}">是</c:if>
									<c:if test="${ypxx.jky == '0'}">否</c:if>
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>包装材质：</TD>
								<TD class=category>
								${ypxx.bzcz}
								</TD>
								<TD height=30 align=right>包装单位：</TD>
								<TD class=category>
								${ypxx.bzdw}
								</TD>
							</TR>
							<TR>
								<TD height=30 align=right>最新零售价：</TD>
								<TD class=category>
								${ypxx.lsjg}
								</TD>
								<TD height=30 align=right>零售价出处：</TD>
								<TD class=category>
								${ypxx.lsjgcc}
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>质量层次：</TD>
								<TD class=category>
								${ypxx.zlcc}
								</TD>
								<TD height=30 align=right>质量层次说明：</TD>
								<TD class=category>
								${ypxx.zlccsm}
								</TD>
							</TR>
							<TR>
								<TD height=30 align=right>有无药品检验报告：</TD>
								<TD class=category>
									<c:if test="${ypxx.ypjybg == '1'}">有</c:if>
								 	<c:if test="${ypxx.ypjybg == '0'}">无</c:if>
								</TD>
								<TD height=30 align=right>药品检验报告编号：</TD>
								<TD class=category>
								${ypxx.ypjybgbm}
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>药品检验报告有效期：</TD>
								<TD class=category>
								<fmt:formatDate value="${ypxx.ypjybgyxq}" pattern="yyyy-MM-dd"/>
								</TD>
								<TD height=30 align=right>药品交易状态：</TD>
								<TD class=category>
								 ${ypxx.jyzt}
								</TD>
							</TR>
							<TR>
							    <TD height=30 align=right>产品说明：</TD>
								<TD class=category colspan=3>
								<textarea rows="6" cols="100" id="ypxx.cpsm" name="ypxx.cpsm" >${ypxx.cpsm}</textarea>
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

