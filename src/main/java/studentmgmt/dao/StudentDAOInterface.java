package studentmgmt.dao;

import studentmgmt.model.Student;

import java.sql.SQLException;
import java.util.List;


public interface StudentDAOInterface {

	public void insertStudent(Student student) throws SQLException ;
	public Student selectStudent(int rollnumber);
	public List<Student> selectAllStudents();
	public boolean deleteStudent(int rollnumber) throws SQLException;
	public boolean updateStudent(Student student) throws SQLException;
	
}
