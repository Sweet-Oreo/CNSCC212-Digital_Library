<%--
  Created by IntelliJ IDEA.
  User: miracle
  Date: 2021/4/16
  Time: 8:16 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script>
        function deletePaper(id) {
            if (confirm("Are you sure to delete the paper? ")) {
                location.href="${pageContext.request.contextPath}/servlet/deletePaperServlet?id=" + id;
            }
        }
    </script>
</head>
<body>
<div>
    <form id="form" action="${pageContext.request.contextPath}/servlet/findMyPapers" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th>No.</th>
                <th>Title</th>
                <th>Author</th>
                <th>keywords</th>
                <th>Operation</th>
            </tr>
            <c:forEach items="${myPapers}" var="myPaper" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${myPaper.title}</td>
                    <td>${myPaper.author}</td>
                    <td>${myPaper.keyword}</td>
                    <td><a class="btn btn-default btn-sm" href="javascript:deletePaper(${myPaper.id});">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>
