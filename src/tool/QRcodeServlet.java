package tool;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRcodeServlet extends HttpServlet {
    // メモリ上に一時的に保存（実際はDBでもOK）
    private static String latestToken = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // 新しいトークンを生成（毎回URLが変わる）
        String token = UUID.randomUUID().toString();
        latestToken = token;

        String qrUrl = "http://localhost:8080/YourApp/verify?token=" + token;

        // QRコード生成
        int size = 200;
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(qrUrl, BarcodeFormat.QR_CODE, size, size);
            res.setContentType("image/png");
            MatrixToImageWriter.writeToStream(matrix, "png", res.getOutputStream());
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static String getLatestToken() {
        return latestToken;
    }
}
