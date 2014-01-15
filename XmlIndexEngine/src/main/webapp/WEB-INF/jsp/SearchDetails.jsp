<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Books</title>
</head>
<body>
<form:form action="searchDetails.do" commandName="searchCriteria">
<div align="center">
	<table style="width: 80%">
	<tr>
	<td height="40px" align="center" colspan="2"><h1>Search Books</h1></td>
	</tr>
	<tr>
			<td><label>Book Isbn No</label></td> 
			
			<td><form:select path="where1">
				<form:option value="contains">Contains</form:option>
				<form:option value="exact">Exactly Matches</form:option>
			</form:select>
			</td>
			
			<td><form:input path="isbn"/></td>
			
	</tr>		
		<tr align="center"><td colspan="3">AND</td></tr>
		
		<tr>
				<td><label>Book Name</label></td>
			
			<td><form:select path="where2">
				<form:option value="contains">Contains</form:option>
				<form:option value="exact">Exactly Matches</form:option>
			</form:select>
			</td>
			<td><form:input path="bookName"/></td>
		</tr>	
		
		<tr  align="center"><td colspan="3">AND</td></tr>
		
		<tr>
			<td><label>Author Name</label></td>
			<td><form:select path="where3">
				<form:option value="contains">Contains</form:option>
				<form:option value="exact">Exactly Matches</form:option>
			</form:select>
			</td>
			<td><form:input path="authorName"/></td>
		<td>
		<input type="submit" value="Search" name="search"/></td>
</tr>	
	<tr align="center"><td colspan="3"><a href="welcome.do" >Upload Another File</a></td></tr>
	
	</table>
	</div>
	</form:form>
</body>
</html>