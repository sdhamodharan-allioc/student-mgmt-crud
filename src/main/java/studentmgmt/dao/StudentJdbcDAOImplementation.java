package studentmgmt.dao;

import studentmgmt.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 *
 */
public class StudentJdbcDAOImplementation implements StudentDAOInterface {
	//private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false&characterEncoding=latin1&useConfigs=maxPerformance";
	private String jdbcUsername = "success";
	private String jdbcPassword = "welcome";

	private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "  (name, email, school) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_STUDENT_BY_ROLLNUMBER = "select rollnumber, name, email, school from student where rollnumber = ?;";
	private static final String SELECT_ALL_STUDENTS = "select * from student";
	private static final String DELETE_STUDENT_SQL = "delete from student where rollnumber = ?;";

	private static final String UPDATE_STUDENT_SQL = "update student set name = ?,email= ?, school =? where rollnumber = ?;";

	public StudentJdbcDAOImplementation() {
	}

//	public static Connection getConnection() throws SQLException {
//
//		final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
//		try {
//			// Importing and registering drivers
//			Class.forName(JDBC_DRIVER);
//
//			Connection con = DriverManager.getConnection(
//					"jdbc:mariadb://localhost:3306/demo",
//					"root", "MariaDB@123");
//			// here,root is the username and 1234 is the
//			// password,you can set your own username and
//			// password.
//			return con;
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	public void insertStudent(Student student) throws SQLException {
		System.out.println(INSERT_STUDENT_SQL);

		// try-with-resource statement will auto close the connection.
		try (Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getEmail());
			preparedStatement.setString(3, student.getSchool());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Student selectStudent(int rollnumber) {
		Student student = null;
		// Step 1: Establishing a Connection
		try (Connection connection = DBConnection.getConnection();) {

			 // Step 2:Create a statement using connection object
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ROLLNUMBER);
			 preparedStatement.setInt(1, rollnumber);
			 System.out.println(preparedStatement);
			 // Step 3: Execute the query or update query
			 ResultSet rs = preparedStatement.executeQuery();

			 // Step 4: Process the ResultSet object.
			while(rs.next()) {
			String name = rs.getString("name");
			String email = rs.getString("email");
			String school = rs.getString("school");
			student = new Student(rollnumber, name, email, school);
		}
	}
	catch(SQLException e){
		printSQLException(e);
	}
	return student;
}

	public List<Student> selectAllStudents() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Student> students = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = DBConnection.getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int rollnumber = rs.getInt("rollnumber");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String school = rs.getString("school");
				students.add(new Student(rollnumber, name, email, school));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
	}

	public boolean deleteStudent(int rollnumber) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
			statement.setInt(1, rollnumber);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateStudent(Student student) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
			statement.setString(1, student.getName());
			statement.setString(2, student.getEmail());
			statement.setString(3, student.getSchool());
			statement.setInt(4, student.getRollnumber());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
