package sapyo.objects;

import easyjdbc.annotation.Column;
import easyjdbc.annotation.Key;
import easyjdbc.annotation.Table;

@Table("user")
public class User {

	@Key
	private String email;
	private String password;
	private String familyname;
	@Column("class")
	private String type;
	private String company;
	
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", familyname=" + familyname + ", type=" + type + ", company=" + company + "]";
	}


	public User() {

	}

	public User(String email, String password, String familyname, String type, String company) {
		super();
		this.email = email;
		this.password = password;
		this.familyname = familyname;
		this.type = type;
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}