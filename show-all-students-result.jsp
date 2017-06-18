<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="les11.logic.dto.Student" %>
<%@ page import="java.util.*"%>

<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View all students result</title>
</head>
<body>

<B>Here go all students from the DB</B>

<%List<Object> lst = (List<Object>) request.getAttribute("list");
Iterator<Object> iterator = lst.iterator();
%>

<table border=1>

<%while (iterator.hasNext()) {%>
    <tr>
       <% Object element = iterator.next();%>
        <td> <%=element%> </td>
    </tr>
    <%}%>
</table>
<a href="index.jsp">Go to Home Page</a>

</body>
</html>