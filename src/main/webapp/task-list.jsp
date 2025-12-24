<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.TaskBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧表示</title>
</head>
<body>
	<h2>タスク一覧表示</h2>
	<%
	List<TaskBean> taskList = (List) request.getAttribute("taskList");
		%>

	<table border=1>
		<tr>
			<th>タスク名</th>
			<th>カテゴリ情報</th>
			<th>期限</th>
			<th>担当者情報</th>
			<th>ステータス情報</th>
			<th>メモ</th>
			<th>編集</th>
			<th>削除</th>
			<th>コメント追加</th>
		</tr>
		<%
			for (TaskBean list : taskList ) {
		%>
		<tr>
			<td><%=list.getTask_name()%></td>
			<td><%=list.getCategory_name()%></td>
			<td><%=list.getLimit_date()%></td>
			<td><%=list.getUser_name()%></td>
			<td><%=list.getStatus_name()%></td>
			<td><%=list.getMemo()%></td>
			<td><form action = "task-edit-servlet" method = "GET">
			<input type="hidden" name="task_id" value="<%=list.getTask_id()%>">
			<input type = "submit" value = "編集">
			</form></td>
			<td><form action = "task-delete-servlet" method = "GET">
			<input type="hidden" name="task_id" value="<%=list.getTask_id()%>">
			<input type = "submit" value = "削除">
			</form></td>
			<td><form action = "task-comment-servlet" method = "GET">
			<input type="hidden" name="task_id" value="<%=list.getTask_id()%>">
			<input type = "submit" value = "コメント">
			</form></td>
		</tr>
		
		<%
			}
		%>
	</table>
	
	<br>
	<form action = "menu.jsp" method = "POST">
		<input type="submit" value="メニュー画面へ">
	</form>
</body>
</html>