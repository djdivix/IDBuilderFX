package viewMultiParsed;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EmployeeClass {
	private SimpleStringProperty name;
	private SimpleStringProperty email;
	private	SimpleStringProperty mobile;
	private SimpleStringProperty add;
	private SimpleStringProperty dob;
	private SimpleStringProperty sex;
	private SimpleStringProperty fname;
	private ImageView photo;
	public String getName() {
		return name.get();
	}

	public void setName(String name1) {
		this.name.set(name1);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email1) {
		this.email.set(email1);
	}

	public String getMobile() {
		return mobile.get();
	}

	public void setMobile(String mobile1) {
		this.mobile.set(mobile1);
	}

	public String getAdd() {
		return add.get();
	}

	public void setAdd(String add1) {
		this.add.set(add1);
	}

	public String getDob() {
		return dob.get();
	}

	public void setDob(String dob1) {
		this.dob.set(dob1);
	}
	public String getSex() {
		return sex.get();
	}

	public void setSex(String sex1) {
		this.sex.set(sex1);
	}

	public String getFname() {
		return fname.get();
	}

	public void setFname(String fname1) {
	this.fname.set(fname1);
	}

	public EmployeeClass(String name, String email, String mobile, String add, String dob, String sex,
			String fname,ImageView photo) {
		super();
		this.name = new SimpleStringProperty(name);
		this.email = new SimpleStringProperty(email);
		this.mobile = new SimpleStringProperty(mobile);
		this.add = new SimpleStringProperty(add);
		this.dob = new SimpleStringProperty(dob);
		this.sex = new SimpleStringProperty(sex);
		this.fname = new SimpleStringProperty(fname);
		photo.setFitHeight(100);
		photo.setFitWidth(100);
		this.photo=photo;
	}

	public ImageView getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo.setImage(photo);;
	}
}