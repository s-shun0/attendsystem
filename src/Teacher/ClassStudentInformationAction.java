package Teacher;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class ClassStudentInformationAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	HttpSession session = req.getSession();
    	int classnum = (int) session.getAttribute("classnum");
    	
        StudentDao StudentDao = new StudentDao();
        List<Student> students = StudentDao.getStudentsByClass(classnum);
        
        // セッションに保存
        req.getSession().setAttribute("students", students);
        req.getSession().setAttribute("classnum", classnum);  // ← 追加
    	
        req.getRequestDispatcher("/main/teacher/class_student_information.jsp")
           .forward(req, res);
    }
}