package viewMultiParsed;

import javafx.beans.property.SimpleStringProperty;

public class EmployeeClass {
	private final SimpleStringProperty name;
	private final SimpleStringProperty email;
	private final SimpleStringProperty mobile;
	private final SimpleStringProperty add;
	private final SimpleStringProperty dob;
	private final SimpleStringProperty sex;
	private final SimpleStringProperty fname;
	
	public EmployeeClass(String name, String email, String mobile, String add, String dob, String sex,
			String fname) {
		super();
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.mobile = new SimpleStringProperty(mobile);
		this.add = new SimpleStringProperty(add);
		this.dob = new SimpleStringProperty(dob);
		this.sex = new SimpleStringProperty(sex);
		this.fname = new SimpleStringProperty(fname);
	}
	public String getName() {
		return name.get();
	}
	public String getEmail() {
		return email.get();
	}
	public String getMobile() {
		return mobile.get();
	}
	public String getAdd() {
		return add.get();
	}
	public String getDob() {
		return dob.get();
	}
	public String getSex() {
		return sex.get();
	}
	public String getFname() {
		return fname.get();
	}
}
