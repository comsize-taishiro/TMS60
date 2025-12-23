package mode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import mode.entity.TaskBean;

public class TaskRegistrationDAO {
	public int insert(TaskBean task) throws SQLException, ClassNotFoundException {
		//件数0件
		int count = 0;
		String sql = "INSERT INTO m_task(task_name,category_name,limit_date, user_id, status_code, memo )VALUES(?, ?, ?)";

		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
		//DBから取り出し
		String task_name = task.getTask_name();
		String category_code = task.getTask_name();
		Date limit_date = task.getLimit_date();
		String user_id = task.getUser_id();
		String status_code = task.getStatus_code();
		String memo = task.getMemo();
		
		//プレスホルダーへの値の設定
		
		
		

		}
		return count;
	}
	

}
