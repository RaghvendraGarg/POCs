<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
</head>
<body>
<div align="center">
<span style="color: red;">${noresults}</span>
	<table style="width: 80%" border="1 px; solid">
	<tr>
	<td height="40px" align="center" colspan="4"><h1>Search Results</h1></td>
	</tr>
	<tr>
		<th>Book Isbn No</th>
		<th>Book Name</th>
		<th>Author Names</th>
		<th>Book Details</th>
	</tr>
	<c:forEach var="book" items="${searchResult}">
   		<tr>
   			<td>${book.bookIsbnNumber}</td>
   			<td>${book.bookName}</td>
   			<td>${book.authorNames}</td>
   			<td>${book.bookDetails}</td>
   		
   		</tr>   
    </c:forEach>
</table>
<a href="search.do" >Search Again</a>
</div>
</body>
</html>