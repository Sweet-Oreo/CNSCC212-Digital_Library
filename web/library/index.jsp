<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Digital Library for Computer Science Research</title>
    <link rel="stylesheet" type="text/css" href="css/index.css">

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">
    <script rel="script" type="text/javascript" src="js/index.js"></script>
    <script rel="script" type="text/javascript" src="js/logout.js"></script>
</head>


<body>

<header class="dl4csr-header">
    <ul>
        <li style="float: left;"><a>DL4CSR</a></li>
        <li><a id="logout">Logout</a></li>
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

        <!-- Display papers-->
        <form id="form" action="${pageContext.request.contextPath}/servlet/downloadServlet" method="post">
            <table border="1" class="table table-bordered table-hover">
                <tr class="success">
                    <th>No.</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>keywords</th>
                    <th>Operation</th>
                </tr>
                <c:forEach items="${pb.list}" var="paper" varStatus="s">
                    <tr>
                        <td>${s.count}</td>
                        <td>${paper.title}</td>
                        <td>${paper.author}</td>
                        <td>${paper.keyword}</td>
                        <td><a href="">Download</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form>


        <div>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li>
                        <a href="${pageContext.request.contextPath}/servlet/findPaperByPageServlet?currentPage=${pb.currentPage - 1}&rows=5" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach begin="1" end="${pb.totalPage}" var="i">
                        <c:if test="${pb.currentPage == i}">
                            <li class="active"><a href="${pageContext.request.contextPath}/servlet/findPaperByPageServlet?currentPage=${i}&rows=5">${i}</a></li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li><a href="${pageContext.request.contextPath}/servlet/findPaperByPageServlet?currentPage=${i}&rows=5">${i}</a></li>
                        </c:if>
                    </c:forEach>
                    <li>
                        <a href="${pageContext.request.contextPath}/servlet/findPaperByPageServlet?currentPage=${pb.currentPage + 1}&rows=5" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style="font-size: 25px;margin-left: 5px;">
                        Total  ${pb.totalCount}  papers, ${pb.totalPage}  pages

                    </span>
                </ul>
            </nav>
        </div>

    <div>
        <c:if test="${identity == 'university'}">
            <a href="${pageContext.request.contextPath}/servlet/findMyPapersServlet">My Papers</a>
        </c:if>
    </div>



    </div>
</main>

<footer class="dl4csr-footer">
</footer>

</body>

</html>