<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<title>Book Store</title>

</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Book Store</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<!-- put new button: Add Student -->

			<input type="button" value="Add Book"
				onclick="window.location.href='add-student-form.jsp'; return false;"
				class="add-student-button" />

			<table>

				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>NXB</th>
					<th>Pirce</th>
					<th>Acction</th>
				</tr>

				<c:forEach var="tempBook" items="${BOOK_LIST}">

					<!-- set up a link for each student -->
					<c:url var="tempLink" value="BookController">
						<c:param name="command" value="LOAD" />
						<c:param name="bookID" value="${tempBook.id}" />
					</c:url>

					<!--  set up a link to delete a student -->
					<c:url var="deleteLink" value="BookController">
						<c:param name="command" value="DELETE" />
						<c:param name="bookID" value="${tempBook.id}" />
					</c:url>
					<tr>
						<td>${tempBook.id}</td>
						<td>${tempBook.name}</td>
						<td>${tempBook.nxb}</td>
						<td>${tempBook.price}</td>
						<td><a href="${tempLink}">Update</a> | <a
							href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">
								Delete</a></td>
					</tr>

				</c:forEach>

			</table>

		</div>

	</div>
</body>
</html>








