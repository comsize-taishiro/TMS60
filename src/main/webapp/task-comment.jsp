<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList, java.util.List, 
	model.entity.CategoryBean, model.entity.StatusBean, 
	model.entity.UserBean, model.entity.TaskBean, model.entity.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント一覧</title>
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
				<td><%=selectedTask.getLimit_date()%></td>
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
	</table>

	<table border=1>
		<tr>
			<th>No</th>
			<th>投稿者</th>
			<th>コメント</th>
			<th>投稿日時</th>
			<th>削除</th>
		</tr>
		<%
		List<CommentBean> comments = (List) request.getAttribute("comments");
		for (CommentBean comment : comments) {
		%>
		<tr>
			<td><%=comment.getComment_id()%></td>
			<%
			String commentUser = null;
			for (UserBean u : userList) {
				if (u.getUser_id().equals(comment.getUser_id())) {
					commentUser = u.getUser_name();
				}
			}
			%>
			<td><%=commentUser%></td>
			<td><%=comment.getComment()%></td>
			<td><%=comment.getUpdate_datetime()%></td>
			<td>
				<form action="task-comment-servlet" method="POST">
					<input type="hidden" name="UpdateStatus"
						value="<%=comment.getComment_id()%>">
					<!-- valueが-1の場合は投稿、それ以外の場合は値＝コメントidで削除 -->
					<% if(comment.getUser_id().equals((String)request.getAttribute("user_id"))){ %>
					<input type="submit" value="削除">
					<%} %>
					<!-- そもそもユーザーと投稿主が一致しなければボタン出さない -->
				</form>
			</td>
		</tr>
		<%
		}
		%>
	</table>

	<form action="task-comment-servlet" method="POST">

		<input type="hidden" name="UpdateStatus" value="-1">
		<!-- valueが-1の場合は投稿、それ以外の場合は値＝コメントidで削除 -->

		<textarea name="new_comment"></textarea><br> <input
			type="submit" value="投稿する"> <input type="reset" value="リセット">
	</form>
	<br>
	<br>
	<form action="task-list-servlet" method="POST">
		<br>
		<input type="submit" value="タスク一覧表示へ戻る"><br>
	</form>
</body>
</html>