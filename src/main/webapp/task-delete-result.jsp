<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除結果画面</title>
</head>
<body>
	<%
	boolean error = (boolean) request.getAttribute("error");
	if (error) {
	%>
	<h1>タスクの削除に失敗しました。</h1>
	<%
	} else {
	%>
	<h1>タスクの削除に成功しました。</h1>
	<%
	}
	%>
	<form action="menu.jsp" method="POST">
		<input type="submit" value="メニューへ戻る">
	</form>
</body>
</html>