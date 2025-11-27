package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.TotalAbsences;

public class TotalAbsencesDao extends Dao{

	public TotalAbsences get(int id) throws Exception{
		TotalAbsences total = new TotalAbsences();
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
		return total;
	}





}
