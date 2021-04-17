<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Digital Library for Computer Science Research</title>
    <link rel="stylesheet" type="text/css" href="css/public.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">
    <script rel="script" type="text/javascript" src="js/index.js"></script>
    <script rel="script" type="text/javascript" src="js/public.js"></script>
</head>


<body>

<!-- TODO: A back-to-top button -->

<header class="dl4csr-header">
    <ul>
        <li style="float: left;"><a>DL4CSR</a></li>
        <li><a class="material-icons md-24" id="search_btn">search</a></li>
        <li><a id="logout">Logout</a></li>
        <c:if test="${identity == 'university'}">
            <li><a href="${pageContext.request.contextPath}/servlet/findMyPapersServlet">Manage</a></li>
            <li><a href="${pageContext.request.contextPath}/library/upload.jsp">Upload</a></li>
        </c:if>
    </ul>
</header>

<!-- TODO: Add selectable constraints for searching -->
<div id="search_bar" class="dl4csr-search">
    <form action="${pageContext.request.contextPath}/servlet/search" method="get" onsubmit="return checkSearch()">
        <label for="search_input" class="title">DL4CSR</label>
        <button type="submit" class="material-icons md-24">search</button>
        <input type="text" id="search_input" placeholder="Search anything in the library">
        <button type="button" id="search_close" class="material-icons md-24">close</button>
    </form>
</div>

<!-- TODO: Display more details of the paper and design the button of download -->
<main class="dl4csr-main">
    <div class="dl4csr-main--paper">
        <h1 class="dl4csr-main--paper title">All Published Papers</h1>
        <c:forEach items="${pb.list}" var="paper" varStatus="s">
            <div class="dl4csr-main--paper item" id="item_${s.count}">
                <h2 style="border-bottom: 1px solid #E0E0E0">${paper.title}</h2>
                <p>Written by <i>${paper.author}</i> (${paper.university})</p>
                <p>Keywords: <i>${paper.keyword}</i></p>
                <p>Major: <i>${paper.major}</i></p>
                <!--p>Published on ${paper.publish_date}</p-->
                <p><a href="${pageContext.request.contextPath}/servlet/downloadServlet">Download</a></p>
            </div>
        </c:forEach>
    </div>
</main>

<!-- TODO: Stylesheet of this part is undone -->
<nav class="dl4csr-nav">
    <ul>
        <li>
            <a href="${pageContext.request.contextPath}/servlet/findPaperByPageServlet?currentPage=${pb.currentPage - 1}&rows=5">
                <span aria-hidden="true" class="material-icons">chevron_left</span>
            </a>
        </li>
        <c:forEach begin="1" end="${pb.totalPage}" var="i">
            <c:if test="${pb.currentPage == i}">
                <li class="active"><a
                    href="${pageContext.request.contextPath}/servlet/findPaperByPageServlet?currentPage=${i}&rows=5">${i}</a>
                </li>
            </c:if>
            <c:if test="${pb.currentPage != i}">
                <li><a
                    href="${pageContext.request.contextPath}/servlet/findPaperByPageServlet?currentPage=${i}&rows=5">${i}</a>
                </li>
            </c:if>
        </c:forEach>
        <li>
            <a href="${pageContext.request.contextPath}/servlet/findPaperByPageServlet?currentPage=${pb.currentPage + 1}&rows=5">
                <span aria-hidden="true" class="material-icons">chevron_right</span>
            </a>
        </li>
        <br>
        <span>Total ${pb.totalCount} papers, ${pb.totalPage} pages</span>
    </ul>
</nav>

<footer class="dl4csr-footer">
    <p class="dl4csr-footer--title">Digital Library for Computer Science Research</p>
    <p class="dl4csr-footer--content">Copyright &copy; 2021 CNSCC.212 Group 5. All rights reserved.</p>
</footer>

</body>

</html>