package Teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class OtherEditsAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //other_edits.jsp にフォワード
        req.getRequestDispatcher("/main/teacher/other_edits.jsp").forward(req, res);
    }
}
