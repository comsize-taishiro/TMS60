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

import model.dao.CommentDAO;
import model.dao.SelectsDAO;
import model.entity.CategoryBean;
import model.entity.CommentBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskCommentSurvlet
 */
@WebServlet("/task-comment-servlet")
public class TaskCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int task_id = Integer.parseInt(request.getParameter("task_id")); // 本番
		//int task_id = 1; // デバッグ

		session.setAttribute("task_id", task_id);

		List<CategoryBean> catList = new ArrayList<>();
		List<UserBean> userList = new ArrayList<>();
		List<StatusBean> statList = new ArrayList<>();

		//DAO生成
		SelectsDAO dao1 = new SelectsDAO();
		//DAOのメソッドにtask_idを渡し、そのidのレコードを取得。selectedTaskに格納。
		//レコードのカテゴリIDをカテゴリ名、ユーザIDをユーザ名、ステータスコードをステータス名に変換
		//変換した情報を渡す
		TaskBean selectedTask = null;
		try {
			selectedTask = dao1.selectTask(task_id);
			catList = dao1.selectAllCategory();
			userList = dao1.selectAllUser();
			statList = dao1.selectAllStatus();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		session.setAttribute("selectedTask", selectedTask);
		session.setAttribute("catList", catList);
		session.setAttribute("userList", userList);
		session.setAttribute("statList", statList);

		//タスクのコメント一覧を取得する。
		CommentDAO dao2 = new CommentDAO();
		List<CommentBean> comments = new ArrayList<>();
		try {
			comments = dao2.Select(selectedTask.getTask_id());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("comments", comments);

		String user_name = (String) session.getAttribute("name");
		String user_id = null;
		for (UserBean u : userList) {
			if (u.getUser_name().equals(user_name)) {
				user_id = u.getUser_id();
			}
		}
		request.setAttribute("user_id", user_id);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-comment.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		List<CategoryBean> catList = (List) session.getAttribute("catList");
		List<UserBean> userList = (List) session.getAttribute("userList");
		List<StatusBean> statList = (List) session.getAttribute("statList");

		int task_id = (int) session.getAttribute("task_id");
		String user_name = (String) session.getAttribute("name");
		String user_id = null;
		for (UserBean u : userList) {
			if (u.getUser_name().equals(user_name)) {
				user_id = u.getUser_id();
			}
		}
		request.setAttribute("user_id", user_id);

		TaskBean selectedTask = null;

		//現在選択しているタスクの再取得
		SelectsDAO dao1 = new SelectsDAO();
		try {
			selectedTask = dao1.selectTask(task_id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		session.setAttribute("selectedTask", selectedTask);

		//新しいコメントの登録
		CommentDAO dao2 = new CommentDAO();

		CommentBean newComment = new CommentBean();
		newComment.setTask_id(task_id);
		newComment.setUser_id(user_id);
		newComment.setComment(request.getParameter("new_comment"));

		int count = 0;
		int UpdateStatus = Integer.parseInt(request.getParameter("UpdateStatus"));

		try {
			if (UpdateStatus == -1) {
				count = dao2.Insert(newComment);
			} else {
				count = dao2.Delete(UpdateStatus);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		boolean error = false;
		if (count == 0) {
			error = true;
		}

		//コメント一覧の更新
		List<CommentBean> comments = new ArrayList<>();
		try {
			comments = dao2.Select(selectedTask.getTask_id());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("comments", comments);
		request.setAttribute("error", error);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-comment.jsp");
		rd.forward(request, response);
	}

}
