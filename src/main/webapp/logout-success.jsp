<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト画面</title>
</head>
<body>
<h1>ログアウト画面</h1>
<hr>
<form action="login.jsp" method="POST">
		お疲れ様でした！
		<%HttpSession session2 = request.getSession();
		String name = (String) session2.getAttribute("name");
		session2.invalidate();%>
		
		<%=name%>さん<br>
		<h1>ログアウトしました。</h1><br>
		<input type="submit" value="ログイン画面へ">
		<br>
		<hr>
	</form>
</body>
</html>