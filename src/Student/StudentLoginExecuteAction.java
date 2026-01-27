package Student;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.StudentDao;
import tool.Action;

public class StudentLoginExecuteAction extends Action {
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
            RequestDispatcher dispatcher = req.getRequestDispatcher("student_login.jsp");
            dispatcher.forward(req, res);
            return;
        }

        // DAOを使用してユーザー認証
        StudentDao sDao = new StudentDao();
        boolean count = sDao.getLogin(id, password);


        if (count == true) {
            // ログイン成功
            // セッションにユーザー情報を保存
            session.setAttribute("id", id);
            session.setAttribute("password", password);
            // 生徒情報に遷移
            RequestDispatcher dispatcher = req.getRequestDispatcher("MyDiary.action");
            dispatcher.forward(req, res);
        } else {
            // ログイン失敗
            errors.add("IDまたはパスワードが正しくありません");
            req.setAttribute("errors", errors);
            req.setAttribute("id", id);

            RequestDispatcher dispatcher = req.getRequestDispatcher("student_login.jsp");
            dispatcher.forward(req, res);
        }
    }
}