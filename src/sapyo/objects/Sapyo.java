package sapyo.objects;

import java.util.Date;

import easyjdbc.annotation.Key;
import easyjdbc.annotation.Table;
import easymapping.annotation.DateFormat;

@Table("sapyo")
public class Sapyo {
	@Key
	private Integer id;
	private String email;
	@DateFormat("yyyy-MM-dd hh:mm")
	private Date submittime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Sapyo [id=" + id + ", email=" + email + ", submittime=" + submittime + "]";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSubmittime() {
		return submittime;
	}

	public void setSubmittime(Date submittime) {
		this.submittime = submittime;
	}
}