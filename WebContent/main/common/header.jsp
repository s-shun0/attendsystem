<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header>
<div class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
	<h1 class="fs-1">出席管理システム</h1>
</div>

<!-- ✅ ログインしている場合のみ表示 -->
<c:if test="${user != null}">
  <div class="container">
      <div class="logout">
          <a href="仮状態">ログアウト</a>
      </div>

      <div class="hamburger">
        <span></span>
        <span></span>
        <span></span>
      </div>
  </div>

  <nav class="menu">
    <ul>
      <li>メニュー</li>
      <hr>
      <li><a href="/attendsystem/Teacher/ClassSelect.action">クラスセレクト</a></li>
      <li><a href="/attendsystem/Teacher/Absence_archive.action">欠席アーカイブ</a></li>
      <li><a href="/attendsystem/main/qrcode_display.jsp">QRコードジェネレーター</a></li>
      <li><a href="/attendsystem/Teacher/Attendance_Tracker.action">出席トラッカー</a></li>
      <li><a href="/attendsystem/Teacher/OtherEdits.action">その他の編集</a></li>
    </ul>
  </nav>
</c:if>

</header>