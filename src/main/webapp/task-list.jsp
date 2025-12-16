<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧表示</title>
</head>
<body>
	<h2>タスク一覧表示</h2>

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
		</tr>
	</table>
	
	<br>
	
	<form action = "task-list-servlet" method = "POST">
		<input type="submit" value="編集する">
	</form>
	
	<form action = "menu.jsp" method = "POST">
		<input type="submit" value="メニュー画面へ">
	</form>
</body>
</html>