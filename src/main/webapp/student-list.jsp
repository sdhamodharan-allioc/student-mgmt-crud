<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Student Management Application</title>
</head>
<body>
	<center>
		<h1>Student Management</h1>
        <h2>
        	<a href="new">Add New Student</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Students</a>
        	
        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Students</h2></caption>
            <tr>
                <th>RollNumber</th>
                <th>Name</th>
                <th>Email</th>
                <th>School</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="student" items="${listStudents}">
                <tr>
                    <td><c:out value="${student.rollnumber}" /></td>
                    <td><c:out value="${student.name}" /></td>
                    <td><c:out value="${student.email}" /></td>
                    <td><c:out value="${student.school}" /></td>
                    <td>
                    	<a href="edit?rollnumber=<c:out value='${student.rollnumber}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?rollnumber=<c:out value='${student.rollnumber}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>
