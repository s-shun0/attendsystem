package main.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentLoginAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 学生用ログイン画面へフォワード
        req.getRequestDispatcher("student_login.jsp").forward(req, res);
    }
}
