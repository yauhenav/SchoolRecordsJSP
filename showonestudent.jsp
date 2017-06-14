<%@ page import="les11.logic.dto.Student" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Show one student form</title>
</head>
<body>

<B>Here goes one student from selected key</B>

<%Student student = (Student) request.getAttribute("studentObj");%>

<table border=1>
<tr>
    <td> <%=student.getId()%> </td>
</tr>
</table>

</body>
</html>