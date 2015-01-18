package sapyo.objects;

import java.util.Date;

import easyjdbc.annotation.Key;
import easyjdbc.annotation.OtherTable;
import easyjdbc.annotation.Table;
import easymapping.annotation.DateFormat;

@Table(value = "sapyo", joinWith = "contents", joinType = "right", on = "sapyo.id = contents.id", defaultCondition = "order by sapyo.id desc limit 0,7")
public class SapyoWithContents {
	@Key
	private Integer id;
	private String email;
	@DateFormat("yyyy-MM-dd hh:mm")
	private Date submittime;
	
	@OtherTable
	private String contents;

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

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
		return "SapyoWithContents [id=" + id + ", email=" + email + ", submittime=" + submittime + ", contents=" + contents + "]";
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
