<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous">
    <title>学生 - ログイン</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
    <div id="wrapper" class="container">
        <!-- ヘッダー -->
        <header class="d-flex flex-wrap justify-content-center py-3 px-5 mb-4 border-bottom border-2 bg-primary bg-opacity-10 bg-gradient">
            <h1 class="fs-4 mb-0">学生ログイン</h1>
            <a href="/attendsystem/main/student/student_signup.jsp">サインアップ</a>
        </header>

        <!-- メインコンテンツ -->
        <div class="row justify-content-center">
            <main class="col-lg-6 col-md-8 col-sm-10">
                <section class="border rounded shadow-sm p-4">
                    <h2 class="h3 mb-4 text-center bg-secondary bg-opacity-10 py-3 rounded">ログイン</h2>

                    <!-- エラーメッセージ表示 -->
                    <c:if test="${not empty errors}">
                        <div class="alert alert-danger" role="alert">
                            <ul class="mb-0">
                                <c:forEach var="error" items="${errors}">
                                    <li>${error}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>

                    <!-- ログインフォーム -->
                    <form action="${pageContext.request.contextPath}/Student/StudentLoginExecute.action" method="post">
                        <!-- ＩＤ入力 -->
                        <div class="mb-3">
                            <label for="id-input" class="form-label">ＩＤ</label>
                            <input type="text"
                                   class="form-control form-control-lg"
                                   id="id-input"
                                   name="id"
                                   maxlength="20"
                                   placeholder="半角でご入力ください"
                                   autocomplete="off"
                                   inputmode="latin"
                                   required>
                        </div>

                        <!-- パスワード入力 -->
                        <div class="mb-3">
                            <label for="password-input" class="form-label">パスワード</label>
                            <input type="password"
                                   class="form-control form-control-lg"
                                   id="password-input"
                                   name="password"
                                   maxlength="20"
                                   placeholder="20文字以内の半角英数字"
                                   autocomplete="current-password"
                                   inputmode="latin"
                                   required>
                        </div>

                        <!-- パスワード表示チェックボックス -->
                        <div class="form-check mb-4">
                            <input class="form-check-input"
                                   type="checkbox"
                                   id="password-display">
                            <label class="form-check-label" for="password-display">
                                パスワードを表示
                            </label>
                            <a href="/attendsystem/Teacher/TeacherPasswordReset.action">パスワードを忘れた方はこちら</a>
                        </div>

                        <!-- ログインボタン -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-lg">
                                ログイン
                            </button>
                        </div>
                    </form>
                </section>
            </main>
        </div>

        <!-- フッター -->
        <footer class="py-3 my-4 bg-dark bg-opacity-10 border-top text-center">
            <p class="mb-0 text-muted">&copy; 2024 出席管理システム</p>
        </footer>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>

    <!-- パスワード表示/非表示の切り替え -->
    <script>
        $(function() {
            $('#password-display').on('change', function() {
                const passwordInput = $('#password-input');
                passwordInput.attr('type', $(this).prop('checked') ? 'text' : 'password');
            });
        });
    </script>
</body>
</html>
