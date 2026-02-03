package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.User;

public class UserDao extends Dao{

	private String baseSql = "select * from users where id=?";

	public User login(String id,String pass) throws Exception {
		User user = new User();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try{
			//プリペアードスタートメントにSQL文をセット
			statement = connection.prepareStatement("select * from users where id=? and password=?");
			//学生番号をバインド
			statement.setString(1,id);
			statement.setString(2, pass);
			//実行
			ResultSet rSet = statement.executeQuery();


			if (rSet.next()){
				//学生インスタンスに検索結果をセット
				user.setId(rSet.getString("id"));
				user.setName(rSet.getString("name"));
				user.setEmail(rSet.getString("email"));
				user.setPass(rSet.getString("password"));
				user.setJob(rSet.getString("job"));
				user.setClassnum(rSet.getString("class"));

			} else{
				user = null;
			}
		} catch (Exception e){
			throw e;
		}finally {
			if (statement != null){
				try {
					statement.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			if (connection != null){
				try {
					connection.close();
				} catch(SQLException e){
					throw e;
				}
			}
		}
		return user;

	}






//	public List<User> filter(int id,String name,String email,String job,int classnum) throws Exception{
//		List<User> list = new ArrayList<>();
//		Connection connection = getConnection();
//		PreparedStatement statement = null;
//		//リザルトセット
//		ResultSet rSet = null;
//		//SQL文の条件
//		String condition = "and =? and class_num=?";
//		//SQL文のソート
//		String order = " order by no asc";
//
//		String conditionIsAttend = "";
//
//
//		try {
//			statement =connection.prepareStatement(baseSql + conditionIsAttend + order);
//			statement.setString(1,school.getCd());
//			rSet = statement.executeQuery();
//			list = postFilter(rSet,school);
//		} catch (Exception e){
//			throw e;
//		} finally {
//			if (statement != null) {
//				try{
//					statement.close();
//				} catch (SQLException sqle){
//					throw sqle;
//				}
//			}
//			if (connection != null){
//				try{
//					connection.close();
//				} catch (SQLException sqle){
//					throw sqle;
//				}
//			}
//		}
//
//
//		return list;
//	}

	public List<User> class_(int classnum) throws Exception{
		List<User> list = new ArrayList<User>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try{
			

			statement = connection.prepareStatement("select * from users where class=?");
			statement.setInt(1,classnum);

			ResultSet rSet = statement.executeQuery();

			while (rSet.next()){
				User user = new User();
				//学生インスタンスに検索結果をセット
				user.setId(rSet.getString("id"));
				user.setName(rSet.getString("name"));
				user.setEmail(rSet.getString("email"));
				user.setPass(rSet.getString("password"));
				user.setJob(rSet.getString("job"));
				user.setClassnum(rSet.getString("class"));

				list.add(user);
			}
		}catch(Exception e){
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


		return list;

	}







	//emailのかくにん
	public User send(String email) throws Exception{
		Connection connection = getConnection();
		PreparedStatement statement = null;
		User user = new User();

		try{
			statement = connection.prepareStatement("select * from users where email=?");
			statement.setString(1,email);

			ResultSet rSet = statement.executeQuery();

			if (rSet.next()){
				//学生インスタンスに検索結果をセット
				user.setId(rSet.getString("id"));
				user.setName(rSet.getString("name"));
				user.setEmail(rSet.getString("email"));
				user.setPass(rSet.getString("password"));
				user.setJob(rSet.getString("job"));
				user.setClassnum(rSet.getString("class"));


			} else{
				user = null;
			}

		}catch(Exception e){
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
		return user;
	}

	//生徒追加必要か不明
//	public boolean insert(User user) throws Exception{
//		Connection connection = getConnection();
		//プリペアードスタートメント
//		PreparedStatement statement = null;
//		int count = 0;


//		try{
//			User old = login(user.getId(),user.getPass());
//			if (old == null){
				//学生が存在しない場合
				//プリペアードスタートメントにINSERT文をセット
//				statement = connection.prepareStatement(
//						"insert into users (id,name,email,password,job,class) values(?,?,?,?,?,?)");
				//プリペアードスタートメントに値をバインド
//				statement.setInt(1, Integer.parseInt(user.getId()));
//				statement.setString(2,user.getName());
//				statement.setString(3, user.getEmail());
//				statement.setString(4,user.getPass());
//				statement.setString(5,user.getJob());
//				statement.setInt(6,Integer.parseInt(user.getId()));
//			}
//			count = statement.executeUpdate();
//		} catch (Exception e){
//			throw e;
//		} finally {
//			if (statement != null){
//				try {
//					statement.close();
//				} catch (SQLException sqle){
//					throw sqle;
//				}
//			}
//			if (connection != null){
//				try {
//					connection.close();
//				} catch (SQLException sqle){
//					throw sqle;
//				}
//			}
//		}

//		if (count > 0){
//			return true;
//		} else{
//			return false;
//		}
//	}
	
	public boolean insert(User user) throws Exception {

	    // まずID重複チェック（IDだけで判定）
	    if (getById(user.getId()) != null) {
	        return false; // 既に存在
	    }

	    String sql = "insert into users (id, name, email, password, job, class) values (?, ?, ?, ?, ?, ?)";

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, user.getId());
	        statement.setString(2, user.getName());
	        statement.setString(3, user.getEmail());
	        statement.setString(4, user.getPass());
	        statement.setString(5, user.getJob());

	        // 教員はクラス不要なら 0（DBがNULL許容なら setNullでもOK）
	        statement.setInt(6, 0);

	        int count = statement.executeUpdate();
	        return count > 0;
	    }
	}





	//パスワード変更
	public boolean change(User user,String newpass) throws Exception{
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		try{
			statement = connection.prepareStatement("update users set password=? where id=? and name=? and email=?");
			statement.setString(1,newpass);
			statement.setString(2,user.getId() );
			statement.setString(3, user.getName());
			statement.setString(4,user.getEmail());

			count = statement.executeUpdate();

		}catch(Exception e){
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



		if (count > 0){
			return true;
		} else{
			return false;
		}
	}






	public String getUserId(String email) {
		String userId = "";
		return userId;
	}

	
	
	// idでユーザー取得（存在確認用）
	public User getById(String id) throws Exception {
	    User user = null;
	    String sql = "select * from users where id=?";

	    try (Connection connection = getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {

	        statement.setString(1, id);

	        try (ResultSet rSet = statement.executeQuery()) {
	            if (rSet.next()) {
	                user = new User();
	                user.setId(rSet.getString("id"));
	                user.setName(rSet.getString("name"));
	                user.setEmail(rSet.getString("email"));
	                user.setPass(rSet.getString("password"));
	                user.setJob(rSet.getString("job"));
	                user.setClassnum(rSet.getString("class"));
	            }
	        }
	    }
	    return user;
	}

}



