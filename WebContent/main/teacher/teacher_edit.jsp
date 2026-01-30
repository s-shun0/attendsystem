<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="/attendsystem/css/style.css">
<link rel="stylesheet" href="/attendsystem/css/menu.css">

<title>出席管理システム</title>

</head>
<body>

	<div class="wrapper">
		<!-- ヘッダー（JSで読み込み） -->
		<div id="header"></div>
		<h2 style="text-align: center;">教員情報更新</h2>
		<main class="content">
			<form action="${pageContext.request.contextPath}/Teacher/TeacherEditExecute.action" method="post">
			
			  <!-- 元の学籍番号（更新対象を特定する用） -->
			  <input type="hidden" name="oldId" value="${user.id}">
			
			  氏名：
			  <input type="text" name="name" value="${user.name}" required><br>
			
			  教員番号：
			  <input type="text" name="newId" value="${user.id}" required><br>
			  
			  パスワード：
			  <input type="text" name="password" value="${user.pass}" required><br>
			
			  メールアドレス：
			  <input type="email" name="email" value="${user.email}" required><br>
			
			  <button type="submit">更新</button>
			</form>
		</main>
		<!-- フッター（JSで読み込み） -->
	    <div id="footer"></div>
    </div>

    <!-- JavaScriptファイルの読み込み -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- jQuery本体 -->
	<script src="/attendsystem/js/header.js"></script>
	<script src="/attendsystem/js/footer.js"></script>
</body>
</html>
