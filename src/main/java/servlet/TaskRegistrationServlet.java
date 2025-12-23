package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.TaskRegistrationDAO;
import model.entity.TaskBean;

/**
 * Servlet implementation class TaskRegistrationServlet
 */
@WebServlet("/task-registration-servlet")
public class TaskRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//入力された値を受け取る
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 登録されたタスク情報を受け取るメソッド
		TaskRegistrationDAO dao = new TaskRegistrationDAO();
		
		//転送の変数を宣言
		String register = null;
		
		//エンコーディング
		request.setCharacterEncoding("UTF-8");
		//受け取り
		String task_name = request.getParameter("task_name");
		int category_id = Integer.parseInt(request.getParameter("category_id"));
		LocalDate limit_date = LocalDate.parse(request.getParameter("limit_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String user_id = request.getParameter("user_id");
		String status_code = request.getParameter("status_code");
		String memo = request.getParameter("memo");
		
		//値を格納するBeanをインスタンス化　
		TaskBean task = new TaskBean();
		
		//セッターに
		task.setTask_name(task_name);
		task.setCategory_id(category_id);
		task.setLimit_date(limit_date);
		task.setUser_id(user_id);
		task.setStatus_code(status_code);
		task.setMemo(memo);
		
		//処理件数
		int count = 0;
		
		try {
			// DAOの利用
			count = dao.insert(task);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		//登録成功とエラー
		
		if (count != 0) {
			// 登録成功時の転送先
			register = "registration-success.jsp";
		} else {
			// 登録失敗時の転送先
			//register = "registration-failure.jsp";
		}

		// リクエストの転送
				RequestDispatcher rd = request.getRequestDispatcher(register);
				rd.forward(request, response);
		
	}

}
