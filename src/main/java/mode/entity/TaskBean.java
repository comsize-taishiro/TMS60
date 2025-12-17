package mode.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class TaskBean implements Serializable {
	
	private int task_id;
	private String task_name;
	private int category_id;
	private LocalDate limit_date;
	private String user_id;
	private String status_code;
	private String memo;
	private Date create_datetime;
	private Date update_datetime;
	
	public TaskBean() {
		
	}
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
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getCreate_datetime() {
		return create_datetime;
	}
	public void setCreate_datetime(Date create_datetime) {
		this.create_datetime = create_datetime;
	}
	public Date getUpdate_datetime() {
		return update_datetime;
	}
	public void setUpdate_datetime(Date update_datetime) {
		this.update_datetime = update_datetime;
	}

}
