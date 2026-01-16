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
		<h2 style="text-align: center;">生徒情報編集</h2>
		<main class="content">
			<form action="${pageContext.request.contextPath}/Teacher/StudentEditExecute.action" method="post">
			
			  <!-- 元の学籍番号（更新対象を特定する用） -->
			  <input type="hidden" name="oldId" value="${student.id}">
			
			  氏名：
			  <input type="text" name="name" value="${student.name}"><br>
			
			  コース：<br>
			  <select name="job">
			
			    <option value="システム開発コース"
			      ${student.job == 'システム開発コース' ? 'selected' : ''}>
			      システム開発コース
			    </option>
			
			    <option value="高度システム開発コース"
			      ${student.job == '高度システム開発コース' ? 'selected' : ''}>
			      高度システム開発コース
			    </option>
			
			    <option value="ネットワークセキュリティコース"
			      ${student.job == 'ネットワークセキュリティコース' ? 'selected' : ''}>
			      ネットワークセキュリティコース
			    </option>
			    
			    <option value="AIシステム・データサイエンスコース"
			      ${student.job == 'AIシステム・データサイエンスコース' ? 'selected' : ''}>
			      AIシステム・データサイエンスコース
			    </option>
			    
			    <option value="情報IT＋東京経営大学卒業コース"
			      ${student.job == '情報IT＋東京経営大学卒業コース' ? 'selected' : ''}>
			      情報IT＋東京経営大学卒業コース
			    </option>
			  </select>
			  <br><br>
			
			  学籍番号：
			  <input type="text" name="newId" value="${student.id}"><br>
			
			  メールアドレス：
			  <input type="email" name="email" value="${student.email}"><br>
			
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
