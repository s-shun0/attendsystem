package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
