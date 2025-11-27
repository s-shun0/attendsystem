package bean;

import java.io.Serializable;

public class TotalAbsences implements Serializable{
	private int student_id;
	private String absences;
	private String tradiness;
	private String leaving_early;
	private String other;
	private int total;

	public int getId() {
		return student_id;
	}

	public void setId(int student_id) {
		this.student_id = student_id;
	}

	public String getAbsences() {
		return absences;
	}

	public void setAbsences(String absences) {
		this.absences = absences;
	}

	public String getTradiness() {
		return tradiness;
	}

	public void setTradiness(String tradiness) {
		this.tradiness = tradiness;
	}

	public String getLeaving() {
		return leaving_early;
	}

	public void setId(String leaving_early) {
		this.leaving_early = leaving_early;
	}

	public String getOther() {
		return other;
	}

	public void setOhter(String other) {
		this.other = other;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


}
