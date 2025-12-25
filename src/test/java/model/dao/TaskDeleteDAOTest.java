package model.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

class TaskDeleteDAOTest {

	@Test
	void test() {
		TaskRegistrationDAO regiDao = new TaskRegistrationDAO();
		
		TaskBean task = new TaskBean();

		//テスト用編集データの入力
		String task_name = "テストダミー";
		int category_id = 1;
		LocalDate limit_date = LocalDate.parse("2025-12-30", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String user_id = "root";
		String status_code = "00";
		String memo = "削除用テスト登録";
		
		task.setTask_name(task_name);
		task.setCategory_id(category_id);
		task.setLimit_date(limit_date);
		task.setUser_id(user_id);
		task.setStatus_code(status_code);
		task.setMemo(memo);
		
		try {
			int count = regiDao.insert(task);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		TaskDeleteDAO deleDao = new TaskDeleteDAO();
		TaskListDAO listDao = new TaskListDAO();
		
		int task_id = -1;
		
		try {
			List<TaskBean> list = listDao.selectAll();
			for(TaskBean t : list) {
				if(t.getTask_name().equals(task_name)) {
					task_id = t.getTask_id();
				}
			}
			deleDao.Delete(task_id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		SelectsDAO seleDao = new SelectsDAO();
		
		try {
			assertNull(seleDao.selectTask(task_id));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
	}

}
