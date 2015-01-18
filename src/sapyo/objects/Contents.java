package sapyo.objects;

import easyjdbc.annotation.Key;
import easyjdbc.annotation.Table;

@Table("contents")
public class Contents {

	@Key
	private Integer id;
	private String contents;
	private Integer likes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
}