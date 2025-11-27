<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/menu.css">

<title>出席管理システム</title>

</head>
<body>

	<div class="wrapper">
		<!-- ヘッダー（JSで読み込み） -->
		<div id="header"></div>
		<h2 class="text-password_reset fs-4 mt-5">教員パスワードリセット</h2>
		<main class="password-reset-content">
			<div class="pass_reset">
				<label>パスワード変更</label>
				<input class="form-control px-5 fs-5" autocomplete="off"
					id="password-input" maxlength="20" name="password"
					placeholder="内容未定のため保留中" style="ime-mode: disabled"
					type="password" required />
			</div>
			<div class="pass_reset_again">
				<label>再入力</label>
				<input class="form-control px-5 fs-5" autocomplete="off"
					id="password-input-again" maxlength="20" name="password_confirm"
					placeholder="内容未定のため保留中" style="ime-mode: disabled"
					type="password" required />
			</div>
		</main>
		<input class="btn btn btn-lg btn-primary" type="submit" name="change" value="変更"/>
		<!-- フッター（JSで読み込み） -->
	    <div id="footer"></div>
    </div>

    <!-- JavaScriptファイルの読み込み -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- jQuery本体 -->
	<script src="../../js/header.js"></script>
	<script src="../../js/footer.js"></script>
</body>
</html>
