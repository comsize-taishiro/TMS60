package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

public class UserDAO {

	public UserBean search(String user, String password) throws SQLException, ClassNotFoundException {

		UserBean userdata = null;

		String sql = "SELECT user_id,password,user_name FROM m_user WHERE user_id = ? AND password =?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, user);
			pstmt.setString(2, password);
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				//String id = res.getString("user_id");
				//String name = res.getString("user_name");

				userdata = new UserBean();
				userdata.setUser_name(res.getString("user_name"));

			}

		}

		return userdata;
	}

}
