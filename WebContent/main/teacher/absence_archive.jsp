<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>欠席アーカイブ</title>

<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/menu.css">
</head>

<body>
<div class="wrapper">

    <!-- ヘッダー -->
    <div id="header"></div>

    <h2 class="fs-4 mt-5">学生情報画面</h2>

    <main class="archive-content">

        <c:choose>
            <c:when test="${not empty rows}">
                <c:forEach var="s" items="${rows}">
                    <div class="archive-row">
                        ${s.id}　
                        ${s.name}　
                        欠席累計：${s.weighted}
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="muted">
                    対象の学生が存在しません
                </div>
            </c:otherwise>
        </c:choose>

    </main>

    <!-- フッター -->
    <div id="footer"></div>

</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/header.js"></script>
<script src="../../js/footer.js"></script>
</body>
</html>
