package Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class TeacherPasswordResetAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	req.getRequestDispatcher("/main/common/password_reset_send.jsp").forward(req, res);
    }
}

