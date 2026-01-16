<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>欠席アーカイブ</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
</head>

<body>

<jsp:include page="/main/common/header.jsp" />

<div class="wrapper">

<h2 class="archive-title">欠席アーカイブ</h2>

<!-- ===== 並び替え ===== -->
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

    <!-- 名前 -->
    <div>
        <a href="${pageContext.request.contextPath}/Teacher/Personal_Information_Verification.action?studentId=${s.id}"
           class="student-link">
            ${s.name}
        </a>
    </div>

    <div>欠席：${s.absences}</div>
    <div>遅刻：${s.tardiness}</div>
    <div>早退：${s.leaving}</div>
    <div>その他：${s.other}</div>

    <div class="total ${s.weighted >= 20 ? 'danger' : ''}">
        欠席累計：
        <fmt:formatNumber value="${s.weighted}" type="number"
                          maxFractionDigits="1" minFractionDigits="1"/>
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

<!-- 注釈 -->
<div class="note">
    ※その他欠席は通常累計には含まれませんが、累計欠席が80以上になると加算されます。<br>
    ※遅刻・早退は3回で1欠席換算です。
</div>

</main>

</div>

<jsp:include page="/main/common/footer.jsp" />

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/footer.js"></script>

<!-- ===== ハンバーガー補助スクリプト ===== -->
<script>
  $(function() {
    $('.hamburger').on('click', function(){
      $('.menu').toggleClass('open hidden');
    });
  });
</script>


</body>
</html>
