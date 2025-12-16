<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク登録画面</title>
</head>
<body>
	<h1>タスク登録画面</h1>

	<form action="task-registration-servlet" method="POST">


		①タスク名 <input type="text" name="task_name"><br> 
		②カテゴリ情報 <input type="text" name="category_name"><br> 
		③期限<input type="text" name="date"><br> 
		④担当者情報 <input type="text" name="user_id"> <br>
		⑤ステータス情報 <select name="status_code">
			<option value="00">00</option>
			<%
			// 00～99を出力
			for (int i = 0; i <= 9; i++) {
				for (int j = 0; j <= 9; j++) {
			%>
			<option value="<%=i + "" + j%>">
				<%=i + "" + j%>
			</option>

			<%
			}
			}
			%>

		</select><br> 
		
		⑥メモ <input type="text" name="memo"><br> 
		<input type="submit" value="登録実行">
	</form>

	<form action="" method="POST">
		<input type="submit" value="戻る">
	</form>


</body>
</html>