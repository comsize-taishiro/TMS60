package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;

class TaskEditDAOTest {

	@Test
	void testUpdate() throws NumberFormatException, ClassNotFoundException, IllegalArgumentException, SQLException {
		TaskEditDAO dao = new TaskEditDAO();
		TaskBean task = new TaskBean();

		//テスト用編集データの入力
		int task_id = 1;
		String task_name = "テストA";
		int category_id = 2;
		LocalDate limit_date = LocalDate.parse("2025-12-30", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String user_id = "shirahata";
		String status_code = "50";
		String memo = "テスト後";

		task.setTask_id(task_id);
		task.setTask_name(task_name);
		task.setCategory_id(category_id);
		task.setLimit_date(limit_date);
		task.setUser_id(user_id);
		task.setStatus_code(status_code);
		task.setMemo(memo);

		int count = dao.updateTask(task);

		assertEquals(1, count, "DBの更新件数が0です");

		SelectsDAO dao2 = new SelectsDAO();
		TaskBean taskRef = dao2.selectTask(1);

		assertEquals(taskRef.getTask_id(), task_id);
		assertEquals(taskRef.getTask_name(), task_name);
		assertEquals(taskRef.getCategory_id(), category_id);
		assertEquals(taskRef.getLimit_date(), limit_date);
		assertEquals(taskRef.getUser_id(), user_id);
		assertEquals(taskRef.getStatus_code(), status_code);
		assertEquals(taskRef.getMemo(), memo);

		//リセット
		task.setTask_id(1);
		task.setTask_name("テストA");
		task.setCategory_id(1);
		task.setLimit_date(LocalDate.parse("2026-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		task.setUser_id("root");
		task.setStatus_code("00");
		task.setMemo("テストメモ");

		count = dao.updateTask(task);

	}

}
