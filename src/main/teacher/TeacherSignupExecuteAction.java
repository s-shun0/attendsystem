package main.teacher;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class TeacherSignupExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        int ent_year = 0; // 画面で使うだけ（DBに列が無いなら保存しない）
        String student_no = req.getParameter("no");
        String student_name = req.getParameter("name");
        String email = req.getParameter("email");   // 画面のnameに合わせて。mailなら mail に変える
        String job = req.getParameter("job");       // 画面に無いなら固定で "生徒" にする
        String classStr = req.getParameter("class_num");

        StudentDao studentDao = new StudentDao();
        Map<String, String> errors = new HashMap<>();

        // ent_year（画面のnameに合わせて）
        String entYearStr = req.getParameter("ent_year");
        if (entYearStr != null && !entYearStr.isBlank()) {
            try {
                ent_year = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                ent_year = 0;
            }
        }

        int classnum = 0;
        if (classStr != null && !classStr.isBlank()) {
            try {
                classnum = Integer.parseInt(classStr);
            } catch (NumberFormatException e) {
                classnum = 0;
            }
        }

        // バリデーション
        if (ent_year == 0) errors.put("1", "入学年度を選択してください");
        if (student_no == null || student_no.isBlank()) errors.put("2", "学生番号を入力してください");
        if (student_name == null || student_name.isBlank()) errors.put("3", "氏名を入力してください");
        if (classnum == 0) errors.put("4", "クラスを選択してください");

        // job が画面に無い場合は固定にしてOK
        if (job == null || job.isBlank()) job = "生徒";

        // 重複チェック
        if (errors.isEmpty() && studentDao.getStudentById(student_no) != null) {
            errors.put("5", "学生番号が重複しています");
        }

        // 登録
        if (errors.isEmpty()) {
            Student student = new Student();
            student.setId(student_no);
            student.setName(student_name);
            student.setEmail(email);      
            student.setJob(job);
            student.setClassnum(classnum);
        }

        // 画面へ返す
        req.setAttribute("errors", errors);
        req.setAttribute("ent_year", ent_year);
        req.setAttribute("no", student_no);
        req.setAttribute("name", student_name);
        req.setAttribute("email", email);
        req.setAttribute("job", job);
        req.setAttribute("class_num", classnum);

        if (errors.isEmpty()) {
            req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
        } else {
        	req.getRequestDispatcher("student_create.jsp").forward(req, res);


        }
    }
}


