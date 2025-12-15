package mode.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserBean implements Serializable {

	private String user_id;
	private String password;
	private String user_name;
	private LocalDateTime update_datetime;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public LocalDateTime getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(LocalDateTime update_datetime) {
		this.update_datetime = update_datetime;
	}

}
