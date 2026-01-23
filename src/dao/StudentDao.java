package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao extends Dao {
	
	
	public boolean  getLogin(String id, String password) throws Exception{
		boolean count = false;
		
		String sql="select * from users where id=? and password=? and job <> '教員'"; 
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			statement=connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				count = true;
			}
		}catch(Exception e) {
			
			throw e;
		}finally{
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return count;
	}
	
	

    // 全学生取得
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
    public int updateClass(String studentId, int newClass) throws Exception {
        String sql = "UPDATE users SET class = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newClass);
            ps.setString(2, studentId);
            int count = ps.executeUpdate();
            System.out.println("更新 id=" + studentId + " 件数=" + count);
            return count;
        }
    }

    // 特定クラスの学生取得
    public List<Student> getStudentsByClass(int classnum) throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT id, name, job, class FROM users WHERE class = ? AND job <> '教員' ORDER BY id";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, classnum);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getString("id"));
                    s.setName(rs.getString("name"));
                    s.setJob(rs.getString("job"));
                    s.setClassnum(rs.getInt("class"));
                    list.add(s);
                }
            }
        }
        return list;
    }
    
    // 学籍番号（部分一致）＋ クラス指定で生徒取得
    public List<Student> searchStudentsByIdAndClass(String keyword, int classnum) throws Exception {
        List<Student> list = new ArrayList<>();

        String sql = "SELECT id, name, job, class FROM users "
                   + "WHERE class = ? AND id LIKE ? "
                   + "ORDER BY id";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, classnum);
            ps.setString(2, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Student s = new Student();
                    s.setId(rs.getString("id"));
                    s.setName(rs.getString("name"));
                    s.setJob(rs.getString("job"));
                    s.setClassnum(rs.getInt("class"));
                    list.add(s);
                }
            }
        }
        return list;
    }
    
    public int updateStudent(Student s, String oldId) throws Exception {

        String sql = "UPDATE users SET id = ?, name = ?, email = ?, job = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, s.getId());    // newId
            ps.setString(2, s.getName());
            ps.setString(3, s.getEmail());
            ps.setString(4, s.getJob());
            ps.setString(5, oldId);        // oldId

            return ps.executeUpdate();
        }
    }

    // 生徒情報一人分を取得
    public Student getStudentById(String id) throws Exception {

        Student s = null;
        String sql = "SELECT id, name, email, job FROM users WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s = new Student();
                    s.setId(rs.getString("id"));
                    s.setName(rs.getString("name"));
                    s.setEmail(rs.getString("email"));
                    s.setJob(rs.getString("job"));
                }
            }
        }
        return s;
    }
    
    // クラス単位で生徒を一括削除
    public int deleteStudentsByClass(int classnum) throws Exception {

        String sql = "DELETE FROM users WHERE class = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, classnum);
            return ps.executeUpdate(); // 削除件数
        }
    }
}
