<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
</head>
<body>
	
<div align="center">
	<table style="width: 80%">
	<tr>
	<td height="40px" align="center" colspan="2"><h1>Upload XML </h1></td>
	</tr>
	<tr>
	<form:form action="uploadXml.do" commandName="file" method="POST" enctype="multipart/form-data">
	<td>
		<span style="color: green;">${succesMesg}</span>
		<span style="color: red;">${errorMesg}</span>
		<br><input type="file" name="xmlFile" id="fileId" /></td>
		<td><input type="submit" name="upload" value="Upload XML"/></td>
	</form:form>
	</tr>
	<tr>
	<td height="40px" colspan="2"><a href="search.do" >Search book details</a></td>
	</tr>
	
	</table>
	</div>
</body>
</html>