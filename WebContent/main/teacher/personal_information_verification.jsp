<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>個人情報確認</title>

<link rel="stylesheet" href="../../css/style.css">
<link rel="stylesheet" href="../../css/menu.css">
</head>

<body>
<div class="wrapper">

    <!-- 共通ヘッダー -->
    <div id="header"></div>

    <!-- ===== 上部：個人情報 ===== -->
    <section class="personal-info">

        <h2 class="fs-4 mt-4">氏名</h2>

        <div class="info-row">
            <div>学籍番号：${student.id}</div>
            <div>性別：${student.gender}</div>
        </div>

        <div class="info-row">
            <label>メールアドレス：</label>
            <input type="email"
                   class="form-control"
                   value="${student.email}"
                   readonly>
        </div>

    </section>

    <!-- ===== 出欠サマリー ===== -->
    <section class="summary-section">
        <h3>出欠状況</h3>

        <table class="table table-bordered summary-table">
            <tr>
                <th>欠席</th>
                <th>遅刻</th>
                <th>早退</th>
                <th>その他</th>
                <th>欠席累計</th>
            </tr>
            <tr>
                <td>${summary.absence}</td>
                <td>${summary.late}</td>
                <td>${summary.leaveEarly}</td>
                <td>${summary.other}</td>
                <td>${summary.total}</td>
            </tr>
        </table>
    </section>

    <!-- ===== 月別出席表 ===== -->
    <section class="attendance-table-section">

        <h3 class="text-center">表</h3>

        <table border="1" cellspacing="0" cellpadding="4">

            <!-- ヘッダー -->
            <tr>
                <th>月</th>
                <c:forEach var="d" begin="1" end="31">
                    <th>${d}</th>
                </c:forEach>
                <th>遅刻</th>
                <th>欠席</th>
                <th>その他</th>
                <th>欠席累計</th>
            </tr>

            <!-- 月データ -->
            <c:forEach var="row" items="${monthlyList}">
                <tr>
                    <td>${row.month}</td>

                    <c:forEach var="status" items="${row.dailyStatus}">
                        <td>${status}</td>
                    </c:forEach>

                    <td>${row.late}</td>
                    <td>${row.absence}</td>
                    <td>${row.other}</td>
                    <td>${row.total}</td>
                </tr>
            </c:forEach>

        </table>

    </section>

    <!-- 共通フッター -->
    <div id="footer"></div>

</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/header.js"></script>
<script src="../../js/footer.js"></script>

</body>
</html>
