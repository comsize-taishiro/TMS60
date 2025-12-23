<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, java.util.List, 
	model.entity.CategoryBean, model.entity.StatusBean, 
	model.entity.UserBean, model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編集結果</title>
</head>
<body>
	<%
	TaskBean newTask = (TaskBean) request.getAttribute("newTask");

	boolean error = (boolean) request.getAttribute("error");
	if (error) {
	%>
	<h1>タスクの編集が失敗しました。</h1>
	<%
	} else {
	%>
	<h1>タスクの編集が成功しました。</h1>
	<%
	}
	%>

	<table>
		<tr>
			<th>タスク名</th>
			<td><%=newTask.getTask_name()%></td>
		</tr>
		<tr>
			<th>カテゴリ情報</th>
			<%
			String category_name = null;
			List<CategoryBean> catList = (List) session.getAttribute("catList");
			for (CategoryBean c : catList) {
				if (c.getCategory_id() == newTask.getCategory_id()) {
					category_name = c.getCategory_name();
				}
			}
			%>
			<td><%=category_name%></td>
		</tr>
		<tr>
			<th>期限</th>
			<td><%=newTask.getLimit_date()%></td>
		</tr>
		<tr>
			<th>担当者情報</th>
			<%
			String user_name = null;
			List<UserBean> userList = (List) session.getAttribute("userList");
			for (UserBean u : userList) {
				if (u.getUser_id().equals(newTask.getUser_id())) {
					user_name = u.getUser_name();
				}
			}
			%>
			<td><%=user_name%></td>
		</tr>
		<tr>
			<th>ステータス情報</th>
			<%
			String status_code = null;
			List<StatusBean> statList = (List) session.getAttribute("statList");
			for (StatusBean s : statList) {
				if (s.getStatus_code().equals(newTask.getStatus_code())) {
					status_code = s.getStatus_name();
				}
			}
			%>
			<td><%=status_code%></td>
		</tr>
		<tr>
			<th>メモ</th>
			<td><%=newTask.getMemo()%></td>
		</tr>
		<tr>
			<td colspan="2">
				<form action="menu.jsp" method="POST">
					<input type="submit" value="メニューへ戻る">
				</form>
			</td>
		</tr>
	</table>
	<%
	session.removeAttribute("catList");
	session.removeAttribute("userList");
	session.removeAttribute("statList");
	session.removeAttribute("task_id");
	%>
</body>
</html>