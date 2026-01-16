package tool;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;

@WebServlet("/tool/ClassChangeServlet")
public class ClassChangeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("ClassChangeServlet 到達");

        String newClass = request.getParameter("newClass");
        String[] studentIds = request.getParameterValues("studentIds");

        System.out.println("newClass=" + newClass);
        System.out.println("studentIds=" + java.util.Arrays.toString(studentIds));

        StudentDao dao = new StudentDao();

        if (studentIds != null && newClass != null && !newClass.isEmpty()) {
            int newClassNum = Integer.parseInt(newClass);
            for (String id : studentIds) {
                try {
                    dao.updateClass(id, newClassNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
     // セッションを取得（false にすると無ければ null が返る）
        HttpSession session = request.getSession(false);
        
        Integer classnum = (Integer) session.getAttribute("classnum");

        // クラス変更後に新しく選択しているクラスの情報を取得
        List<Student> students;
        try {
            students = dao.getStudentsByClass(classnum);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        request.setAttribute("students", students);

        // 再表示
        request.getRequestDispatcher("/main/teacher/change_of_belonging_class.jsp")
               .forward(request, response);
    }
}
