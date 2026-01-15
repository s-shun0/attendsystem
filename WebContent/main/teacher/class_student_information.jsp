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
		<h2 style="text-align: center;">学生編集</h2>
		<main class="content class-student-information">
			<ul class="class-change">
				
			    <!-- ヘッダー -->
			    <li class="class-student-list-header">
			      <span></span>
			      <span>学籍番号</span>
			      <span>氏名</span>
			      <span>コース</span>
			    </li>
				
			    <c:forEach var="student" items="${students}">
			      <li class="class-student-row">
			        <span>${student.id}</span>
			        <span>${student.name}</span>
			        <span>${student.job}</span>
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
