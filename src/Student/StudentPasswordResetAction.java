package Student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class StudentPasswordResetAction {
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		req.getRequestDispatcher("student_password_reset.jsp").forward(req, res);
	}
}
