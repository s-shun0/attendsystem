package Teacher;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.User;
import tool.Action;

public class StudentInformationListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	@SuppressWarnings("unchecked")
    	List<User> students = (List<User>) req.getSession().getAttribute("students");

    	// ▼ JSPへ渡す（リクエストスコープに保存）
        req.setAttribute("students", students);

        //other_edits.jsp にフォワード
        req.getRequestDispatcher("/main/teacher/student_information_list.jsp").forward(req, res);
    }
}
