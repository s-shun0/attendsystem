package main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ログインページを表示
        req.getRequestDispatcher("/main/login.jsp").forward(req, res);
    }
}