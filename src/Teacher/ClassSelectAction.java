package Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class ClassSelectAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // class_select.jsp にフォワード
        req.getRequestDispatcher("/main/teacher/class_select.jsp").forward(req, res);
    }
}
