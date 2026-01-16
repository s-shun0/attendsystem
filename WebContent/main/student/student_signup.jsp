<%-- 学生用サインアップJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/main/common/base.jsp">
	<c:param name="title">
		出席管理システム
	</c:param>

	<c:param name="scripts">
		<script type="text/javascript">
			$(function() {
				// 「パスワードを表示」チェックのイベントハンドラ
				$('#showPassword').on('change', function() {
					const passwordInput = $('#sign_pass');
					if ($(this).is(':checked')) {
						passwordInput.attr('type', 'text');
					} else {
						passwordInput.attr('type', 'password');
					}
				});
			});
		</script>
	</c:param>

	<c:param name="content">
		<section>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">サインアップ</h2>
			<form action="StudentSignupExecute.action" method="post">
				<div>
					<label for="sign_id" style="display:inline-block; width:120px;">学籍番号</label>
					<input type="text" id="sign_id" name="sign_id" value="${sign_id }" required maxlength="11" placeholder="学籍番号を入力してください" style="display:inline-block; width:300px;"/>
				</div>
				<br>
				<div>
					<label for="name" style="display:inline-block; width:120px;">名前</label>
					<input type="text" id="name" name="name" value="${name }" required maxlength="50" placeholder="名前を入力してください" style="display:inline-block; width:300px;"/>
				</div>
				<br>
				<div>
					<label for="mail" style="display:inline-block; width:120px;">メールアドレス</label>
					<input type="text" id="mail" name="mail" value="${mail }" required maxlength="50" placeholder="メールアドレスを入力してください" style="display:inline-block; width:300px;"/>
				</div>
				<br>				
				<div>
					<label for="course" style="display:inline-block; width:120px;">コース名</label>
					<input  type="text" id="course" name="course" value="${course }" required maxlength="10" placeholder="コース名を入力してください" style="display:inline-block; width:300px;">
				</div>
				<br>				
				<div>
					<label for="sign_pass" style="display:inline-block; width:120px;">パスワード</label>
					<input type="password" id="sign_pass" name="sign_pass" value="${name }" required maxlength="50" placeholder="パスワードを入力してください" style="display:inline-block; width:300px;"/>
				</div>
				<div class="checkbox-group" style="margin-left:125px;">
                    <input type="checkbox" id="showPassword">
                    <label for="showPassword">パスワードを表示</label>
                </div>

				<div class="mt-4">
					<input class="w-25 btn btn-lg btn-primary" type="submit" name="login" value="ログイン"/>
				</div>
			</form>
			<a href="Login.action">戻る</a>
		</section>
	</c:param>
</c:import>