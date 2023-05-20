package studentmgmt.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import studentmgmt.dao.StudentJdbcDAOImplementation;
import studentmgmt.model.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Ramesh Fadatare
 */

@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentJdbcDAOImplementation studentDAO;
	
	public void init() {
		studentDAO = new StudentJdbcDAOImplementation();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertStudent(request, response);
				break;
			case "/delete":
				deleteStudent(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateStudent(request, response);
				break;
			default:
				listStudent(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Student> listStudent = studentDAO.selectAllStudents();
		request.setAttribute("listStudents", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		System.out.println("RollNumber :" + request.getParameter("rollnumber"));
		int rollnumber = Integer.parseInt(request.getParameter("rollnumber"));
		Student existingUser = studentDAO.selectStudent(rollnumber);
		System.out.println("Existing user :"+existingUser);
		request.setAttribute("student", existingUser);
		request.setAttribute("rollnumber", rollnumber);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);

	}

	private void insertStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String school = request.getParameter("school");
		Student newStudent = new Student(name, email, school);
		studentDAO.insertStudent(newStudent);
		response.sendRedirect("list");
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int rollnumber = Integer.parseInt(request.getParameter("rollnumber"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String school = request.getParameter("school");

		Student student = new Student(rollnumber, name, email, school);
		studentDAO.updateStudent(student);
		response.sendRedirect("list");
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int rollnumber = Integer.parseInt(request.getParameter("rollnumber"));
		studentDAO.deleteStudent(rollnumber);
		response.sendRedirect("list");
	}
}
