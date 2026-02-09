<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>出席トラッカー</title>

<link rel="stylesheet" href="/attendsystem/css/style.css">
<link rel="stylesheet" href="/attendsystem/css/menu.css">

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

        <form id="absenceForm" action="Attendance_Tracker.action" method="post">

            <div class="pass_reset">
                <label>日付</label>
                <input type="date" value="${date}" name="date" class="form-control">
            </div>

            <div class="pass_reset_again">
			    <label>クラス</label>
			    <select name="classnum" class="form-control">
			        <option value="">選択</option>
			        <c:forEach var="cls" items="${classList}">
			            <option value="${cls}" 
			                    <c:if test="${cls == classnum}">selected</c:if>
			            >${cls}</option>
			        </c:forEach>
			    </select>
			</div>

        </form>

        <!-- 出席情報テーブル -->
        <div style="margin-top:30px; margin-bottom:80px;">
            <h3>出席情報一覧</h3>
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>学生ID</th>
                        <th>学生名</th>
                        <th>日付</th>
                        <th>出席状況</th>
                        <th>最新更新</th>
                    </tr>
                </thead>
                <tbody>
			        <c:forEach var="attendance" items="${attendanceList}">
			            <tr>
			                <td>${attendance.id}</td>
			                <td>${attendance.name}</td>
			                <td>${attendance.date}</td>
			                <td>
			                    <c:choose>
			                        <c:when test="${attendance.status == 'present'}">
			                            <span class="badge bg-success">出席</span>
			                        </c:when>
			                        <c:when test="${attendance.status == 'late'}">
			                            <span class="badge bg-warning">遅刻</span>
			                        </c:when>
			                        <c:when test="${attendance.status == 'early_leave'}">
			                            <span class="badge bg-danger">早退</span>
			                        </c:when>
			                        <c:when test="${attendance.status == 'absent'}">
			                            <span class="badge bg-danger">欠席</span>
			                        </c:when>
			                        <c:otherwise>
			                            <span class="badge bg-secondary">${attendance.status}</span>
			                        </c:otherwise>
			                    </c:choose>
			                </td>
			                <td>${empty attendance.update ? "--:--" : attendance.update}</td>
			            </tr>
			        </c:forEach>
			        <c:if test="${empty attendanceList}">
			            <tr>
			                <td colspan="4" class="text-center text-muted">出席情報がありません</td>
			            </tr>
			        </c:if>
			    </tbody>
        </div>

    </main>

    <!-- ★ fixed btn は password_reset.jsp と同じ位置 -->
    <input class="btn btn btn-lg btn-primary"
           type="submit" value="変更"
           form="absenceForm"/>

    <!-- フッター -->
<!--    <div id="footer"></div>-->

</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="/attendsystem/js/header.js"></script>
<script src="/attendsystem/js/footer.js"></script>
</body>
</html>
