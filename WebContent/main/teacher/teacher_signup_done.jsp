<%-- 教員登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/main/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<div id="wrap_box">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">職員用サインアップ</h2>
			<div id="wrap_box">
				<h2 class="text-center">サインアップが完了しました</h2>
				<br>
				<br>
				<p class="text-center">このままログインする方はこちらへ</p>
				<p class="text-center">↓　↓　↓</p>
				<br>				
				<a href="..//Login.action">ログイン画面へ</a>
				
			</div>
		</div>
	</c:param>
</c:import>

