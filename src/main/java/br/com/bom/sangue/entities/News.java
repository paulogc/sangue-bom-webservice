package br.com.bom.sangue.entities;

import java.util.Date;

public class News {
    private Long id;
    private String title;
    private String text;
    private Date createdAt;
    private Administrator administrator;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Administrator getAdministrator() {
		return administrator;
	}
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
    
}
