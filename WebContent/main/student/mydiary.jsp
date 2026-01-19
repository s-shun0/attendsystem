<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出席管理表</title>
<style>
	table {
		border-collapse: collapse;
		margin-top: 20px;
	}
	th, td {
		border: 1px solid #000;
		padding: 10px;
		text-align: center;
	}
	th {
		background-color: #f0f0f0;
	}
	.user-info {
	display: flex;
	align-items: center;
	gap : 350px;
	
	}
	.user-info h2 {
		margin: 0;
	}
	.user-info p {
		margin: 0;
	}
</style>
</head>
<body>

	<div>
	<div class="user-info">
		<h2>${user.name}</h2>
		<p>学生番号: ${user.id}</p>
	</div>
		<table>
			<thead>
				<tr>
					<th>月＼出欠席</th>
					<th>出席数</th>
					<th>欠席数</th>
					<th>遅刻数</th>
					<th>早退数</th>
					<th>出停数</th>
					<th>その他</th>
					<th>欠席累計</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="outerList" items="${attendanceList}" varStatus="status">
					<tr>
						<!-- 4月から3月を表示 -->
						<c:set var="month" value="${3 + status.count}"/>
						<c:if test="${month > 12}">
							<c:set var="month" value="${month - 12}"/>
						</c:if>
						<td>${month}月</td>
						<!-- 各列にInteger値を出力 -->
						<c:forEach var="value" items="${outerList}" varStatus="loop">
							<c:if test="${loop.last}">
								<!-- 最後の値のみ÷10 -->
								<td>${value / 10}</td>
							</c:if>
							<c:if test="${!loop.last}">
								<!-- それ以外はそのまま出力 -->
								<td>${value}</td>
							</c:if>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>
