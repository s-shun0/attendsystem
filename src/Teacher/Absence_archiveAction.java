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

        // ソートパラメータ取得（デフォルトは名前順）
        String sort = Optional.ofNullable(req.getParameter("sort"))
                              .orElse("name");

        // クラス番号パラメータ
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

            // 出欠を attendance から集計
            "  SUM(CASE WHEN a.status = 'present' THEN 1 ELSE 0 END) AS present, " +
            "  SUM(CASE WHEN a.status = 'absent' THEN 1 ELSE 0 END) AS absences, " +
            "  SUM(CASE WHEN a.status = 'late' THEN 1 ELSE 0 END) AS tardiness, " +
            "  SUM(CASE WHEN a.status = 'leaving' THEN 1 ELSE 0 END) AS leaving_early, " +
            "  SUM(CASE WHEN a.status = 'other' THEN 1 ELSE 0 END) AS other " +

            "FROM users u " +
            "LEFT JOIN attendance a ON u.id = a.student_id " +
            "WHERE u.job <> '教員' "
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

                        int present   = rs.getInt("present");
                        int absences  = rs.getInt("absences");
                        int tardiness = rs.getInt("tardiness");
                        int leaving   = rs.getInt("leaving_early");
                        int other     = rs.getInt("other");

                        // ===== 重み付き欠席累計 =====
                        // 遅刻・早退は3回で1欠席換算
                        double tardinessWeighted = tardiness / 3.0;
                        double leavingWeighted   = leaving / 3.0;

                        // 基本欠席累計
                        double weighted = absences + tardinessWeighted + leavingWeighted;

                        // その他欠席は通常累計に含めないが、累計が80以上で加算
                        if (weighted >= 80) {
                            weighted += other;
                        }

                        // Map に格納
                        Map<String, Object> row = new HashMap<>();
                        row.put("id", rs.getString("id"));
                        row.put("name", rs.getString("name"));

                        row.put("present", present);
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

        // ===== ソート処理 =====
        switch (sort) {
            case "absence":
                rows.sort(
                    Comparator.<Map<String, Object>, Double>
                        comparing(r -> (Double) r.get("weighted"))
                        .reversed()
                );
                break;

            case "id":
                rows.sort(Comparator.comparing(r -> (String) r.get("id")));
                break;

            default:
                rows.sort(Comparator.comparing(r -> (String) r.get("name")));
        }

        req.setAttribute("rows", rows);
        req.getRequestDispatcher("/main/teacher/absence_archive.jsp")
           .forward(req, resp);
    }
}
