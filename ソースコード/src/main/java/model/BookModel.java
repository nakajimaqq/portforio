package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class BookModel implements Serializable {

	private int id;
	private int users_id;
	private String title;
	private String author;
	private int publication;
	private Date start;
	private Date finish;
	private String impressions;
	private int is_deleted;
	private Timestamp create_date_time;
	private Timestamp update_date_time;
	
	private UserModel userModel;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUsers_id() {
		return this.users_id;
	}
	
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublication() {
		return this.publication;
	}
	
	public void setPublication(int publication2) {
		this.publication = publication2;
	}
	
	public Date getStart() {
		return this.start;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getFinish() {
		return this.finish;
	}
	
	public void setFinish(Date finish) {
		this.finish = finish;
	}
	
	public String getImpressions() {
		return this.impressions;
	}
	
	public void setImpressions(String impressions) {
		this.impressions = impressions;
	}
	
	
	public int getIs_deleted() {
		return this.is_deleted;
	}
	
	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	public Timestamp getCreate_date_time() {
		return this.create_date_time;
	}
	
	public void setCreate_date_time(Timestamp create_date_time) {
		this.create_date_time = create_date_time;
	}
	
	public Timestamp getUpdate_date_time() {
		return this.update_date_time;
	}
	
	public void setUpdate_date_time(Timestamp update_date_time) {
		this.update_date_time = update_date_time;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	

}
