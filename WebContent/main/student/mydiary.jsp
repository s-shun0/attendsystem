<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>出席管理表</title>

<style>
    table {
        border-collapse: collapse;
        width: 100%;
        table-layout: fixed; /* 全列を画面内に収める */
        margin-top: 20px;
    }

    th, td {
        border: 1px solid #000;
        text-align: center;
        padding: 6px;
        white-space: normal;
        word-break: break-all;
    }

    th {
        background-color: #f0f0f0;
    }

    .user-info {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .user-info h2,
    .user-info p {
        margin: 0;
    }

    /* スマホ用：とにかく全部入れる */
    @media (max-width: 768px) {
        .user-info {
            flex-direction: column;
            align-items: flex-start;
        }

        th, td {
            padding: 2px;
            font-size: 10px;
        }
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
                    <c:set var="month" value="${3 + status.count}" />
                    <c:if test="${month > 12}">
                        <c:set var="month" value="${month - 12}" />
                    </c:if>
                    <td>${month}月</td>

                    <c:forEach var="value" items="${outerList}" varStatus="loop">
                        <c:choose>
                            <c:when test="${loop.last}">
                                <td>${value / 10}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${value}</td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>
