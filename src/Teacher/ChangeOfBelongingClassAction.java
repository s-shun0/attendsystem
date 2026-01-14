package Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class ChangeOfBelongingClassAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 毎回DBから最新取得（sessionは使わない）
//        StudentDao dao = new StudentDao();
//        List<Student> students = dao.getAllStudents();
//
//        req.setAttribute("students", students);

        req.getRequestDispatcher("/main/teacher/change_of_belonging_class.jsp")
           .forward(req, res);
    }
}