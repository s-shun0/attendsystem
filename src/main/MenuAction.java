package Main;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import tool.Action;

public class MenuAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションからユーザー情報を取得
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // ログインチェック
        if (user == null) {
            // 未ログインの場合はログイン画面へ
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // ログイン済みの場合はメニュー画面へ
        RequestDispatcher dispatcher = request.getRequestDispatcher("main/menu.jsp");
        dispatcher.forward(request, response);
    }
}