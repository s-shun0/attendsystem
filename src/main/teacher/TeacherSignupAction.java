package main.teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class TeacherSignupAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// JSPへフォワード
		req.getRequestDispatcher("teacher_signup.jsp").forward(req, res);
	}

}
