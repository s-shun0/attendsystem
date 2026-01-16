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
		<h2 style="text-align: center;">${classnum}クラス変更</h2>
			<main class="content class-change-page">
			
				<form action="${pageContext.request.contextPath}/tool/ClassChangeServlet"method="post">
				
				  <!-- 操作エリア -->
				  <div class="change-action">
				    <select name="newClass">
				      <option value="">変更先クラス</option>
				      <option value="41">41教室</option>
				      <option value="42">42教室</option>
				      <option value="43">43教室</option>
				      <option value="44">44教室</option>
				      <option value="51">51教室</option>
				      <option value="52">52教室</option>
				      <option value="53">53教室</option>
				      <option value="54">54教室</option>
				      <option value="61">61教室</option>
				      <option value="62">62教室</option>
				      <option value="63">63教室</option>
				      <option value="64">64教室</option>
				    </select>
				
				    <button type="submit" class="change-btn">変更</button>
				  </div>
				
				  <!-- 一覧 -->
				  <ul class="class-change">

					  <!-- ヘッダー -->
					  <li class="list-header">
					    <span></span>
					    <span>学籍番号</span>
					    <span>氏名</span>
					    <span>コース</span>
					    <span>クラス</span>
					  </li>
					
					  <c:choose>
					
					    <c:when test="${empty students}">
						  <li class="student-row no-student">
						    <!-- チェックボックス列 -->
						    <span></span>
						
						    <!-- 2〜5列をまとめて使用 -->
						    <span class="no-student-text" style="grid-column: 2 / 6;">
						      生徒情報が存在しません
						    </span>
						  </li>
						</c:when>
					    <c:otherwise>
					      <c:forEach var="student" items="${students}">
					        <li class="student-row">
					
					          <span>
					            <input type="checkbox" name="studentIds" value="${student.id}">
					          </span>
					
					          <span>${student.id}</span>
					          <span>${student.name}</span>
					          <span>${student.job}</span>
					          <span>${student.classnum}</span>
					
					        </li>
					      </c:forEach>
					    </c:otherwise>
					
					  </c:choose>
					
					</ul>				
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
