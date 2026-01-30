package Student;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.User;
import dao.AttendanceDao;
import dao.UserDao;
import tool.Action;

public class MyDiaryAction extends Action {
	
	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession();
		String password = (String) session.getAttribute("password");
		String id =(String) session.getAttribute("id");
//		String id = "240001"; //テスト用
//		String password="tky240001";
		
		try {
			ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
			AttendanceDao aDao = new AttendanceDao();
			
			list = aDao.all(id);
			int sum = 0;
			
			for (ArrayList<Integer> List : list) {
			    for (int i = 0; i < List.size(); i++) {
			        Integer num = List.get(i);
			        if (i == List.size() - 1) {  // 最後の要素
			            sum += num;
			        }
			    }
			}
			
			User user = new User();
			UserDao uDao = new UserDao();
		
			
			
			user = uDao.login(id,password);
			
			String message = "";
			int num=0;
			
			sum = Math.round(sum/10);
			if (sum < 20) {
				num = 20-sum;
				message="訓告通知まであと"+num+"日";
			}else if (sum < 40) {
				num = 40-sum;
				message="戒告通知まであと"+num+"日";
			}else {
				num = 60-sum;
				message="再戒告通知まであと"+num+"日";
			}
	
			String sum_mesage="累計欠席数"+sum+"日";
			req.setAttribute("message", message);
			req.setAttribute("sum", sum_mesage);
			req.setAttribute("user", user);
			req.setAttribute("attendanceList", list);
			req.getRequestDispatcher("../main/student/mydiary.jsp").forward(req, res);
			
		}catch(Exception e) {
			
		}
		
	}
}
