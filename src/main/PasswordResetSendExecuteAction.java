package main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class PasswordResetSendExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ログインページを表示
        req.getRequestDispatcher("/main/common/password_reset_form.jsp").forward(req, res);
    }
}