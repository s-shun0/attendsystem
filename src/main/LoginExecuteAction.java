package main;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import tool.Action;

public class LoginExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // エラーメッセージを格納するリスト
        List<String> errors = new ArrayList<String>();

        // セッションを取得
        HttpSession session = req.getSession();

        // フォームからパラメータを取得
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        // 入力値の検証
        if (id == null || id.trim().isEmpty()) {
            errors.add("IDを入力してください");
        }
        if (password == null || password.trim().isEmpty()) {
            errors.add("パスワードを入力してください");
        }

        // エラーがある場合はログイン画面に戻る
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("id", id);
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, res);
            return;
        }

        // DAOを使用してユーザー認証
        UserDao userDao = new UserDao();
        User user = userDao.login(id, password);
        // 教員情報更新の時に使いたいからセッションに保存追加
        req.getSession().setAttribute("id", id);
        req.getSession().setAttribute("password", password);

        if (user != null) {
            // ログイン成功
            if (!user.getJob().equals("教員")) {
            	errors.add("教員情報を入力して下さい");
                req.setAttribute("errors", errors);
                req.setAttribute("id", id);
            	RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, res);
            }else {
        	
	        	// セッションにユーザー情報を保存
	            session.setAttribute("user", user);
	            
	            // MenuActionにフォワード
	            RequestDispatcher dispatcher = req.getRequestDispatcher("../Teacher/ClassSelect.action");
	            dispatcher.forward(req, res);
            }
        } else {
            // ログイン失敗
            errors.add("IDまたはパスワードが正しくありません");
            req.setAttribute("errors", errors);
            req.setAttribute("id", id);

            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, res);
        }
    }
}