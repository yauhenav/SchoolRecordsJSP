<%@ page import="les11.logic.dto.Student" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View add new student result</title>
</head>
<body>

<B>Here goes one new student that has just been added to the DB</B>

<%Student student = (Student) request.getAttribute("studentObj0");%>
<%String message = (String) request.getAttribute("message");%>

<table border=1>
    <tr>
        <td> <%=message%> </td>
    </tr>

    <tr>
        <td> <%=student%> </td>
    </tr>

</table>
<a href="index.jsp">Go to Home Page</a>
</body>
</html>