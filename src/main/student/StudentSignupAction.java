package main.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentSignupAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// JSPへフォワード
		req.getRequestDispatcher("student_signup.jsp").forward(req, res);
	}

}