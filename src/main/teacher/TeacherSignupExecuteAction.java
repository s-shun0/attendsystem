package main.teacher;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import tool.Action;

public class TeacherSignupExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // リクエストパラメータ取得
        String id = req.getParameter("sign_id");     // 職員番号
        String name = req.getParameter("name");      // 名前
        String mail = req.getParameter("mail");      // メール
        String pass = req.getParameter("sign_pass"); // パスワード

        Map<String, String> errors = new HashMap<>();

        // ===== バリデーション =====
        if (id == null || id.isBlank()) {
            errors.put("1", "職員番号が未入力です");
        }
        if (name == null || name.isBlank()) {
            errors.put("2", "名前が未入力です");
        }
        if (mail == null || mail.isBlank()) {
            errors.put("3", "メールアドレスが未入力です");
        }
        if (pass == null || pass.isBlank()) {
            errors.put("4", "パスワードが未入力です");
        }

        UserDao userDao = new UserDao();


        // ===== エラーがある場合 =====
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("sign_id", id);
            req.setAttribute("name", name);
            req.setAttribute("mail", mail);
            req.setAttribute("sign_pass", pass);

            // サインアップ画面へ戻す
            req.getRequestDispatcher("teacher_signup.jsp").forward(req, res);
            return;
        }
        


        // ===== 登録処理 =====
        User teacher = new User();
        teacher.setId(id);
        teacher.setName(name);
        teacher.setEmail(mail);
        teacher.setPass(pass);
        teacher.setJob("教員");

        userDao.insert(teacher);   // ※ UserDao に insert(User) がある前提

        // ===== 完了画面へ =====
        req.getRequestDispatcher("teacher_signup_done.jsp").forward(req, res);
    }
}
