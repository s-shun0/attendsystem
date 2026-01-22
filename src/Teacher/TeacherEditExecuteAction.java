package Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.User;
import dao.TeacherDao;
import tool.Action;

public class TeacherEditExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setCharacterEncoding("UTF-8");

        String oldId = req.getParameter("oldId");

        User t = new User();
        t.setId(req.getParameter("newId"));   // 新しい学籍番号
        t.setName(req.getParameter("name"));
        t.setEmail(req.getParameter("email"));
        t.setPass(req.getParameter("password"));

        TeacherDao dao = new TeacherDao();
        dao.updateTeacher(t, oldId);

        req.getRequestDispatcher("/main/teacher/teacher_edit_complete.jsp")
           .forward(req, res);
    }
}
