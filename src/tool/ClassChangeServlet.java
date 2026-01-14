package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.StudentDao;

@WebServlet("/tool/ClassChangeServlet")
public class ClassChangeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String[] ids = request.getParameterValues("studentIds");
        String newClass = request.getParameter("newClass");

        if (ids != null && newClass != null && !newClass.isEmpty()) {
            StudentDao dao = new StudentDao();
            try {
                for (String id : ids) {
                    dao.updateClass(id, Integer.parseInt(newClass));
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        // PRGパターン：更新後は必ずredirect
        response.sendRedirect(
            request.getContextPath() + "/Teacher/ChangeOfBelongingClass.action"
        );
    }
}