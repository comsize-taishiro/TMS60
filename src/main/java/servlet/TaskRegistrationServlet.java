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

import mode.dao.SelectsDAO;
import mode.dao.TaskRegistrationDAO;
import mode.entity.CategoryBean;
import mode.entity.StatusBean;
import mode.entity.TaskBean;
import mode.entity.UserBean;

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
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		List<CategoryBean> catList = new ArrayList<>();
		List<UserBean> userList = new ArrayList<>();
		List<StatusBean> statList = new ArrayList<>();

		//DAO生成
		SelectsDAO dao = new SelectsDAO();
		//レコードのカテゴリIDをカテゴリ名、ユーザIDをユーザ名、ステータスコードをステータス名に変換
		//変換した情報を渡す
		try {
		    catList = dao.selectAllCategory();
		    userList = dao.selectAllUser();
		    statList = dao.selectAllStatus();
		    
		} catch (ClassNotFoundException | SQLException e) {
		    e.printStackTrace();
		}
		// 新規登録なので selectedTask は空の状態で渡す（Nullで送るとJSPでエラーになる）
	    TaskBean selectedTask = new TaskBean();

		session.setAttribute("selectedTask", selectedTask);
		session.setAttribute("catList", catList);
		session.setAttribute("userList", userList);
		session.setAttribute("statList", statList);

		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("task-registration.jsp");
		rd.forward(request, response);

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
		//空白パターン
	    String blankDate = request.getParameter("limit_date");
	    
	    // 2. 未入力チェック
	    if (blankDate == null || blankDate.isEmpty()) {
	        request.setAttribute("errorMsg", "期限の日付を入力してください。");
	        doGet(request, response);
	        return;
	    }
		
		//受け取り
		LocalDate limit_date = LocalDate.parse(request.getParameter("limit_date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (limit_date.isBefore(LocalDate.now())) {
	        // (過去日)だった時のエラー文スコープセット
	        request.setAttribute("errorMsg", "過去の日付は登録できません。");
	        
	        //doGetを呼び出して、プルダウンリストの再準備とJSPへの転送を行う
	        doGet(request, response);
	        //登録させない
	        return;
		}
		
		String task_name = request.getParameter("task_name");
		int category_id = Integer.parseInt(request.getParameter("category_id"));
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
			register = "registration-failure.jsp";
		}

			// リクエストの転送
				RequestDispatcher rd = request.getRequestDispatcher(register);
				rd.forward(request, response);
				
	}

}
