package Teacher;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.User;
import tool.Action;

public class ChangeOfBelongingClassAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	HttpSession session = req.getSession(false);

    	@SuppressWarnings("unchecked")
    	List<User> students = (List<User>) session.getAttribute("students");

    	
    	req.setAttribute("students", students);

        //change_of_belonging_class.jsp にフォワード
        req.getRequestDispatcher("/main/teacher/change_of_belonging_class.jsp").forward(req, res);
    }
}
