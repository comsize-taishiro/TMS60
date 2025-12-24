package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class UserDAOTest {

	@Test
	//void test() {
		//fail("まだ実装されていません");
		
		void testLogin_成功() {
			//準備
			UserDAO dao = new UserDAO();
			String user = "shirahata";
			String password = "shirahata";
			
			UserBean bean = null;
			
			//実行
			try {
				bean = dao.search(user,password);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			//検証
			assertNotNull(bean);
			assertEquals("shirahata",bean.getUser_name());
			
		}
		
		@Test
		void testlogin_失敗() {
			//準備
			UserDAO dao = new UserDAO();
			String user = "shirahama";
			String password = "shirahama";
			
			UserBean bean = null;
			
			//実行
			try {
				bean = dao.search(user,password);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			//検証
			assertNull(bean);
			//↑assertNotNullからassertNullに変更
			//↓assertEqualsメソッドは失敗時はいらない（引っ張ってこれないため）
			//assertEquals("shirahata",bean.getUserName());
		}

	}
