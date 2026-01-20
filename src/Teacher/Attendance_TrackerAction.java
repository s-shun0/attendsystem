package Teacher;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Attendance;
import dao.AttendanceDao;
import tool.Action;

public class Attendance_TrackerAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
    	
    	HttpSession session = req.getSession();
    	Integer classnum = (Integer) session.getAttribute("classnum");
    	String classnumStr = String.valueOf(classnum);
    	if (classnum == null) {
    	    resp.sendRedirect("/attendsystem/Teacher/ClassSelect.action");
    	    return;
    	}
    	String date = req.getParameter("date");
//        
//        List<String> errors = new ArrayList<String>();
//
//        HttpSession session = req.getSession();

        if (date != null ) {
        	ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
        	AttendanceDao aDao = new AttendanceDao();
        	attendanceList = aDao.tracker(classnumStr,date);
        	 
        	req.setAttribute("attendanceList", attendanceList);
        	
        }
        ArrayList<String> classlist = new ArrayList<String>();
        for (int i=4;i<=6;i++) {
        	for (int j=1;j<=4;j++){
        		date = ""+i+j;
        		classlist.add(date);
        	}
        }
        req.setAttribute("classList", classlist);
        req.getRequestDispatcher(
            "/main/teacher/attendance_tracker.jsp"
        ).forward(req, resp);
    }
}
