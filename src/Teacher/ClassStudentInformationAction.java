package Teacher;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;

public class ClassStudentInformationAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
        req.getRequestDispatcher("/main/teacher/class_student_information.jsp")
           .forward(req, res);
    }
}