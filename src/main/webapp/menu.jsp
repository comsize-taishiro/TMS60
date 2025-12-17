<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>

</head>
<body>
<form action="task-registration-servlet" method="POST">
		<h1>メニュー画面</h1>
		<hr>
		ようこそ！<%
		String name = (String) session.getAttribute("name");%>
		
		<%=name%>さん<br>
		<br>
		<input type="submit" value="タスク登録"><br>
	</form>
	
	<form action="task-list-servlet" method="POST">
		<br>
		<input type="submit" value="タスク一覧表示"><br>
	</form>
	
	<form action="logout-success.jsp" method="POST">
		<br>
		<input type="submit" value="ログアウト"><br>
		<hr>
	</form>

</body>
</html>