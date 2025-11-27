package bean;

import java.io.Serializable;

public class Attendance implements Serializable {

	private int student_id;
	private String date;
	private String status;
	private String updatetime;

	public int getId() {
		return student_id;
	}

	public void setId(int student_id) {
		this.student_id = student_id;
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
