<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h1>ログイン画面</h1>
<hr>
<form action="login-servlet" method="POST">
		<h1>ユーザIDとパスワードを入力してください。</h1><br>
		ユーザID<input type="text" name="user"><br>
		パスワード<input type="password" name="password"><br>
		<br>
		<input type="submit" value="ログイン">
		<input type="reset" value="取消">
	</form>
	
	

</body>
</html>