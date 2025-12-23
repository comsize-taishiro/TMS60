package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.SelectsDAO;
import model.dao.TaskDeleteDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskDeleteServlet
 */
@WebServlet("/task-delete-servlet")
public class TaskDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//変数等の宣言
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//int task_id = Integer.parseInt(request.getParameter("task_id")); // 本番
		int task_id = 1; // デバッグ

		//セッションに選択したタスクIDを格納
		session.setAttribute("task_id", task_id);

		List<CategoryBean> catList = new ArrayList<>();
		List<UserBean> userList = new ArrayList<>();
		List<StatusBean> statList = new ArrayList<>();

		//DAO生成
		SelectsDAO dao = new SelectsDAO();
		//DAOのメソッドにtask_idを渡し、そのidのレコードを取得。selectedTaskに格納。
		//レコードのカテゴリIDをカテゴリ名、ユーザIDをユーザ名、ステータスコードをステータス名に変換
		//変換した情報を渡す
		TaskBean selectedTask = null;
		try {
			selectedTask = dao.selectTask(task_id);
			catList = dao.selectAllCategory();
			userList = dao.selectAllUser();
			statList = dao.selectAllStatus();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		session.setAttribute("selectedTask", selectedTask);
		session.setAttribute("catList", catList);
		session.setAttribute("userList", userList);
		session.setAttribute("statList", statList);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-delete-confirm.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//変数等の宣言
		request.setCharacterEncoding("UTF-8");
		TaskDeleteDAO dao = new TaskDeleteDAO();
		int count = 0;
		HttpSession session = request.getSession();

		//選択されたタスクのIDをセッションから取得、DAOのDeleteメソッドに渡して削除を行う。
		int task_id = (int) session.getAttribute("task_id");
		try {
			count = dao.Delete(task_id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//タスクの削除がうまくいったかどうかによって、遷移先に送るエラー情報の可否を決める
		boolean error = true;
		if (count != 0) {
			error = false;
		}
		request.setAttribute("error", error);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-delete-result.jsp");
		rd.forward(request, response);
	}

}
