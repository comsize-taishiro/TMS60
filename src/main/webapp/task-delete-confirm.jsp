<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, java.util.List, 
	model.entity.CategoryBean, model.entity.StatusBean, 
	model.entity.UserBean, model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク削除確認画面</title>
</head>
<body>
	<%
	//編集対象として選択されたタスク
	TaskBean selectedTask = (TaskBean) session.getAttribute("selectedTask");
	%>
	<table>
		<form action="task-delete-servlet" method="POST">
			<tr>
				<th>タスク名</th>
				<td><%=selectedTask.getTask_name()%></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>

				<%
				String category_name = null;
				List<CategoryBean> catList = (List) session.getAttribute("catList");
				for (CategoryBean c : catList) {
					if (c.getCategory_id() == selectedTask.getCategory_id()) {
						category_name = c.getCategory_name();
					}
				}
				%>
				<td><%=category_name%></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date"
					value="<%=selectedTask.getLimit_date()%>"></td>
			</tr>
			<tr>
				<th>担当者情報</th>

				<%
				String user_name = null;
				List<UserBean> userList = (List) session.getAttribute("userList");
				for (UserBean u : userList) {
					if (u.getUser_id().equals(selectedTask.getUser_id())) {
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
					if (s.getStatus_code().equals(selectedTask.getStatus_code())) {
						status_code = s.getStatus_name();
					}
				}
				%>
				<td><%=status_code%></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><%=selectedTask.getMemo()%></td>
			</tr>
			<tr>
				<td><input type="submit" value="削除する">
		</form>
		</td>

		<td><form action="task-list.jsp" method="POST">
				<input type="submit" value="キャンセル">
			</form></td>
		</tr>
	</table>
</body>
</html>