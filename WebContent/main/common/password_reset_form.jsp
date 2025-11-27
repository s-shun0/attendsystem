<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>パスワードリセット</title></head>
<body>
<h2>新しいパスワードを入力してください</h2>
<form action="/attendsystem/Main/PasswordResetExecute.action" method="post">
    <input type="hidden" name="token" value="${param.token}" />
    <label>新しいパスワード</label>
    <input type="password" name="password" required />
    <button type="submit">リセット</button>
</form>
</body>
</html>
