<!-- TODO: Improve the review page  -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<form href="${pageContext.request.contextPath}/servlet/reviewServlet">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">

            <th></th>
            <th>Title</th>
            <th>Author</th>
            <th>University</th>
            <th>Keywords</th>
            <th>Abstract</th>
            <th>Submit date</th>
            <th>Operation</th>
        </tr>

        <c:forEach items="${reviewPapers}" var="reviewPaper" varStatus="s">
            <tr>
                <td><input type="checkbox"  name="uid" value="${user.id}"> </td>
                <td>${reviewPaper.title}</td>
                <td>${reviewPaper.author}</td>
                <td>${reviewPaper.university}</td>
                <td>${reviewPaper.keyword}</td>
                <td>${reviewPaper.outline}</td>
                <td>${reviewPaper.submit_date}</td>

                <td><a>Submit</a>&nbsp;
                    <a>Download</a>
            </tr>

        </c:forEach>
    </table>
</form>

</body>
</html>
