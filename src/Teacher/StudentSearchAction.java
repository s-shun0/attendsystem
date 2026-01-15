package Teacher;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentSearchAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	HttpSession session = req.getSession();
    	
    	String keyword = req.getParameter("studentId");
    	int classnum = (int) session.getAttribute("classnum");

    	StudentDao dao = new StudentDao();
    	List<Student> students;

    	if (keyword == null || keyword.isEmpty()) {
    	    students = dao.getStudentsByClass(classnum);
    	} else {
    	    students = dao.searchStudentsByIdAndClass(keyword, classnum);
    	}

    	req.setAttribute("students", students);



        req.getRequestDispatcher("/main/teacher/student_search.jsp")
           .forward(req, res);
    }
}