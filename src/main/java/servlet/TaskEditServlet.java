package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import model.dao.TaskEditDAO;
import model.entity.CategoryBean;
import model.entity.StatusBean;
import model.entity.TaskBean;
import model.entity.UserBean;

/**
 * Servlet implementation class TaskEditServlet
 */
@WebServlet("/task-edit-servlet")
public class TaskEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaskEditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 選択したタスクの情報を入力欄のデフォルト値として渡す
	 * input = 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int task_id = Integer.parseInt(request.getParameter("task_id")); // 本番
		//int task_id = 1; // デバッグ
		
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
		RequestDispatcher rd = request.getRequestDispatcher("task-edit-form.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 入力された情報(Bean)を受け取ってタスクを更新する
	 * input = requestスコープに入って渡されるTaskBeanの要素が複数
	 * output = 更新SQLを走らせた結果をもとに渡される"erroe"(boolean型)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//変数等の宣言
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		TaskEditDAO dao = new TaskEditDAO();

		//requestスコープで渡した編集後タスクデータをDAOで反映
		TaskBean newTask = new TaskBean();
		newTask.setTask_id((int)session.getAttribute("task_id"));
		newTask.setTask_name(request.getParameter("task_name"));
		newTask.setCategory_id(Integer.parseInt(request.getParameter("category_id")));
		newTask.setLimit_date(LocalDate.parse(request.getParameter("limit_date")));
		newTask.setUser_id(request.getParameter("user_id"));
		newTask.setStatus_code(request.getParameter("status_code"));
		newTask.setMemo(request.getParameter("memo"));
		
		request.setAttribute("newTask", newTask);
		
		int count = 0;
		try {
			LocalDate limit_date = LocalDate.parse(request.getParameter("limit_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			if (limit_date.isBefore(LocalDate.now())) {
				count = 0;
			}else {
			count = dao.updateTask(newTask);
			}
		} catch (ClassNotFoundException | IllegalArgumentException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		

		//タスクの編集がうまくいったかどうかによって、遷移先に送るエラー情報の可否を決める
		boolean error = true;
		if (count != 0) {
			error = false;
		}
		request.setAttribute("error", error);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-edit-result.jsp");
		rd.forward(request, response);

	}

}
