package main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class PasswordResetSendAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // password_reset_send.jsp にフォワード
        req.getRequestDispatcher("/main/common/password_reset_send.jsp").forward(req, res);
    }
}
