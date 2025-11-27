package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import tool.Action; // 共通Action基底クラス

public class PasswordResetSendExecuteAction extends Action{

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String email = req.getParameter("email");

        // 1. ユーザー存在確認
        UserDao userDao = new UserDao();
        String userId = userDao.getUserId(email);
        if(userId == null) {
            req.setAttribute("message", "そのメールアドレスは登録されていません。");
            req.getRequestDispatcher("/password_reset_send.jsp").forward(req, res);
            return;
        }

        // 2. トークン生成
        String token = UUID.randomUUID().toString();

        // 3. トークンをDBに保存（有効期限30分）
        Connection conn = null;
        PreparedStatement ps = null;
        try {
//            Connection conn = getConnection();
            String sql = "INSERT INTO password_reset_tokens(user_id, token, expiration_time, used) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, token);

            // 有効期限30分後を計算
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 30);
            Timestamp expire = new Timestamp(cal.getTimeInMillis());

            ps.setTimestamp(3, expire);
            ps.setBoolean(4, false);
            ps.executeUpdate();
        } finally {
            // 明示的に close
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }

        // 4. メール送信
        String resetUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                + req.getContextPath() + "/password_reset_form.jsp?token=" + token;

        String subject = "パスワードリセットURLのご案内";
        String body = "以下のリンクをクリックしてパスワードをリセットしてください。\n\n" + resetUrl
                    + "\n\n※このURLは30分間有効です。";

//        EmailUtil.sendEmail(email, subject, body); // EmailUtilでメール送信処理を実装

        req.setAttribute("message", "パスワードリセット用のURLを送信しました。メールを確認してください。");
        req.getRequestDispatcher("/password_reset_send.jsp").forward(req, res);
    }
}
