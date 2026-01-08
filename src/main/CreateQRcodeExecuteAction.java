package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AttendanceDao;

@WebServlet("/CreateQRcodeExecuteAction")
public class CreateQRcodeExecuteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	
    	List<String> errors = new ArrayList<String>();
        
        // ログインフォームから受け取った情報
        String id = req.getParameter("username");
        String password = req.getParameter("password");
        
        
        
        try {
            // AttendDaoを使用してログイン処理
            AttendanceDao aDao = new  AttendanceDao(); 
            boolean jug = aDao.attend(id, password);
            
            if (jug == true) {
       
                // フォワード
                RequestDispatcher dispatcher = req.getRequestDispatcher("qrcode_loginexecute.jsp");
                dispatcher.forward(req, res);
            	
            }else {
            	errors.add("IDまたはパスワードが正しくありません");
                req.setAttribute("errors", errors);
                req.setAttribute("id", id);
                
                RequestDispatcher dispatcher = req.getRequestDispatcher("CreateQRcode.action");
                dispatcher.forward(req, res);
            }
            
        }catch(Exception e) {
        	
        }
    }
}