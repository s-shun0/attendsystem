package Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
            "SELECT " +
            "  u.id, " +
            "  u.name, " +

            "  SUM(CASE WHEN a.status = 'present' THEN 1 ELSE 0 END) AS present, " +
            "  SUM(CASE WHEN a.status = 'absent' THEN 1 ELSE 0 END) AS absences, " +
            "  SUM(CASE WHEN a.status = 'late' THEN 1 ELSE 0 END) AS tardiness, " +
            "  SUM(CASE WHEN a.status = 'leaving' THEN 1 ELSE 0 END) AS leaving_early, " +
            "  SUM(CASE WHEN a.status = 'other' THEN 1 ELSE 0 END) AS other " +

            "FROM users u " +
            "LEFT JOIN attendance a ON u.id = a.student_id " +

            // ★★ ここが超重要 ★★
            "WHERE u.job <> '教員' " +
            "AND u.name IS NOT NULL " +
            "AND TRIM(u.name) <> '' "
            );

            if (classnum != null) {
                sql.append("AND u.class = ? ");
            }

            sql.append("GROUP BY u.id, u.name ");

            try (PreparedStatement ps = con.prepareStatement(sql.toString())) {

                if (classnum != null) {
                    ps.setInt(1, classnum);
                }

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        int absences  = rs.getInt("absences");
                        int tardiness = rs.getInt("tardiness");
                        int leaving   = rs.getInt("leaving_early");
                        int other     = rs.getInt("other");

                        double tardinessWeighted = tardiness / 3.0;
                        double leavingWeighted   = leaving / 3.0;

                        double weighted =
                            absences + tardinessWeighted + leavingWeighted;

                        if (weighted >= 80) {
                            weighted += other;
                        }

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

        // ★ Java側でも二重ガード
        rows.removeIf(r -> {
            String name = String.valueOf(r.get("name"));
            return name == null || name.trim().isEmpty();
        });

        // ===== ソート処理 =====
        System.out.println("sort param = " + sort);

        switch (sort) {

        case "absence":
            rows.sort((a, b) -> {
                Double wa = (Double) a.get("weighted");
                Double wb = (Double) b.get("weighted");
                return wb.compareTo(wa);
            });
            break;

        case "id":
            rows.sort((a, b) ->
                ((String)a.get("id")).compareTo((String)b.get("id"))
            );
            break;

        default: // 名前順
            java.text.Collator collator =
                java.text.Collator.getInstance(java.util.Locale.JAPANESE);

            rows.sort((a, b) ->
                collator.compare(
                    String.valueOf(a.get("name")),
                    String.valueOf(b.get("name"))
                )
            );
            break;
        }

        System.out.println("---- 並び替え結果 ----");
        for (Map<String,Object> r : rows) {
            System.out.println(r.get("name") + " / " + r.get("weighted"));
        }

        req.setAttribute("rows", rows);
        req.getRequestDispatcher("/main/teacher/absence_archive.jsp")
           .forward(req, resp);
    }
}
