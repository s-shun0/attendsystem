package Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import tool.Action;

public class TeacherEditAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	HttpSession session = req.getSession();
    	String id = (String) session.getAttribute("id");
    	String password = (String) session.getAttribute("password");
        
        UserDao userDao = new UserDao();
        User user = userDao.login(id, password);
        
        req.setAttribute("user", user);
    	
        req.getRequestDispatcher("/main/teacher/teacher_edit.jsp")
           .forward(req, res);
    }
}