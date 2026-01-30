package main;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AttendanceDao;
import tool.Action;

public class CreateQRcodeExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) 
            throws Exception {
        
        String id = req.getParameter("username");
        String password = req.getParameter("password");
        String errors = null;
        
        // 入力値チェック
        if (id == null || id.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            errors = "IDとパスワードを入力してください";
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("qrcode_login.jsp").forward(req, res);
            return;
        }
        
        try {
            AttendanceDao aDao = new AttendanceDao(); 
            boolean jug = aDao.attend(id, password);
            
            if (jug) {
                // ログイン成功
                HttpSession session = req.getSession();
                session.setAttribute("id", id);
                session.setAttribute("password", password);
                req.getRequestDispatcher("qrcode_loginexecute.jsp").forward(req, res);
            } else {
                // ログイン失敗
                errors = "IDまたはパスワードが正しくありません";
                req.setAttribute("errors", errors);
                req.setAttribute("id", id);
                req.getRequestDispatcher("qrcode_login.jsp").forward(req, res);
            }
        } catch(Exception e) {
            errors = "処理中にエラーが発生しました：" + e.getMessage();
            req.setAttribute("errors", errors);
            req.setAttribute("id", id);
            req.getRequestDispatcher("qrcode_login.jsp").forward(req, res);
        }
    }
}