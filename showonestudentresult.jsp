<%@ page import="les11.logic.dto.Student" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>View one student result</title>
</head>
<body>

<B>Here goes one student from selected key</B>

<%Student student = (Student) request.getAttribute("studentObj");%>

<table border=1>
<tr>
    <td> <%=student%> </td>
</tr>
</table>
<a href="index.jsp">Go to Home Page</a>
</body>
</html>