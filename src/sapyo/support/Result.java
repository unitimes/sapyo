package sapyo.support;

public class Result {

	public Result(Boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}
	
	@SuppressWarnings("unused")
	private Boolean success;
	@SuppressWarnings("unused")
	private String error;
}
