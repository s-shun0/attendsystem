package Teacher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Attendance;
import dao.AttendanceDao;
import tool.Action;

public class Attendance_TrackerAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
    	
    	String classnum=req.getParameter("classnum");
    	if (classnum == null) {
    	    resp.sendRedirect("/attendsystem/Teacher/ClassSelect.action");
    	    return;
    	}
    	
    	LocalDateTime nowDate = LocalDateTime.now();
    	DateTimeFormatter dtf1 =
    		DateTimeFormatter.ofPattern("yyyy-MM-dd"); // ①
    	String now = dtf1.format(nowDate);
    	String date = req.getParameter("date");
    	


    	ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
    	AttendanceDao aDao = new AttendanceDao();
        if (date != null ) {
        
        	attendanceList = aDao.tracker(classnum,date);
        	 
        	req.setAttribute("attendanceList", attendanceList);
        	req.setAttribute("date", date);
        }else {
        	attendanceList = aDao.tracker(classnum, now);
        	req.setAttribute("date", now);
        	req.setAttribute("attendanceList", attendanceList);
        }
        ArrayList<String> classlist = new ArrayList<String>();
        for (int i=4;i<=6;i++) {
        	for (int j=1;j<=4;j++){
        		date = ""+i+j;
        		classlist.add(date);
        	}
        }
        
        req.setAttribute("classnum", classnum);
        req.setAttribute("classList", classlist);
        req.getRequestDispatcher(
            "/main/teacher/attendance_tracker.jsp"
        ).forward(req, resp);
    }
}
