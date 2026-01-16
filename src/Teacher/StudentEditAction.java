package Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentEditAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession(false);
        if (session == null) {
            req.getRequestDispatcher("/main/login.jsp").forward(req, res);
            return;
        }

        String studentId = req.getParameter("studentId");

        StudentDao dao = new StudentDao();
        Student student = dao.getStudentById(studentId);

        // JSPで使う名前と合わせる
        req.setAttribute("student", student);

        req.getRequestDispatcher("/main/teacher/student_edit.jsp")
           .forward(req, res);
    }
}
