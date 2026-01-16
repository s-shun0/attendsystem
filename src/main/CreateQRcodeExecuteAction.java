package main;  // または適切なパッケージ

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AttendanceDao;
import tool.Action;

public class CreateQRcodeExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) 
            throws Exception {
        
        List<String> errors = new ArrayList<String>();
        
        String id = req.getParameter("username");
        String password = req.getParameter("password");
        
        try {
            AttendanceDao aDao = new AttendanceDao(); 
            boolean jug = aDao.attend(id, password);
            
            if (jug) {
                // ログイン成功
                HttpSession session = req.getSession();
                session.setAttribute("userId", id);
                
                req.getRequestDispatcher("qrcode_loginexecute.jsp").forward(req, res);
                
            } else {
                // ログイン失敗
                errors.add("IDまたはパスワードが正しくありません");
                req.setAttribute("errors", errors);
                req.setAttribute("id", id);
                
                req.getRequestDispatcher("qrcode_login.jsp").forward(req, res);
            }
            
        } catch(Exception e) {
            errors.add("IDまたはパスワードが正しくありません");
            req.setAttribute("errors", errors);
            req.setAttribute("id", id);
            
            req.getRequestDispatcher("qrcode_login.jsp").forward(req, res);

        }
    }
}