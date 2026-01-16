package Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentEditExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setCharacterEncoding("UTF-8");

        String oldId = req.getParameter("oldId");

        Student s = new Student();
        s.setId(req.getParameter("newId"));   // 新しい学籍番号
        s.setName(req.getParameter("name"));
        s.setEmail(req.getParameter("email"));
        s.setJob(req.getParameter("job"));

        StudentDao dao = new StudentDao();
        dao.updateStudent(s, oldId);

        req.getRequestDispatcher("/main/teacher/student_edit_complete.jsp")
           .forward(req, res);
    }
}
