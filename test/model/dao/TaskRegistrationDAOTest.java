import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.dao.TaskRegistrationDAO;
import model.entity.TaskBean;

class TaskRegistrationDAOTest {

	
	    private TaskRegistrationDAO dao;

	    @BeforeEach
	    void setUp() {
	        // 準備 : 各テスト毎にテスト対象のインスタンスを準備
	        dao = new TaskRegistrationDAO();
	    }
	     //1. 成功ケース（正常系）
	    @Test
	    @DisplayName("正常系：正しいデータを入力した場合、1件の登録に成功すること")
	    void testInsert_Success() throws Exception {
	        // 準備：テスト用データ(yamada)
	        TaskBean task = new TaskBean();
	        task.setTask_name("yamada");
	        task.setCategory_id(1); 
	        task.setLimit_date(LocalDate.now());
	        task.setUser_id("yamada"); 
	        task.setStatus_code("00");
	        task.setMemo("正常登録のテスト");

	        // 実行：DAOのメソッドを呼ぶ
	        int result = dao.insert(task);

	        // 検証：戻り値が1（1件成功）であることを確認
	        assertEquals(1, result, "登録成功時は戻り値が1である必要があります");
	        
	        // ※注意：このテストを実行すると、DBに「JUnitテスト成功」というデータが実際に保存されます。
	    }
	
	    //異常系のテスト：エラーが起きた時に正しく例外が出るか
	    @Test
	    @DisplayName("異常系：タスク名が未入力（null）の場合にエラーが発生すること")
	    void testInsert_Failure() {
	        // 1. 準備：タスク名がnullにする
	        TaskBean task = new TaskBean();
	        task.setTask_name(null); 
	        task.setCategory_id(1);
	        task.setLimit_date(LocalDate.now());
	        task.setUser_id("yamada");
	        task.setStatus_code("00");
	        task.setMemo("異常系のテスト");

	        // 2. 実行 & 検証：
	        // 実行した結果、SQLExceptionが投げられれば「テスト成功」とみなす
	        assertThrows(SQLException.class, () -> {
	            dao.insert(task);
	        }, "タスク名がnullならDB制約エラーが出るはず");
	    
	}

}