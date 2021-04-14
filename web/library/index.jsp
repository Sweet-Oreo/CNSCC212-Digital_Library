<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Digital Library for Computer Science Research</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">
    <script rel="script" type="text/javascript" src="js/index.js"></script>
</head>


<body>

<header class="dl4csr-header">
    <ul>
        <li style="float: left;"><a>DL4CSR</a></li>
        <li><a href="${pageContext.request.contextPath}/servlet/logoutServlet">Logout</a></li>
        <li><a href="${pageContext.request.contextPath}/library/about.jsp">About</a></li>
        <li><a href="${pageContext.request.contextPath}/library/help.jsp">Help</a></li>
    </ul>
</header>

<main class="dl4csr-main">
    <div class="dl4csr-main--search">
        <h1>Search papers</h1>
        <form action="${pageContext.request.contextPath}/library/search.jsp" method="get" onsubmit="return false;">
            <div class="dl4csr-main--search--option">
                <label for="title">Title</label>
                <input type="text" id="title" name="title" placeholder="Enter the title">
            </div>
            <div>
                <label for="author">Author</label>
                <input type="text" id="author" name="author" placeholder="Enter the author">
            </div>
            <div>
                <label for="keyword">Keyword</label>
                <input type="text" id="keyword" name="keyword" placeholder="Enter the keyword">
            </div>
            <button type="submit" class="material-icons">search</button>
        </form>




        <form id="form" action="${pageContext.request.contextPath}/downloadServlet", method="post">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th>No.</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>keywords</th>
                    <th>Operation</th>
                </tr>

                <c:forEach items="${papers}" var="paper" varStatus="s">
                    <tr>
                        <td>${s.count}</td>
                        <td>${paper.title}</td>
                        <td>${paper.author}</td>
                        <td>${paper.keyword}</td>
                        <td><a href="">Download</a> </td>
                    </tr>
                </c:forEach>


            </table>

        </form>






    </div>
    <div class="dl4csr-main--latest">
    </div>
</main>

<footer class="dl4csr-footer">
</footer>

</body>

</html>