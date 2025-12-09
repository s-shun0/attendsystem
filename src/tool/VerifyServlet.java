package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verify")
public class VerifyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        String token = req.getParameter("token");
        HttpSession session = req.getSession();
        
        // URLから受け取ったトークンをセッションと比較
        String sessionToken = (String) session.getAttribute("qrToken");
        
        if (token != null && !token.isEmpty() && token.equals(sessionToken)) {
            // トークンが有効な場合、ログインページにリダイレクト
            session.setAttribute("qrVerified", true);
            res.sendRedirect(req.getContextPath() + "/main/qrcode_login.jsp");
        } else {
            // トークンが無効な場合、エラーページに遷移
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }
}