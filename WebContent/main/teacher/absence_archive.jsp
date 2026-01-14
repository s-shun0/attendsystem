<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>欠席アーカイブ</title>

<link rel="stylesheet" href="/attendsystem/css/style.css">
<link rel="stylesheet" href="/attendsystem/css/menu.css">
</head>

<body>
<div class="wrapper">

    <div id="header"></div>

    <h2 class="archive-title">欠席アーカイブ</h2>

    <!-- 並び替え -->
    <div class="sort-menu">
        <form method="get" action="Absence_archive.action">
            <select name="sort" onchange="this.form.submit()">
                <option value="name">名前順</option>
                <option value="id">学籍番号順</option>
                <option value="absence">欠席累計順</option>
            </select>
        </form>
    </div>

    <main class="archive-content">

    <c:choose>
        <c:when test="${not empty rows}">
            <div class="student-list">
                <c:forEach var="s" items="${rows}">
                    <div class="student-card archive-card">
                        <div><strong>${s.id}</strong> ${s.name}</div>
                        <div>欠席：${s.absences}</div>
                        <div>遅刻：${s.tardiness}</div>
                        <div>早退：${s.leaving}</div>
                        <div>その他：${s.other}</div>
                        <div class="total ${s.weighted >= 20 ? 'danger' : ''}">
                            欠席累計：${s.weighted}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>

        <c:otherwise>
            <div class="muted">
                対象の学生が存在しません
            </div>
        </c:otherwise>
    </c:choose>

    </main>

    <div id="footer"></div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/attendsystem/js/header.js"></script>
<script src="/attendsystem/js/footer.js"></script>
</body>
</html>
