package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDeleteDAO {
	public int Delete(int task_id) throws SQLException, ClassNotFoundException {
		int result = 0;
		String sql = "Delete FROM t_task WHERE task_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, task_id);
			result = pstmt.executeUpdate();
		}
		return result;
	}
}
