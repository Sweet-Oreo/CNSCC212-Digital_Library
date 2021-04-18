<!-- TODO: Make this page better -->
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="#2196F3" name="theme-color">
    <title>Digital Library for Computer Science Research</title>
    <link rel="stylesheet" type="text/css" href="css/public.css">
    <link rel="stylesheet" type="text/css" href="css/upload.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">
    <script rel="script" type="text/javascript" src="js/upload.js"></script>
    <script rel="script" type="text/javascript" src="js/public.js"></script>
</head>


<body>

<button class="back-to-top" id="back_to_top"><span class="material-icons">arrow_upward</span></button>

<header class="dl4csr-header">
    <ul>
        <li style="float: left;"><a href="${pageContext.request.contextPath}/library/">DL4CSR</a></li>
        <li><a class="material-icons md-24" id="search_btn">search</a></li>
        <li><a id="logout">Logout</a></li>
        <c:if test="${identity == 'university'}">
            <li><a href="${pageContext.request.contextPath}/servlet/findMyPapersServlet">Manage</a></li>
            <li><a>Upload</a></li>
        </c:if>
    </ul>
</header>

<div id="search_bar" class="dl4csr-search">
    <form action="${pageContext.request.contextPath}/servlet/search" method="get" onsubmit="return checkSearch()">
        <label for="search_input" class="title">DL4CSR</label>
        <button type="submit" class="material-icons md-24">search</button>
        <input type="text" id="search_input" placeholder="Search anything in the library">
        <button type="button" id="search_close" class="material-icons md-24">close</button>
    </form>
</div>

<main class="dl4csr-main">
    <div class="dl4csr-main--upload">
        <h1 class="dl4csr-main--upload title">Upload Papers</h1>
        <form onsubmit="return checkUpload();">
            <div class="dl4csr-main--form">
                <label for="title">Title</label>
                <input type="text" id="title" placeholder="Enter the title">
                <label for="author">Author</label>
                <input type="text" id="author" placeholder="Enter the author">
                <label for="keyword">Keyword</label>
                <input type="text" id="keyword" placeholder="Enter the keyword">
                <label for="major">Major</label>
                <input type="text" list="selectable_majors" id="major" placeholder="Select a major">
                <label for="outline">Outline</label>
                <textarea id="outline" placeholder="Enter the outline"></textarea>
            </div>
            <datalist id="selectable_majors" style="height: 200px"></datalist>
            <p>
                <a class="dl4csr-button" href="javascript:">
                    <input type="file" accept="application/pdf" id="file">SELECT FILE
                </a>
                <button class="dl4csr-button" type="submit" id="add-paper">ADD PAPER</button>
            </p>
        </form>
    </div>
</main>

</body>

</html>
