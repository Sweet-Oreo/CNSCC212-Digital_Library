<!-- TODO: Frontend design -->
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta content="#2196F3" name="theme-color">
    <title>Digital Library for Computer Science Research</title>
    <link rel="stylesheet" type="text/css" href="css/public.css">
    <link rel="stylesheet" type="text/css" href="css/manage.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">
    <script rel="script" type="text/javascript" src="js/manage.js"></script>
    <script rel="script" type="text/javascript" src="js/public.js"></script>
    <script>
        function deletePaper(id) {
            if (confirm("Are you sure to delete the paper? ")) {
                location.href = "${pageContext.request.contextPath}/servlet/deletePaperServlet?id=" + id;
            }
        }
    </script>
</head>


<body>

<button class="back-to-top" id="back_to_top"><span class="material-icons">arrow_upward</span></button>

<header class="dl4csr-header">
    <ul>
        <li style="float: left;"><a href="${pageContext.request.contextPath}/library/">DL4CSR</a></li>
        <li><a class="material-icons md-24" id="search_btn">search</a></li>
        <li><a id="logout">Logout</a></li>
        <c:if test="${identity == 'university'}">
            <li><a>Manage</a></li>
            <li><a href="${pageContext.request.contextPath}/library/upload.jsp">Upload</a></li>
        </c:if>
    </ul>
</header>

<div id="search_bar" class="dl4csr-search">
    <form action="${pageContext.request.contextPath}/servlet/search" method="get" onsubmit="return checkSearch()">
        <label for="search_input" class="title">DL4CSR</label>
        <button type="submit" class="material-icons md-24">search</button>
        <input type="text" id="search_input" name="w" placeholder="Search anything in the library">
        <button type="button" id="search_close" class="material-icons md-24">close</button>
    </form>
</div>

<main class="dl4csr-main">
    <div class="dl4csr-main--paper">
        <h1 class="dl4csr-main--paper title">All Papers</h1>
        <c:forEach items="${myPapers}" var="paper" varStatus="s">
            <div class="dl4csr-main--paper item">
                <h2>${paper.title}</h2>
                <div class="dl4csr-main--paper details">
                    <p class="dl4csr-main--paper key">Author</p>
                    <p class="dl4csr-main--paper value">${paper.author}</p>
                    <p class="dl4csr-main--paper key">Major</p>
                    <p class="dl4csr-main--paper value">${paper.major}</p>
                    <p class="dl4csr-main--paper key">Keywords</p>
                    <p class="dl4csr-main--paper value">${paper.keyword}</p>
                    <p class="dl4csr-main--paper key">Outline</p>
                    <p class="dl4csr-main--paper value">${paper.outline}</p>
                    <p class="dl4csr-main--paper key">Submit date</p>
                    <p class="dl4csr-main--paper value">${paper.submit_date}</p>
                    <p class="dl4csr-main--paper key">Publish date</p>
                    <p class="dl4csr-main--paper value">
                        <c:if test="${paper.publish_date == ''}">-</c:if>
                        <c:if test="${paper.publish_date != ''}">${paper.publish_date}</c:if>
                    </p>
                    <p><a class="btn btn-default btn-sm" href="javascript:deletePaper(${paper.id});">Delete</a></p>
                    <p><a href="${pageContext.request.contextPath}/servlet/downloadServlet?filename=${paper.id}.pdf">Download</a></p>
                </div>
            </div>
        </c:forEach>
    </div>
</main>

</body>

</html>
