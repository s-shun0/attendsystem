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

        // ===== ソートパラメータ =====
        String sort = Optional.ofNullable(req.getParameter("sort"))
                              .orElse("name");

        // ===== クラス番号の取得 =====
        String classStr = req.getParameter("classnum");

        // ハンバーガー経由の場合はセッションから
        if (classStr == null) {
            Object s = req.getSession().getAttribute("classnum");
            if (s != null) {
                classStr = s.toString();
            }
        }

        Integer classnum = null;
        if (classStr != null && !classStr.isEmpty()) {
            classnum = Integer.parseInt(classStr);
        }

        // デバッグ
        System.out.println("classnum = " + classnum);

        List<Map<String, Object>> rows = new ArrayList<>();

        try (Connection con = new Dao().getConnection()) {

            // ===== SQL 作成 =====
            StringBuilder sql = new StringBuilder();

            sql.append(
            "SELECT " +
            "  u.id, " +
            "  u.name, " +

            "  SUM(CASE WHEN a.status = 'present' THEN 1 ELSE 0 END) AS present, " +
            "  SUM(CASE WHEN a.status = 'absent' THEN 1 ELSE 0 END) AS absences, " +
            "  SUM(CASE WHEN a.status = 'late' THEN 1 ELSE 0 END) AS tardiness, " +
            "  SUM(CASE WHEN a.status = 'early_leave' THEN 1 ELSE 0 END) AS leaving_early, " +
            "  SUM(CASE WHEN a.status = 'other' THEN 1 ELSE 0 END) AS other " +

            "FROM users u " +
            "LEFT JOIN attendance a ON u.id = a.student_id " +

            "WHERE u.job <> '教員' " +
            "AND u.name IS NOT NULL " +
            "AND TRIM(u.name) <> '' "
            );

            boolean useClass = (classnum != null);

            if (useClass) {
                sql.append("AND u.class = ? ");
            }

            sql.append("GROUP BY u.id, u.name");

            // ===== PreparedStatement =====
            PreparedStatement ps = con.prepareStatement(sql.toString());

            if (useClass) {
                ps.setInt(1, classnum);
            }

            // ===== 実行 =====
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int absences  = rs.getInt("absences");
                int tardiness = rs.getInt("tardiness");
                int leaving   = rs.getInt("leaving_early");
                int other     = rs.getInt("other");

                // 遅刻・早退は3回で1欠席
                double tardinessWeighted = tardiness / 3.0;
                double leavingWeighted   = leaving / 3.0;

                double weighted =
                    absences + tardinessWeighted + leavingWeighted;

                // 80以上ならその他も加算
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

            rs.close();
            ps.close();
        }

        // ===== 空白名の除去 =====
        rows.removeIf(r -> {
            String name = String.valueOf(r.get("name"));
            return name == null || name.trim().isEmpty();
        });

        // ===== ソート =====
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

        // デバッグ表示
        System.out.println("---- 並び替え結果 ----");
        for (Map<String,Object> r : rows) {
            System.out.println(r.get("name") + " / " + r.get("weighted"));
        }

        // JSPへ
        req.setAttribute("rows", rows);
        req.setAttribute("classnum", classnum);
        req.setAttribute("rows", rows);
        req.getRequestDispatcher("/main/teacher/absence_archive.jsp")
           .forward(req, resp);

    }
}
