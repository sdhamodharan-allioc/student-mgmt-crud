package studentmgmt.model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Student {
	protected int rollnumber;
	protected String name;
	protected String email;
	protected String school;

	public Student(int rollnumber, String name, String email, String school) {
		this.rollnumber = rollnumber;
		this.name = name;
		this.email = email;
		this.school = school;
	}

	public Student(String name, String email, String school) {
		this.name = name;
		this.email = email;
		this.school = school;
	}

	public int getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(int rollnumber) {
		this.rollnumber = rollnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Override
	public String toString() {
		return "Student{" +
				"rollnumber=" + rollnumber +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", school='" + school + '\'' +
				'}';
	}
}
