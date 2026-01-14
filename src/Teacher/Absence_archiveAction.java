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
            classnum = Integer.parseInt(classStr);
        }

        List<Map<String, Object>> rows = new ArrayList<>();

        try (Connection con = new Dao().getConnection()) {

            StringBuilder sql = new StringBuilder();
            sql.append(
                "SELECT u.id, u.name, " +
                "COALESCE(t.absences, 0) AS absences, " +
                "COALESCE(t.tardiness, 0) AS tardiness, " +
                "COALESCE(t.`leaving_early`, 0) AS leaving_early, " +
                "COALESCE(t.other, 0) AS other " +
                "FROM users u " +
                "LEFT JOIN total_absences t ON u.id = t.student_id "
            );

            if (classnum != null) {
                sql.append("WHERE u.class = ?");
            }

            try (PreparedStatement ps = con.prepareStatement(sql.toString())) {

                if (classnum != null) {
                    ps.setInt(1, classnum);
                }

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        int absences = rs.getInt("absences");
                        int tardiness = rs.getInt("tardiness");
                        int leaving = rs.getInt("leaving_early");
                        int other = rs.getInt("other");

                        double weighted =
                                absences * 1.0 +
                                tardiness * 0.5 +
                                leaving * 0.5 +
                                other * 0.2;

                        Map<String, Object> row = new HashMap<>();
                        row.put("id", rs.getString("id"));
                        row.put("name", rs.getString("name"));
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

        // ソート
        switch (sort) {
            case "absence":
                rows.sort(Comparator
                        .<Map<String, Object>, Double>comparing(
                                r -> (Double) r.get("weighted"))
                        .reversed());
                break;
            case "id":
                rows.sort(Comparator.comparing(
                        r -> (String) r.get("id")));
                break;
            default:
                rows.sort(Comparator.comparing(
                        r -> (String) r.get("name")));
        }

        req.setAttribute("rows", rows);
        req.getRequestDispatcher(
            "/main/teacher/absence_archive.jsp"
        ).forward(req, resp);
    }
}
