package Teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import tool.Action;

public class ClassSelectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    try {
	        // リクエストパラメータ―の取得
	        int classnum = Integer.parseInt(req.getParameter("classnum"));

	        // DBからクラスに基づいたデータの取得
	        UserDao userDao = new UserDao();
	        List<User> students = userDao.class_(classnum);

	        req.getSession().setAttribute("students", students);

	        req.getRequestDispatcher("/attendsystem/Teacher/AttendanceTracker.action").forward(req, res);


	    } catch (Exception e) {
	        // 何らかのエラーが発生したら error.jsp に飛ばす
	        req.getRequestDispatcher("error.jsp").forward(req, res);
	    }
	}
}