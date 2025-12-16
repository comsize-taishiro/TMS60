package mode.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import mode.entity.TaskBean;


public class TaskListDAO {
	public List<TaskBean> selectAll()throws SQLException, ClassNotFoundException {
	
	List<TaskBean> taskList = new ArrayList<TaskBean>();
	String sql = "SELECT * FROM t_task";
	
	try (Connection con = ConnectionManager.getConnection();
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql)){
		
			while (res.next()) {
				String name = res.getString("task_name");
				int category = res.getInt("category_id");
				LocalDate date = res.getDate("limit_date").toLocalDate();
				String user = res.getString("user_id");
				String status_code = res.getString("status_code");
				String memo = res.getString("memo");

				TaskBean task = new TaskBean();
				task.setTask_name(name);
				task.setCategory_id(category);
				task.setLimit_date(date);
				task.setUser_id(user);
				task.setStatus_code(status_code);
				task.setMemo(memo);

				taskList.add(task);
			}
		}

	return taskList;
	
	
	}
}
