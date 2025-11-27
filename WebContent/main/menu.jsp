<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="bean.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .header {
            text-align: center;
            border-bottom: 2px solid #007bff;
            padding-bottom: 20px;
            margin-bottom: 30px;
        }
        h1 {
            color: #333;
            margin: 0;
        }
        .user-info {
            text-align: right;
            color: #666;
            margin-bottom: 20px;
            font-size: 14px;
        }
        .menu-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 15px;
            margin-bottom: 30px;
        }
        .menu-item {
            padding: 20px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
            text-align: center;
            transition: all 0.3s ease;
        }
        .menu-item:hover {
            background-color: #e7f3ff;
            border-color: #007bff;
            box-shadow: 0 2px 8px rgba(0,123,255,0.2);
        }
        .menu-item a {
            display: block;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
            font-size: 16px;
        }
        .menu-item a:hover {
            color: #0056b3;
        }
        .menu-item p {
            margin: 10px 0 0 0;
            color: #666;
            font-size: 13px;
        }
        .footer {
            text-align: center;
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #ddd;
        }
        .logout-btn {
            background-color: #dc3545;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            text-decoration: none;
            display: inline-block;
            transition: background-color 0.3s ease;
        }
        .logout-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>メニュー画面</h1>
        </div>

        <div class="user-info">
            <% if (user != null) { %>
                ログイン中: <strong><%= user.getName() %></strong> さん
            <% } %>
        </div>

        <div class="menu-grid">
            <div class="menu-item">
                <a href="#">出勤登録</a>
                <p>本日の出勤を登録します</p>
            </div>

            <div class="menu-item">
                <a href="#">退勤登録</a>
                <p>本日の退勤を登録します</p>
            </div>

            <div class="menu-item">
                <a href="#">勤務時間確認</a>
                <p>勤務時間を確認します</p>
            </div>

            <div class="menu-item">
                <a href="#">休暇申請</a>
                <p>休暇を申請します</p>
            </div>

            <div class="menu-item">
                <a href="#">勤務表</a>
                <p>月間勤務表を表示します</p>
            </div>

            <div class="menu-item">
                <a href="#">設定</a>
                <p>ユーザー設定を変更します</p>
            </div>
        </div>

        <div class="footer">
            <form action="logout.jsp" method="post" style="display: inline;">
                <button type="submit" class="logout-btn">ログアウト</button>
            </form>
        </div>
    </div>
</body>
</html>