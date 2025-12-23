package model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskBean implements Serializable {

	private int task_id;
	private String task_name;
	private int category_id;
	private String category_name;
	private LocalDate limit_date;
	private String user_id;
	private String user_name;
	private String status_code;
	private String status_name;
	private String memo;
	private LocalDateTime create_datetime;
	private LocalDateTime update_datetime;

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

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

	public LocalDate getLimit_date() {
		return limit_date;
	}

	public void setLimit_date(LocalDate limit_date) {
		this.limit_date = limit_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	
	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LocalDateTime getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(LocalDateTime create_datetime) {
		this.create_datetime = create_datetime;
	}

	public LocalDateTime getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(LocalDateTime update_datetime) {
		this.update_datetime = update_datetime;
	}

	

}
