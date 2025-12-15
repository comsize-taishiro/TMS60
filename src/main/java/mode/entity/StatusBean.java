package mode.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StatusBean implements Serializable{
	
	private String status_code;
	private String status_name;
	private LocalDateTime update_datetime;
	
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
	public LocalDateTime getUpdate_datetime() {
		return update_datetime;
	}
	public void setUpdate_datetime(LocalDateTime update_datetime) {
		this.update_datetime = update_datetime;
	}

}
