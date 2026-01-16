package Student;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AttendanceDao;
import tool.Action;

public class MyDiaryAction extends Action {
	
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String id = req.getParameter("id");
//		String id = "240001"; テスト用
		
		try {
			ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			AttendanceDao aDao = new AttendanceDao();
			
			list = aDao.all(id);
					
			req.setAttribute("attendanceList", list);
			req.getRequestDispatcher("../main/student/mydiary.jsp").forward(req, res);
			
		}catch(Exception e) {
			
		}
		
	}
}
