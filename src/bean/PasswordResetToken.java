package bean;

import java.time.LocalDateTime;

public class PasswordResetToken {

    private int userId;
    private String token;
    private LocalDateTime expireAt;

    // --- getter/setter ---
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }
    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    // --- 期限切れ判定 ---
    public boolean isExpired() {
        return expireAt.isBefore(LocalDateTime.now());
    }
}
