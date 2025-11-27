package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.ClassNum;

public class ClassNumDao extends Dao{


	//クラスを選択後、クラス情報の取得
	public ClassNum get(String classnum) throws Exception{
		ClassNum num = new ClassNum();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try{
			statement = connection.prepareStatement("");

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

		return num;
	}


}
