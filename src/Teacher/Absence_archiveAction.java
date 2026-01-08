package Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.Dao;
import tool.Action;

public class Absence_archiveAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {

        resp.setContentType("text/html; charset=UTF-8");

        String sort = Optional.ofNullable(req.getParameter("sort"))
                              .orElse("name");

        String classStr = req.getParameter("classnum");
        Integer classnum = null;
        if (classStr != null && !classStr.isEmpty()) {
            try {
                classnum = Integer.parseInt(classStr);
            } catch (NumberFormatException ignored) {}
        }

        List<Map<String, Object>> rows = new ArrayList<>();

        try (Connection con = new Dao().getConnection()) {

            String sql =
                "SELECT id, name FROM users " +
                (classnum != null ? "WHERE class=? " : "");

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                if (classnum != null) {
                    ps.setInt(1, classnum);
                }

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        String sid = rs.getString("id");
                        String name = rs.getString("name");

                        int absences = 0;
                        int tardiness = 0;
                        int leaving = 0;
                        int other = 0;

                        String taSql =
                            "SELECT absences, tardiness, leaving_early, other " +
                            "FROM total_absences WHERE student_id=?";

                        try (PreparedStatement ps2 =
                                 con.prepareStatement(taSql)) {

                            ps2.setString(1, sid);

                            try (ResultSet rs2 = ps2.executeQuery()) {
                                if (rs2.next()) {
                                    absences = rs2.getInt("absences");
                                    tardiness = rs2.getInt("tardiness");
                                    leaving = rs2.getInt("leaving_early");
                                    other = rs2.getInt("other");
                                }
                            }
                        }

                        double weighted =
                                absences * 1.0 +
                                tardiness * 0.5 +
                                leaving * 0.5 +
                                other * 0.2;

                        Map<String, Object> row = new HashMap<>();
                        row.put("id", sid);
                        row.put("name", name);
                        row.put("absences", absences);
                        row.put("tardiness", tardiness);
                        row.put("leaving", leaving);
                        row.put("other", other);
                        row.put("weighted", weighted);

                        rows.add(row);
                    }
                }
            }
        }

        if ("absence".equals(sort)) {
            rows.sort(
                Comparator.<Map<String, Object>, Double>
                    comparing(r -> (Double) r.get("weighted"))
                    .reversed()
            );
        } else {
            rows.sort(Comparator.comparing(r -> (String) r.get("name")));
        }

        req.setAttribute("rows", rows);
        req.setAttribute("classnum", classnum);
        req.setAttribute("selectedSort", sort);

        req.getRequestDispatcher(
            "/main/teacher/student_information_list.jsp"
        ).forward(req, resp);
    }
}
