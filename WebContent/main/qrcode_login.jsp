<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // セッションからqrVerifiedをチェック
    Boolean qrVerified = (Boolean) session.getAttribute("qrVerified");
    if (qrVerified == null || !qrVerified) {
        response.sendRedirect(request.getContextPath() + "/error.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        }

        .login-container {
            background-color: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-top: 0;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 5px rgba(102, 126, 234, 0.5);
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #667eea;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
        }

        button:hover {
            background-color: #5568d3;
        }

        .qr-verified {
            text-align: center;
            color: #4caf50;
            margin-bottom: 20px;
            padding: 10px;
            background-color: #e8f5e9;
            border-radius: 4px;
        }

        .qr-verified::before {
            content: "✓ ";
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h1>ログイン</h1>
        
        <div class="qr-verified">
            QRコード認証完了
        </div>

        <form method="POST" action="CreateQRcodeExecuteAction">
            <div class="form-group">
                <label for="username">ユーザー名：</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">パスワード：</label>
                <input type="password" id="password" name="password" required>
            </div>

            <button type="submit">ログイン</button>
        </form>
    </div>
</body>
</html>