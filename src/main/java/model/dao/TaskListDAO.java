package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entity.TaskBean;


public class TaskListDAO {
	/*public List<TaskBean> selectAll()throws SQLException, ClassNotFoundException {
	
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
	}*/
	
	public List<TaskBean> selectAll()throws SQLException, ClassNotFoundException {
		
		List<TaskBean> taskList = new ArrayList<TaskBean>();
		String sql = "SELECT t1.task_name,t2.category_name,t1.limit_date,t3.user_name,t4.status_name,t1.memo\r\n"
				+ "			FROM t_task t1 INNER JOIN m_category t2 ON t1.category_id = t2.category_id\r\n"
				+ "				INNER JOIN m_user t3 ON t1.user_id = t3.user_id INNER JOIN m_status t4\r\n"
				+ "				ON t1.status_code = t4.status_code;";
		
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)){
			
				while (res.next()) {
					String name = res.getString("task_name");
					String category_name = res.getString("category_name");
					LocalDate date = res.getDate("limit_date").toLocalDate();
					String user = res.getString("user_name");
					String status_name = res.getString("status_name");
					String memo = res.getString("memo");

					TaskBean task = new TaskBean();
					task.setTask_name(name);
					task.setCategory_name(category_name);
					task.setLimit_date(date);
					task.setUser_name(user);
					task.setStatus_name(status_name);
					task.setMemo(memo);

					taskList.add(task);
				}
			}
		
		
		
		
		return taskList;
		
	}
	
	
	
}




