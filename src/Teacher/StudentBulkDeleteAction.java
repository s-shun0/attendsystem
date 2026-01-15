package Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.StudentDao;
import tool.Action;

public class StudentBulkDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("classnum") == null) {
            res.sendRedirect(req.getContextPath() + "/Teacher/ClassSelect.action");
            return;
        }

        int classnum = (int) session.getAttribute("classnum");

        StudentDao dao = new StudentDao();
        dao.deleteStudentsByClass(classnum);

        // 削除後はクラス選択画面へ戻す
        req.getRequestDispatcher("/main/teacher/student_bulk_delete_complete.jsp").forward(req, res);
    }
}
