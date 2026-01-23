<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>出席完了</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            margin-top: 0;
        }

        .message {
            color: #666;
            margin-bottom: 20px;
        }

        .info {
            background-color: #f9f9f9;
            padding: 15px;
            border-left: 4px solid #007bff;
            margin-bottom: 20px;
        }

        .info-row {
            margin: 10px 0;
            font-size: 14px;
        }

        .info-label {
            font-weight: bold;
            color: #555;
        }

        .button-group {
            margin-top: 20px;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin-right: 10px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 3px;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .btn-secondary {
            background-color: #6c757d;
        }

        .btn-secondary:hover {
            background-color: #545b62;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>出席完了</h1>

        <p class="message">出席が完了しました。</p>

        <div class="info">
            <div class="info-row">
                <span class="info-label">ユーザー名:</span>
                <c:out default="user@example.com" value="${sessionScope.userName}"/>
            </div>
            <div class="info-row">
                <span class="info-label">出席時刻:</span>
                <%= new java.text.SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new java.util.Date()) %>
            </div>
	        <form action="../Student/MyDiary.action" method="POST">
    			<button type="submit" class="btn btn-secondary">あなたのページへ</button>
			</form>
        </div>

    </div>
</body>
</html>