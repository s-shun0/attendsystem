<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>出席トラッカー</title>

<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/menu.css">

</head>
<body>

<div class="wrapper">

    <!-- ヘッダー（JSで header タグ挿入） -->
    <div id="header"></div>

    <!-- ★ 固定ヘッダー分のスペーサー（CSS変更なしの要） -->
    <div style="height:50px;"></div>

    <!-- タイトル（password_reset.jsp と同じ位置） -->
    <h2 class="text-password_reset fs-4 mt-5">出席トラッカー</h2>

    <!-- ===== メイン ===== -->
    <main class="password-reset-content">
        <!-- ↑ 既存CSSを流用するのがポイント -->

        <form action="Teacher/Absence_archive.action" method="post">

            <div class="pass_reset">
                <label>日付</label>
                <input type="date" name="date" class="form-control">
            </div>

            <div class="pass_reset_again">
                <label>クラス</label>
                <select name="classId" class="form-control">
                    <option value="">選択</option>
                    <c:forEach var="cls" items="${classList}">
                        <option value="${cls.id}">${cls.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div style="font-size:22px; margin-top:20px;">
                最新更新：${empty updateTime ? "--:--" : updateTime}
            </div>

        </form>

    </main>

    <!-- ★ fixed btn は password_reset.jsp と同じ位置 -->
    <input class="btn btn btn-lg btn-primary"
           type="submit" value="変更"
           form="absenceForm"/>

    <!-- フッター -->
    <div id="footer"></div>

</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/header.js"></script>
<script src="../../js/footer.js"></script>

</body>
</html>
