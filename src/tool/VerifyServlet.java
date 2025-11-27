package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VerifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String token = req.getParameter("token");

///       if (token != null && token.equals(QRcodeServlet.getLatestToken())) {
//            res.getWriter().println("出席成功！");
//        } else {
//            res.getWriter().println("無効または期限切れのQRコードです。");
//        }
    }
}
