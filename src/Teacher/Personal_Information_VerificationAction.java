package Teacher;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Attendance;
import bean.Student;
import dao.AttendanceDao;
import dao.StudentDao;
import tool.Action;

public class Personal_Information_VerificationAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {

        String studentId = req.getParameter("studentId");

        // ===== 学生取得 =====
        StudentDao sdao = new StudentDao();
        List<Student> all = sdao.getAllStudents();

        Student target = null;
        for (Student s : all) {
            if (s.getId().equals(studentId)) {
                target = s;
                break;
            }
        }

        if (target == null) {
            req.setAttribute("error", "学生が存在しません");
            req.getRequestDispatcher("/main/error.jsp").forward(req, resp);
            return;
        }

        Map<String, Object> student = new HashMap<>();
        student.put("id", target.getId());
        student.put("name", target.getName());
        student.put("classnum", target.getClassnum());

        // ===== DBから出欠取得 =====
        AttendanceDao adao = new AttendanceDao();
        List<Attendance> attendList =
                adao.getByStudent(studentId);

        // 月順
        List<Integer> months =
            Arrays.asList(4,5,6,7,8,9,10,11,12,1,2,3);

        Map<String, Map<String, String>> attendanceMap = new HashMap<>();

        int present = 0;
        int absent = 0;
        int late = 0;
        int other = 0;
        int leaving = 0;

        // 初期化
        for (int m : months) {
            attendanceMap.put(String.valueOf(m), new HashMap<>());
        }

        System.out.println("---- 変換開始 ----");

        for (Attendance a : attendList) {

            String[] sp = a.getDate().split("-");

            String month = String.valueOf(Integer.parseInt(sp[1]));
            String day   = String.valueOf(Integer.parseInt(sp[2]));

            String mark = "";

            switch (a.getStatus()) {
                case "present":
                    mark = "〇"; present++; break;

                case "absent":
                    mark = "×"; absent++; break;

                case "late":
                    mark = "△"; late++; break;
                case "other":
                    mark = "□"; other++; break;
                case "early_leave":
                    mark = "▽"; leaving++; break;
            }

            System.out.println("登録: " + month + "/" + day + " = " + mark);

            if (attendanceMap.containsKey(month)) {
                attendanceMap.get(month).put(day, mark);
            } else {
                System.out.println("★ monthキーなし: " + month);
            }
        }


        

        // ===== 集計 =====
        Map<String, Integer> summary = new HashMap<>();
        summary.put("present", present);
        summary.put("absent", absent);
        summary.put("late", late);
        summary.put("early_leave", leaving);
        summary.put("other", other);

        req.setAttribute("student", student);
        req.setAttribute("months", months);
        req.setAttribute("attendanceMap", attendanceMap);
        req.setAttribute("summary", summary);

        req.getRequestDispatcher(
            "/main/teacher/personal_information_verification.jsp"
        ).forward(req, resp);
    }
}
