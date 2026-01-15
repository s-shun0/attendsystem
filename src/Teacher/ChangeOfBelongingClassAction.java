package Teacher;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import tool.Action;

public class ChangeOfBelongingClassAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	HttpSession session = req.getSession();
    	int classnum = (int) session.getAttribute("classnum");
    	
        UserDao userDao = new UserDao();
        List<User> students = userDao.class_(classnum);
        
        // セッションに保存
        req.getSession().setAttribute("students", students);
        req.getSession().setAttribute("classnum", classnum);  // ← 追加

        req.getRequestDispatcher("/main/teacher/change_of_belonging_class.jsp")
           .forward(req, res);
    }
}