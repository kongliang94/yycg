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
		$('#submitbtn').linkbutton({   
    		iconCls: 'icon-ok'  
		});  
		$('#closebtn').linkbutton({   
			iconCls: 'icon-cancel'  
		});
		//**********表单校验*************
		$.formValidator.initConfig({
			formID : "ypxxeditform",
			theme : "Default",
			onError : function(msg, obj, errorlist) {
				//alert(msg);
			}
		});
		//通用名
		$("#ypxx_mc").formValidator({
			onShow : "",
			onCorrect:"&nbsp;"
		}).inputValidator({
			min : 1,
			max : 60,
			onError : "请输入通用名(最长30个字符)"
		});
		//剂型
		$("#ypxx_jx").formValidator({
			onShow : "",
			onCorrect:"&nbsp;"
		}).inputValidator({
			min : 1,
			max : 32,
			onError : "请输入剂型(最长15个字符)"
		});
		//规格
		$("#ypxx_gg").formValidator({
			onShow : "",
			onCorrect:"&nbsp;"
		}).inputValidator({
			min : 1,
			max : 128,
			onError : "请输入规格(最长60个字符)"
		});
		//转换系数
		$("#ypxx_zhxs").formValidator({
			onShow : "",
			onCorrect:"&nbsp;"
		}).inputValidator({
			min : 1,
			max : 16,
			onError : "请输入转换系数(最长8个字符)"
		});
	});
	function ypxxsave(){
		if($.formValidator.pageIsValid()){
			jquerySubByFId('ypxxeditform',ypxxsave_callback,null,"json");
		}

	}
	function ypxxsave_callback(data){
		var result = getCallbackData(data);
		var type = result.type;
		_alert(result);
		/* if (TYPE_RESULT_SUCCESS == type) {
			ypxxedit_toback();
			ypxxquery();//重新查询数据
		}  */
		
	}
	/* function ypxxedit_toback(){
		$("#ypxxquery_div").css("display","block");
		$("#ypxxedit_div").html('');
		$("#ypxxedit_div").css("display","none");
	} */
	
	$(function(){
		
		getDictinfoCodelist('002','ypxx.jyzt');
		getDictinfoCodelist('004','ypxx.zlcc');
	});
	</script>
 </HEAD>
<BODY>
<form id="ypxxeditform" name="ypxxeditform" action="${baseurl}ypml/ypxxsave.action" method="post">
<input type="hidden" name="ypxx.id" value="${ypxx.id}"/>
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
								<input type="text" id="ypxx_bm" name="ypxx.bm" value="${ypxx.bm}"   readOnly="true"/>
								</TD>
								<TD height=30 width="15%" align=right >通用名：</TD>
								<TD class=category width="35%">
								<div>
								<input type="text" id="ypxx_mc" name="ypxx.mc" value="${ypxx.mc}"   />
								</div>
								<div id="ypxx_mcTip"></div>
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>剂型：</TD>
								<TD class=category>
								<div>
								<input type="text" id="ypxx_jx" name="ypxx.jx" value="${ypxx.jx}"  />
								</div>
								<div id="ypxx_jxTip"></div>
								</TD>
								<TD height=30 align=right>规格：</TD>
								<TD class=category>
								<div>
								<input type="text" id="ypxx_gg" name="ypxx.gg" value="${ypxx.gg}"  />
								</div>
								<div id="ypxx_ggTip"></div>
								</TD>
							</TR>
							
							<TR>
								
								<TD height=30 align=right>转换系数：</TD>
								<TD class=category>
								<div>
								<input type="text" id="ypxx_zhxs" name="ypxx.zhxs"  value="${ypxx.zhxs}"  />
								</div>
								<div id="ypxx_zhxsTip"></div>
								</TD>
								<TD height=30 align=right>生产企业名称：</TD>
								<TD class=category>
								<div>
								<input type="text" id="ypxx_scqymc" name="ypxx.scqymc"  value="${ypxx.scqymc}"  />
								</div>
								<div id="ypxx_scqymcTip"></div>
								</TD>
							</TR>
							
							<TR>
								
								<TD height=30 align=right>商品名称：</TD>
								<TD class=category>
								<div>
								<input type="text" id="ypxx_spmc" name="ypxx.spmc"  value="${ypxx.spmc}"  />
								</div>
								<div id="ypxx_spmcTip"></div>
								</TD>
								<TD height=30 align=right>单位：</TD>
								<TD class=category>
								<input type="text" id="ypxx.dw" name="ypxx.dw" value="${ypxx.dw}"  />
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>中标价格：</TD>
								<TD class=category>
								<div>
								<input type="text" id="ypxx_zbjg" name="ypxx.zbjg"  value="${ypxx.zbjg}"  />
								</div>
								<div id="ypxx_zbjgTip"></div>
								</TD>
								<TD height=30 align=right>批准文号：</TD>
								<TD class=category>
								<input type="text" id="ypxx.pzwh" name="ypxx.pzwh"  value="${ypxx.pzwh}" />
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>批准文号有效期：</TD>
								<TD class=category>
								<input type="text" id="ypxx.pzwhyxq" name="ypxx.pzwhyxq"  value="<fmt:formatDate value="${ypxx.pzwhyxq}" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
								</TD>
								<TD height=30 align=right>是否进口药：</TD>
								<TD class=category>
									<input type="radio" name="ypxx.jky" value="1" <c:if test="${ypxx.jky == '1'}">checked</c:if>/>是
									<input type="radio" name="ypxx.jky" value="0" <c:if test="${ypxx.jky == '0'}">checked</c:if>/>否
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>包装材质：</TD>
								<TD class=category>
								<input type="text" id="ypxx.bzcz" name="ypxx.bzcz"  value="${ypxx.bzcz}" />
								</TD>
								<TD height=30 align=right>包装单位：</TD>
								<TD class=category>
								<input type="text" id="ypxx.bzdw" name="ypxx.bzdw"  value="${ypxx.bzdw}" />
								</TD>
							</TR>
							<TR>
								<TD height=30 align=right>最新零售价：</TD>
								<TD class=category>
								<input type="text" id="ypxx.lsjg" name="ypxx.lsjg"  value="${ypxx.lsjg}" />
								</TD>
								<TD height=30 align=right>零售价出处：</TD>
								<TD class=category>
								<input type="text" id="ypxx.lsjgcc" name="ypxx.lsjgcc"  value="${ypxx.lsjgcc}" />
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>质量层次：</TD>
								<TD class=category>
								 <select id="ypxx.zlcc" name="ypxx.zlcc">
								 	<option value="">请选择</option>
								 	<c:forEach items="${ypzlccList}" var="value">							 	
										<option value="${value.id}" <c:if test="${ypxx.zlcc == value.id}">selected</c:if>>${value.info}</option>
									</c:forEach>
								 </select>
								</TD>
								<TD height=30 align=right>质量层次说明：</TD>
								<TD class=category>
								<input type="text" id="ypxx.zlccsm" name="ypxx.zlccsm"  value="${ypxx.zlccsm}" />
								</TD>
							</TR>
							<TR>
								<TD height=30 align=right>有无药品检验报告：</TD>
								<TD class=category>
									<input type="radio" name="ypxx.ypjybg" value="1" <c:if test="${ypxx.ypjybg == '1'}">checked</c:if>/>有
								 	<input type="radio" name="ypxx.ypjybg" value="0" <c:if test="${ypxx.ypjybg == '0'}">checked</c:if>/>无
								</TD>
								<TD height=30 align=right>药品检验报告编号：</TD>
								<TD class=category>
								<input type="text" id="ypxx.ypjybgbm" name="ypxx.ypjybgbm"  value="${ypxx.ypjybgbm}" />
								</TD>
							</TR>
							
							<TR>
								<TD height=30 align=right>药品检验报告有效期：</TD>
								<TD class=category>
								<input type="text" id="ypxx.ypjybgyxq" name="ypxx.ypjybgyxq"  value="<fmt:formatDate value="${ypxx.ypjybgyxq}" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({isShowWeek:false,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
								</TD>
								<TD height=30 align=right>药品交易状态：</TD>
								<TD class=category>
								 <select id="ypxx.jyzt" name="ypxx.jyzt">
								 	<option value="">请选择</option>
								 	<c:forEach items="${ypjyztList}" var="value">							 	
										<option value="${value.dictcode}" <c:if test="${ypxx.jyzt == value.dictcode}">selected</c:if>>${value.info}</option>
									</c:forEach>
								 </select>
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
								<a id="submitbtn" href="#" onclick="ypxxsave()">提交</a>
								<a id="closebtn" href="#" onclick="parent.closemodalwindow()">关闭</a>
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

