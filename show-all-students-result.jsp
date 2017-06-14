<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="les11.logic.dto.Student" %>
<%@page import="java.util.*"%>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View all students result</title>
</head>
<body>

<B>Here go all students from the DB</B>

<%List<Student> lst = (List<Student>) request.getAttribute("studentsList");
Iterator<Student> iterator = lst.iterator();
%>

<table border=1>

<%while (iterator.hasNext()) {%>
    <tr>
       <% Student element = iterator.next();%>
        <td> <%=element%> </td>
    </tr>
    <%}%>
</table>
<a href="index.jsp">Go to Home Page</a>

</body>
</html>