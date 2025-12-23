package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import mode.entity.TaskBean;

public class TaskRegistrationDAO {
	public int insert(TaskBean task) throws SQLException, ClassNotFoundException {
		//件数0件
		int count = 0;
		String sql = "INSERT INTO t_task(task_name,category_id,limit_date, "
				+ "user_id, status_code, memo )"
				+ "VALUES(?, ?, ?, ?, ?, ?)";

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			//DBから取り出し
			String task_name = task.getTask_name();
			int category_id = task.getCategory_id();
			LocalDate limit_date = task.getLimit_date();
			String user_id = task.getUser_id();
			String status_code = task.getStatus_code();
			String memo = task.getMemo();

			//プレスホルダーへの値の設定
			pstmt.setString(1, task_name);
			pstmt.setInt(2, category_id);
			pstmt.setDate(3, java.sql.Date.valueOf(limit_date));
			pstmt.setString(4, user_id);
			pstmt.setString(5, status_code);
			pstmt.setString(6, memo);

			// SQLステートメントの実行
			count = pstmt.executeUpdate();

		}
		return count;
	}

}
