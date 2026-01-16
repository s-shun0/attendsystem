package bean;

import java.io.Serializable;

public class Attendance implements Serializable {

	private String student_id;
	private String student_name;
	private String date;
	private String status;
	private String updatetime;

	public String getId() {
		return student_id;
	}

	public void setId(String student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return student_name;
	}
	
	public void setName(String student_name) {
		this.student_name = student_name;
	}
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdate() {
		return updatetime;
	}

	public void setUpdate(String updatetime) {
		this.updatetime = updatetime;
	}
}
