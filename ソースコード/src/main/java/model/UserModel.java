package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserModel implements Serializable {

	private int id;
	private String user_name;
	private String email;
	private String password;
	private int is_deleted;
	private Timestamp create_date_time;
	private Timestamp update_date_time;
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUser_name() {
		return this.user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	
	
	
	
	
}
