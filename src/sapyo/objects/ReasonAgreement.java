package sapyo.objects;

import easyjdbc.annotation.Key;
import easyjdbc.annotation.Table;

@Table("reasonagreement")
public class ReasonAgreement {
	@Key
	private int id;
	@Key
	private String email;
	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
