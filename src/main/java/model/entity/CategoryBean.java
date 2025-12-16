package model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CategoryBean implements Serializable{
	
	private int category_id;
	private String category_name;
	private LocalDateTime update_datetime;
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public LocalDateTime getUpdate_datetime() {
		return update_datetime;
	}
	public void setUpdate_datetime(LocalDateTime update_datetime) {
		this.update_datetime = update_datetime;
	}

}
