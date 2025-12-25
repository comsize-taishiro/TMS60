<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.time.LocalDate, model.entity.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>タスク登録画面</title>
</head>
<body>
    <h2>タスク登録画面</h2>
    <%//データ取得
    List<CategoryBean> catList = (List<CategoryBean>) session.getAttribute("catList");
    List<UserBean> userList = (List<UserBean>) session.getAttribute("userList");
    List<StatusBean> statList = (List<StatusBean>) session.getAttribute("statList");
    String errorMsg = (String) request.getAttribute("errorMsg");
    %>

    <% if (errorMsg != null) { %>
        <p>【登録エラー】 <%= errorMsg %></p>
    <% } %>

    <form action="task-registration-servlet" method="POST">
        <table border="1">
            <tr>
                <th>タスク名</th>
                <td><input type="text" name="task_name" value=""></td> 
            </tr>
            <tr>
                <th>カテゴリ情報</th>
                <td>
                    <select name="category_id">
                        <% if (catList != null) { 
                            for (int i = 0; i < catList.size(); i++) { 
                                CategoryBean c = catList.get(i); // i番目を取り出す
                        %>
                            <option value="<%= c.getCategory_id() %>"><%= c.getCategory_name() %></option>
                        <% } } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th>期限</th>
                <td><input type="date" name="limit_date" min="<%= LocalDate.now() %>" value=""></td>
            </tr>   
            <tr>
                <th>担当者情報</th>
                <td>
                    <select name="user_id">
                        <% if (userList != null) { 
                            for (int i = 0; i < userList.size(); i++) { 
                                UserBean u = userList.get(i);
                        %>
                            <option value="<%= u.getUser_id() %>"><%= u.getUser_name() %></option>
                        <% } } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th>ステータス情報</th> 
                <td>
                    <select name="status_code">
                        <% if (statList != null) { 
                            for (int i = 0; i < statList.size(); i++) { 
                                StatusBean s = statList.get(i);
                        %>
                            <option value="<%= s.getStatus_code() %>"><%= s.getStatus_name() %></option>
                        <% } } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th>メモ</th> 
                <td><input type="text" name="memo" value=""></td>
            </tr>
            <tr>
                <td><input type="submit" value="登録実行"></td>
                <td><input type="reset" value="リセット"></td>
            </tr>
        </table>
    </form>
    <br>
    <form action="menu.jsp" method="POST">
        <input type="submit" value="戻る">
    </form>
</body>
</html>