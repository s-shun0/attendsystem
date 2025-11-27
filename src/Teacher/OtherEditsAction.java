package Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class OtherEditsAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //other_edits.jsp にフォワード
        req.getRequestDispatcher("/main/teacher/other_edits.jsp").forward(req, res);
    }
}
