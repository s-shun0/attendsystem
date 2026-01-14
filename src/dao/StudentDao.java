package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {

    // 学生一覧取得
    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();

        String sql = "SELECT id, name, job, class FROM users ORDER BY id";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setName(rs.getString("name"));
                s.setJob(rs.getString("job"));
                s.setClassnum(rs.getInt("class"));
                list.add(s);
            }
        }
        return list;
    }

    // クラス更新
    public void updateClass(String id, int newClass) throws Exception {
        String sql = "UPDATE users SET class = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newClass);
            ps.setString(2, id);
            ps.executeUpdate();
        }
    }
}