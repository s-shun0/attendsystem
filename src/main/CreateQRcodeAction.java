package main;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@WebServlet("/CreateQRcodeAction")
public class CreateQRcodeAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        try {
            HttpSession session = req.getSession();
            String token = (String) session.getAttribute("qrToken");
            
            // セッションにトークンがなければ新規生成
            if (token == null) {
                token = UUID.randomUUID().toString();
                session.setAttribute("qrToken", token);
            }
            
            // QRコードに含めるURL
            String qrUrl = "http://localhost:8080/attendsystem/verify?token=" + token;
            
            // QRコード生成
            int size = 200;
            BitMatrix matrix = new MultiFormatWriter().encode(
                    qrUrl, 
                    BarcodeFormat.QR_CODE, 
                    size, 
                    size
            );
            
            // PNG形式でレスポンスに書き込み
            res.setContentType("image/png");
            MatrixToImageWriter.writeToStream(matrix, "png", res.getOutputStream());
            
        } catch (WriterException e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}