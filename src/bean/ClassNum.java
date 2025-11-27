package bean;

import java.io.Serializable;

public class ClassNum implements Serializable{
	private int classnum;
	private int teacher_id;
	private String teacher_name;

	public int getClassnum() {
		return classnum;
	}

	public void setClassnum(int classnum) {
		this.classnum = classnum;
	}

	public int getId() {
		return teacher_id;
	}

	public void setId(int teacher_id) {
		this.teacher_id = teacher_id;
	}

}
