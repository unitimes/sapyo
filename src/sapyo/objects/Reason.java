package sapyo.objects;

import easyjdbc.annotation.Key;
import easyjdbc.annotation.Table;

@Table("reason")
public class Reason {

	@Key
	private int id;
	private String reason;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}



}
