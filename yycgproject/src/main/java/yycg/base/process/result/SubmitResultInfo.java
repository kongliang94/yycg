package yycg.base.process.result;

/**
 * 系统提交结果结果类型
 * @author KL
 *
 */
public class SubmitResultInfo {
	
	//操作结果信息
	private ResultInfo resultInfo;
	
	public ResultInfo getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(ResultInfo resultInfo) {
		this.resultInfo = resultInfo;
	}

	/**
	 * 系统提交结果集类型
	 * @param resultInfo
	 */
	public SubmitResultInfo(ResultInfo resultInfo){
		this.resultInfo=resultInfo;
	}
}
