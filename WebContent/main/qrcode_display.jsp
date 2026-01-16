<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.UUID" %>
<%@ page import="com.google.zxing.BarcodeFormat" %>
<%@ page import="com.google.zxing.MultiFormatWriter" %>
<%@ page import="com.google.zxing.client.j2se.MatrixToImageWriter" %>
<%@ page import="com.google.zxing.common.BitMatrix" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>

<%
    // トークンがセッションに存在しなければ新規生成
    if (session.getAttribute("qrToken") == null) {
        String token = UUID.randomUUID().toString();
        session.setAttribute("qrToken", token);
    }
    String token = (String) session.getAttribute("qrToken");
    String qrUrl = "http://localhost:8080/attendsystem/verify?token=" + token;
    
    // QRコードを生成してBase64エンコード
    String qrImageBase64 = "";
    try {
        BitMatrix matrix = new MultiFormatWriter().encode(
                qrUrl, 
                BarcodeFormat.QR_CODE, 
                200, 
                200
        );
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        qrImageBase64 = Base64.getEncoder().encodeToString(imageBytes);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>QRコード表示</title>
    
    
    
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
            background-color: #f5f5f5;
        }

        .container {
            background-color: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            color: #333;
            margin-top: 0;
        }

        .qr-container {
            margin: 30px 0;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            border: 2px solid #ddd;
        }

        .qr-container img {
            max-width: 300px;
            height: auto;
            border: 1px solid #ccc;
            padding: 10px;
            background-color: white;
        }

        .token-info {
            margin-top: 20px;
            padding: 15px;
            background-color: #e8f4f8;
            border-left: 4px solid #0288d1;
            border-radius: 4px;
            text-align: left;
        }

        .token-info label {
            font-weight: bold;
            color: #0288d1;
        }

        .token-info span {
            display: block;
            word-break: break-all;
            margin-top: 5px;
            font-family: monospace;
            font-size: 12px;
            color: #333;
        }

        button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #0288d1;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }

        button:hover {
            background-color: #0277bd;
        }

        .refresh-info {
            margin-top: 20px;
            padding: 15px;
            background-color: #fff3e0;
            border-left: 4px solid #ff9800;
            border-radius: 4px;
            text-align: left;
            font-size: 14px;
            color: #e65100;
        }

        .timer {
            font-weight: bold;
            color: #ff6f00;
        }
    </style>
</head>
<body>
	<div id="header"></div>
	
    <div class="container">
        <h1>QRコード表示</h1>

        <div class="qr-container">
            <p>以下のQRコードをスキャンしてください：</p>
            <img src="data:image/png;base64,<%= qrImageBase64 %>" alt="QRコード">
        </div>
		<script src="/attendsystem/js/header.js"></script>
		<script src="/attendsystem/js/footer.js"></script>
        <div class="token-info">
            <label>生成されたトークン：</label>
            <span><%= token %></span>
        </div>

        <div class="token-info">
            <label>QRコードのURL：</label>
            <span><%= qrUrl %></span>
        </div>

        <button onclick="location.reload()">新しいQRコードを生成</button>

        <div class="refresh-info">
            QRコードは <span class="timer" id="timer">60</span> 秒ごとに自動更新されます
        </div>
    </div>

    <script>
        // 自動更新設定（ミリ秒単位）
        // 60秒ごとにページをリロードしてトークンを更新
        const REFRESH_INTERVAL = 60000; // 60000ミリ秒 = 60秒
        let remainingTime = REFRESH_INTERVAL / 1000;

        function updateTimer() {
            remainingTime--;
            const timerElement = document.getElementById('timer');
            if (timerElement) {
                timerElement.textContent = remainingTime;
            }
            
            if (remainingTime <= 0) {
                location.reload();
            }
        }

        // ページ読み込み時にタイマー開始
        window.addEventListener('load', function() {
            setInterval(updateTimer, 1000);
        });
    </script>
</body>
</html>