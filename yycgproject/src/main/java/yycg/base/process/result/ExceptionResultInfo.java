package yycg.base.process.result;

/**
 * 自定义系统异常
 * @author KL
 *
 */
public class ExceptionResultInfo extends Exception{

	//系统统一使用的结果类，包括了 提示信息类型和信息内容
	private ResultInfo resultInfo;
	
	public ResultInfo getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}

	public ExceptionResultInfo(ResultInfo resultInfo){
		//调用父类Exception的构造函数将resultInfo.getMessage()当做新的异常信息构造
		super(resultInfo.getMessage()); 
		this.resultInfo=resultInfo;
	}
}
