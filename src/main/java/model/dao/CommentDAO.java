package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CommentBean;

public class CommentDAO {
	public List<CommentBean> Select(int task_id) throws SQLException, ClassNotFoundException {
		List<CommentBean> result = new ArrayList<>();
		String sql = "SELECT * FROM t_comment WHERE task_id = ?;";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, task_id);
			ResultSet res = pstmt.executeQuery();
			while (res.next()) {
				CommentBean comment = new CommentBean();
				comment.setComment_id(res.getInt("comment_id"));
				comment.setTask_id(res.getInt("task_id"));
				comment.setUser_id(res.getString("user_id"));
				comment.setComment(res.getString("comment"));
				comment.setUpdate_datetime(res.getTimestamp("update_datetime").toLocalDateTime());
				result.add(comment);
			}
		}
		return result;
	}

	public int Insert(CommentBean comment) throws SQLException, ClassNotFoundException {
		int result = 0;
		String sql = "INSERT INTO t_comment(task_id, user_id, comment)"
				+ "VALUES(?, ?, ?)";
		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, comment.getTask_id());
			pstmt.setString(2, comment.getUser_id());
			pstmt.setString(3, comment.getComment());
			result = pstmt.executeUpdate();
		}
		return result;
	}

	public int Delete(int comment_id) throws SQLException, ClassNotFoundException {
		int result = 0;
		String sql = "Delete FROM t_comment WHERE comment_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, comment_id);
			result = pstmt.executeUpdate();
		}
		return result;
	}
}
