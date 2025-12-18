<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, java.util.List, 
	model.entity.CategoryBean, model.entity.StatusBean, 
	model.entity.UserBean, model.entity.TaskBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク編集画面</title>
</head>
<body>
	<%
	//編集対象として選択されたタスク
	TaskBean selectedTask = (TaskBean) session.getAttribute("selectedTask");
	%>
	<form action="task-edit-servlet" method="POST">
		<table>
			<tr>
				<th>タスク名</th>
				<td><input type="text" name="task_name"
					value="<%=selectedTask.getTask_name()%>"></td>
			</tr>
			<tr>
				<th>カテゴリ情報</th>
				<td><select name="category_id">
						<%
						List<CategoryBean> catList = (List) session.getAttribute("catList");
						for (CategoryBean c : catList) {
						%>
						<option value="<%=c.getCategory_id()%>"
							<%if (c.getCategory_id() == selectedTask.getCategory_id()) {%>
							selected <%}%>><%=c.getCategory_name()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>期限</th>
				<td><input type="date" name="limit_date"
					value="<%=selectedTask.getLimit_date()%>"></td>
			</tr>
			<tr>
				<th>担当者情報</th>
				<td><select name="user_id">
						<%
						List<UserBean> userList = (List) session.getAttribute("userList");
						for (UserBean u : userList) {
						%>
						<option value="<%=u.getUser_id()%>"
							<%if (u.getUser_id() == selectedTask.getUser_id()) {%> selected
							<%}%>><%=u.getUser_name()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>ステータス情報</th>
				<td><select name="status_code">
						<%
						List<StatusBean> statList = (List) session.getAttribute("statList");
						for (StatusBean s : statList) {
						%>
						<option value="<%=s.getStatus_code()%>"
							<%if (s.getStatus_code() == selectedTask.getStatus_code()) {%>
							selected <%}%>><%=s.getStatus_name()%></option>
						<%
						}
						%>
				</select></td>
			</tr>
			<tr>
				<th>メモ</th>
				<td><input type="text" name="memo"
					value="<%=selectedTask.getMemo()%>"></td>
			</tr>
			<tr>
				<td><input type="submit" value="編集完了"></td>
				<td><input type="reset" value="リセット"></td>
			</tr>
			</form>

			<tr>
				<td colspan="2">
					<form action="task-menu.jsp" method="POST">
						<input type="submit" value="メニューへ">
					</form>
				</td>
			</tr>
		</table>
</body>
</html>