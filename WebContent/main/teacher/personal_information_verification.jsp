<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>個人欠席詳細</title>

<!-- ===== CSS ===== -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
</head>

<body>

<!-- ===== ヘッダー ===== -->
<jsp:include page="/main/common/header.jsp" />

<!-- ★ 固定ヘッダー分のスペーサー（高さ50px） -->
<div style="height:50px;"></div>


<div class="wrapper">

    <h2 class="archive-title">個人欠席詳細</h2>

    <!-- ===== 生徒情報 ===== -->
    <div class="table-center">
        <table class="table table-bordered archive-table">
            <tr>
                <th>学籍番号</th>
                <td>${student.id}</td>
            </tr>
            <tr>
                <th>氏名</th>
                <td>${student.name}</td>
            </tr>
            <tr>
                <th>クラス</th>
                <td>${student.classnum}</td>
            </tr>
        </table>
    </div>

    <!-- ===== 出席表（スクロール） ===== -->
    <div class="scroll-box">
        <table class="attendance-table">
            <tr>
                <th>月</th>
                <c:forEach begin="1" end="31" var="d">
                    <th>${d}</th>
                </c:forEach>
            </tr>

            <c:forEach var="m" items="${months}">
                <tr>
                    <th>${m}</th>
                    <c:forEach begin="1" end="31" var="d">
                        <td>
                            <c:out value="${attendanceMap[m.toString()][d.toString()]}" />
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </div>

    <!-- ===== 集計 ===== -->
    <h3 class="mt-4">出欠集計</h3>
    <table class="summary-table">
        <tr><th>出席数</th><td>${summary.present}</td></tr>
        <tr><th>欠席数</th><td>${summary.absent}</td></tr>
        <tr><th>遅刻数</th><td>${summary.late}</td></tr>
        <tr><th>その他</th><td>${summary.other}</td></tr>
    </table>

</div>

<!-- ===== フッター ===== -->
<jsp:include page="/main/common/footer.jsp" />

<!-- ===== JS ===== -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="${pageContext.request.contextPath}/js/footer.js"></script>

<!-- ===== ハンバーガー動作用補助スクリプト ===== -->
<script>
  $(function() {
    // class="hamburger" に合わせてクリックイベントを登録
    $('.hamburger').on('click', function(){
      $('.menu').toggleClass('open hidden');
    });
  });
</script>

</body>
</html>
