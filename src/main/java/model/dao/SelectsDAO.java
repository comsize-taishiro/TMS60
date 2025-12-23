package mode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mode.entity.CategoryBean;
import mode.entity.StatusBean;
import mode.entity.TaskBean;
import mode.entity.UserBean;

public class SelectsDAO {
	public TaskBean selectTask(int task_id)
			throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM t_task WHERE task_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, task_id);
			ResultSet res = pstmt.executeQuery();
			res.next();
			TaskBean result = new TaskBean();
			result.setTask_id(task_id);
			result.setTask_name(res.getString("task_name"));
			result.setCategory_id(res.getInt("category_id"));
			result.setLimit_date(res.getDate("limit_date").toLocalDate());
			result.setUser_id(res.getString("user_id"));
			result.setStatus_code(res.getString("status_code"));
			result.setMemo(res.getString("memo"));

			return result;
		}
	}

	public List<CategoryBean> selectAllCategory() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM m_category;";
		List<CategoryBean> result = new ArrayList<>();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				CategoryBean cat = new CategoryBean();
				cat.setCategory_id(res.getInt("category_id"));
				cat.setCategory_name(res.getString("category_name"));
				result.add(cat);
			}
		}
		return result;
	}

	public List<UserBean> selectAllUser() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM m_user;";
		List<UserBean> result = new ArrayList<>();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				UserBean user = new UserBean();
				user.setUser_id(res.getString("user_id"));
				user.setPassword(res.getString("password"));
				user.setUser_name(res.getString("user_name"));
				result.add(user);
			}
		}
		return result;
	}

	public List<StatusBean> selectAllStatus() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM m_status;";
		List<StatusBean> result = new ArrayList<>();
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				StatusBean stat = new StatusBean();
				stat.setStatus_code(res.getString("status_code"));
				stat.setStatus_name(res.getString("status_name"));
				result.add(stat);
			}
		}
		return result;
	}
}
