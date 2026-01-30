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
<body class="class-select-page">

	<div class="wrapper">
		<!-- ヘッダー（JSで読み込み） -->
		<div id="header"></div>
		<main class="content">
			<ul class="class-list">
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=41">41教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=42">42教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=43">43教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=44">44教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=51">51教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=52">52教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=53">53教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=54">54教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=61">61教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=62">62教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=63">63教室</a></li>
				<li><a href="/attendsystem/Teacher/ClassSelectExecute.action?classnum=64">64教室</a></li>
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
