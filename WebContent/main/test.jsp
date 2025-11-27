<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>出席管理システム</title>

    <style>
        body { font-family: sans-serif; margin: 0; padding: 0; }
        header { background: #003366; color: white; padding: 15px; }
        nav { background: #f0f0f0; padding: 10px; width: 200px; float: left; }
        nav ul { list-style: none; padding: 0; }
        nav li { margin: 10px 0; }
        nav a { text-decoration: none; color: #333; }
        main { margin-left: 220px; padding: 20px; }
        footer { margin-top: 40px; padding: 10px; background: #eee; text-align: center; }
    </style>
</head>

<body>

<header>
    <h2>出席管理システム</h2>
    <div style="text-align:right;">
        <a href="/attendsystem/main/logout" style="color:white;">ログアウト</a>
    </div>
</header>

<nav>
    <ul>
        <li>メニュー</li>
        <li><a href="/attendsystem/main/classselect.jsp">クラスセレクト</a></li>
        <li><a href="/attendsystem/main/absenceArchive.jsp">欠席アーカイブ</a></li>
        <li><a href="/attendsystem/main/qr.jsp">QRコードジェネレーター</a></li>
        <li><a href="/attendsystem/main/tracker.jsp">出席トラッカー</a></li>
        <li><a href="/attendsystem/main/edit.jsp">その他の編集</a></li>
    </ul>
</nav>

<main>
    <!-- ▼ ここに内容が入る ▼ -->
    ${param.content}
</main>

<footer>
    © 2025 チームD
</footer>

</body>
</html>
