package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class PasswordResetSendAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // login.jsp にフォワード
        req.getRequestDispatcher("/password_reset_send.jsp").forward(req, res);
    }
}
