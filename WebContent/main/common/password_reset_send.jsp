<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html> <html lang="ja">
<head>
	<meta charset="UTF-8">
	<title>パスワードリセットURL送信</title>
	<link rel="stylesheet" href="../css/style.css">
	<link rel="stylesheet" href="../css/menu.css">
</head>
<body class="class-select-page">
<div class="wrapper">
	<div id="header"></div>
		<h2 class="text-password_reset fs-4 mt-5">パスワードリセットURL送信</h2>
		<main class="content"> <%-- 送信結果メッセージ --%>
			<c:if test="${not empty message}">
				<p style="color: green; font-weight: bold;">
    				<c:out value="${message}" />
				</p>
			</c:if>
			<form action="/attendsystem/main/PasswordResetSendExecute.action" method="post">
				<label>メールアドレス</label>
				<input class="form-control px-5 fs-5" autocomplete="off" id="password-input" maxlength="30" name="email" placeholder="メールアドレスを入力" style="ime-mode: disabled" type="email" required />
				<input class="btn btn-lg btn-primary mt-3" type="submit" value="メール送信"/>
			</form>
		</main>
	<div id="footer"></div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../js/header.js"></script>
<script src="../js/footer.js"></script>
</body>
</html>