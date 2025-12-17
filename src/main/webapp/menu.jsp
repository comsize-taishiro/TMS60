<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>

</head>
<body>
<form action="task-list-servlet" method="POST">
		<h1>メニュー画面</h1>
		<hr>
		ようこそ！<%
		String name = (String) session.getAttribute("name");%>
		
		<%=name%>さん<br>
		<br>
		<input type="submit" value="商品一覧"><br>
	</form>
	
	<form action="item-add-servlet" method="GET">
		<br>
		<input type="submit" value="商品登録"><br>
	</form>
	
	<form action="logout-success.jsp" method="POST">
		<br>
		<input type="submit" value="ログアウト"><br>
	</form>

</body>
</html>