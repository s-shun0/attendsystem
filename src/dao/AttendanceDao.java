package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Attendance;


public class AttendanceDao extends Dao{

	private String baseSql = "select * from users where id=? and password=?; ";

	//qrコードで出席
	public boolean attend(String id,String password) throws Exception{


		Date date = new Date();
		//データベース用
		SimpleDateFormat  fm1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat  fm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// HH で24時間制に変更

		String FM1 =	fm1.format(date);
		String FM2 =	fm2.format(date);

		//遅刻・欠席の判定用
		SimpleDateFormat  check = new SimpleDateFormat("HHmm");
		String c =	check.format(date);
		int C = Integer.parseInt(c);

		Connection connection = getConnection();
		PreparedStatement statement = null;
		PreparedStatement Statement = null;

		String status="";
		if (C > 1240 ){
			status ="absent";
		}else if(C > 915){
			status ="late";
		}else{
			status = "present";
		}
		int count=0;
		String job="";
		
		try{
			//教員の時はいかないようにする
			statement = connection.prepareStatement(baseSql + "and job <> '教員'");

			statement.setString(1,id);
			statement.setString(2,password);
			
			//ここで対応する生徒がいるかの確認
			ResultSet rSet = statement.executeQuery();
			
			if(rSet.next()){
				Statement = connection.prepareStatement("insert into attendance (student_id,date,status,updatetime) values(?,?,?,?)");
				Statement.setString(1,id);
				Statement.setString(2,FM1);
				Statement.setString(3,status);
				Statement.setString(4,FM2);
			}
		count = Statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(); 
			throw e;
		} finally {
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
		if (count > 0 && job != "教員"){
			return true;
		} else{
			return false;
		}
	}

	public ArrayList<ArrayList<Integer>> all(String id)throws Exception{
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		//今年
		LocalDateTime nowDate = LocalDateTime.now();
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy"); // ①
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MM");
		String month = dtf2.format(nowDate);
		String month_next="";
		
		int tmp1 = Integer.parseInt(month);
		String year = dtf1.format(nowDate);			
		String next="";
		
		int tmp2 = Integer.parseInt(year);
		if (tmp1 < 4) {
			next = String.valueOf(tmp2);
			year= String.valueOf(tmp2-1);
		}else {
			next = String.valueOf(tmp2);
		}
		

		String base="select * from attendance where student_id=? ";
		String condition="ORDER BY date ASC;";
		
		//欠席数計算
		String num ="";
		int present = 0;
		int absent =0;
		int late = 0;
		int early_leave = 0;
		int excused =0;
		int other =0;
		int all= 0;
		
		
		try{
			for(int i=1;i<=12;i++){
				ArrayList<Integer> List = new ArrayList<Integer>();
				
				if (i<10){
					month=String.valueOf(i+3);
					month_next=String.valueOf(i+4);
					statement = connection.prepareStatement(base+"AND date >= '"+year+"-"+month+"-01'"
																+ "AND date <  '"+year+"-"+month_next+"-01'"
																+condition);
				}else{
					month=String.valueOf(i-9);
					statement = connection.prepareStatement(base+"AND date >= '"+next+"-"+month+"-01'"
																+ "AND date <  '"+next+"-"+month_next+"-01'"
																+condition);
				}
				statement.setString(1,id);

				try {
					ResultSet rSet = statement.executeQuery();
					if (rSet.next()){
						num = rSet.getString("status");
						if(num.equals("late")) {
							late++;
						}else if (num.equals("early_leave")) {
							early_leave++;
						}else if (num.equals("absent")) {
							absent++;
						}else if(num.equals("present")) {
							present++;
						}else if (num.equals("excused")) {
							excused++;
						}else {
							other++;
						}
					}
					
					int num3 =late+early_leave;
					all =((absent + (Math.round(num3)/3)) * 10+ ((num3)%3)*3);
					
					
					List.add(present);
					List.add(absent);
					List.add(late);
					List.add(early_leave);
					List.add(excused);
					List.add(other);
					List.add(all);
				}catch(Exception e) {
					
					List.add(present);
					List.add(absent);
					List.add(late);
					List.add(early_leave);
					List.add(excused);
					List.add(other);
					List.add(all);
					
				}
				list.add(List);
			}

		} catch (Exception e) {
			throw e;
		} finally {  
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

	public ArrayList<Attendance> tracker(String class_,String date)throws Exception {
		ArrayList<Attendance> list = new ArrayList<Attendance>();
		Attendance att = new Attendance();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String classnum="";
		String day="";
		try{
			if (class_ != null) {
				classnum = "users.class=? ";
				if (date != null) {
					day = "and attendance.date=?";
				}
			}
			else if (date != null) {
				day = "attendance.date=?";
			}
			
			//プリペアードスタートメントにSQL文をセット
			if (classnum == day) {
				statement = connection.prepareStatement("select * from attendance INNER JOIN users "
														+ " on attendance.student_id = users.id ");
			}else {
				statement = connection.prepareStatement("select * from attendance "
											+ "INNER JOIN users on attendance.student_id = users.id"
											+ " where "+ classnum + day);
				//学生番号をバインド
				statement.setString(1,class_);
				statement.setString(2, date);
			}
			//実行
			ResultSet rSet = statement.executeQuery();


			if (rSet.next()){
				//学生インスタンスに検索結果をセット
				att.setId(rSet.getString("student_id"));
				att.setName(rSet.getString("name"));
				att.setDate(rSet.getString("date"));
				att.setStatus(rSet.getString("status"));
				att.setUpdate(rSet.getString("updatetime"));
				
				list.add(att);
			} else{
				att = null;
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
		
		
		return list;
	}
	
	// ===============================
	// 個人出欠取得（新規）
	// ===============================
	public List<Attendance> getByStudent(String studentId) throws Exception {

	    List<Attendance> list = new ArrayList<>();

	    String sql =
	        "select * from attendance " +
	        "where student_id = ? " +
	        "order by date asc";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, studentId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Attendance a = new Attendance();

	            a.setId(rs.getString("student_id"));
	            a.setDate(rs.getString("date"));
	            a.setStatus(rs.getString("status"));
	            a.setUpdate(rs.getString("updatetime"));

	            list.add(a);
	        }
	    }

	    return list;
	}

	
	

	//主席編集の追加

}