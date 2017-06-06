package easybuy.server.model;

public class HttpResult<T> {
	private Integer resultCode;
	private String resultMessage;
	private T data;
	
	public HttpResult() {
		super();
	}

	public HttpResult(Integer resultCode, String resultMessage, T data) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
		this.data = data;
	}

	public Integer getResultCode() {
		return resultCode;
	}

	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
