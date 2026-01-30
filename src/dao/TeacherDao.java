package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.User;

public class TeacherDao extends Dao {

    public int updateTeacher(User t, String oldId) throws Exception {

        String sql = "UPDATE users SET id = ?, name = ?, email = ?, password = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getId());    // newId
            ps.setString(2, t.getName());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getPass());
            ps.setString(5, oldId);        // oldId

            return ps.executeUpdate();
        }
    }
}
