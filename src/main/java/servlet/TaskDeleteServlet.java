package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.TaskDeleteDAO;

/**
 * Servlet implementation class TaskDeleteServlet
 */
@WebServlet("/TaskDeleteServlet")
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
	 * タスク一覧から選んだタスクを、削除確認画面で表示するための情報を渡す。
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//変数等の宣言
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		
		/*
		 * 
		 * ここに選択したタスクの情報を取得する処理を実装
		 * 
		 */
		
		//セッションに選択したタスクIDを格納
		session.setAttribute("task_id", task_id);
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-delete-confirm.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * タスク削除確認後、タスクを削除し、削除が正しく完了したか判定して画面遷移する。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		String url = "task-delete-result";
		boolean error = true;
		if (count != 0) {
			error = false;
		}
		request.setAttribute("error", error);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
