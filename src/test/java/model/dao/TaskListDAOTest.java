package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.TaskBean;


class TaskListDAOTest {

    @Test
    void test_リスト取得成功() throws Exception {
        // 準備
        TaskListDAO dao = new TaskListDAO();

        // 実行
        List<TaskBean> bean = dao.selectAll();

        // 検証
        assertEquals(2, bean.size());
    }
}

