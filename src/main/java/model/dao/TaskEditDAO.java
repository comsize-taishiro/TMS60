package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.TaskBean;

public class TaskEditDAO {
	public int updateTask(TaskBean task)
			throws SQLException, ClassNotFoundException, NumberFormatException, IllegalArgumentException {
		String sql = "UPDATE t_task SET task_name = ?, "
				+ "category_id = ?, limit_date = ?, "
				+ "user_id = ?, status_code = ?, memo = ? WHERE task_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, task.getTask_name());
			pstmt.setInt(2, task.getCategory_id());
			pstmt.setDate(3, Date.valueOf(task.getLimit_date()));
			pstmt.setString(4, task.getUser_id());
			pstmt.setString(5, task.getStatus_code());
			pstmt.setString(6, task.getMemo());
			pstmt.setInt(7, task.getTask_id());
			int result = pstmt.executeUpdate();

			return result;
		}
	}
}
