<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
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
		<h2 style="text-align: center;">クラス変更</h2>
		<main class="content">
			<ul class="class-change">

			  <c:forEach var="student" items="${students}">
			    <li class="student-card">
			
			      <div class="student-id-number">
			        <span class="label">学籍番号</span>
			        <p>${student.id}</p>
			      </div>
			
			      <div class="student-name">
			        <span class="label">氏名</span>
			        <p>${student.name}</p>
			      </div>
			
			      <div class="student-course">
			        <span class="label">コース</span>
			        <p>${student.job}</p>
			      </div>
			
			      <div class="student-class">
			        <span class="label">クラス</span>
			        <p>${student.classnum}</p>
			      </div>
			
			    </li>
			  </c:forEach>
		
			</ul>
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
