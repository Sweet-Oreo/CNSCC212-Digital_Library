<!-- TODO: Improve the upload page -->
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
    <script rel="script" type="text/javascript" src="js/public/onload.js"></script>
    <script rel="script" type="text/javascript" src="js/public/function.js"></script>
</head>


<body>

<button class="back-to-top" id="back_to_top"><span class="material-icons">arrow_upward</span></button>

<header class="dl4csr-header" id="header">
    <ul>
        <li style="float: left;"><a href="${pageContext.request.contextPath}/servlet/findPaperByPageServlet">DL4CSR</a></li>
        <li><a class="material-icons md-24" id="search_btn">search</a></li>
        <li><a id="logout">Logout</a></li>
        <c:if test="${identity == 'university'}">
            <li><a href="${pageContext.request.contextPath}/servlet/findMyPapersServlet">Manage</a></li>
            <li><a>Upload</a></li>
        </c:if>
    </ul>
</header>

<div id="search_bar" class="dl4csr-search">
    <form action="${pageContext.request.contextPath}/servlet/findPaperByPageServlet" method="get" onsubmit="return checkSearch()">
        <label for="search_input" class="title">DL4CSR</label>
        <button type="submit" class="material-icons md-24">search</button>
        <input type="text" id="search_input" name="w" placeholder="Search anything in the library">
        <button type="button" id="search_close" class="material-icons md-24">close</button>
    </form>
</div>

<main class="dl4csr-main" id="main">
    <div class="dl4csr-main--upload">
        <h1 class="dl4csr-main--upload title">Upload Papers</h1>
        <form onsubmit="return false;" method="post" id="form" enctype="multipart/form-data">
            <div class="dl4csr-main--form">
                <label for="title">Title</label>
                <input type="text" name="title" id="title" placeholder="Enter the title">
                <label for="author">Author</label>
                <input type="text" name="author" id="author" placeholder="Enter the author">
                <label for="keyword">Keyword</label>
                <input type="text" name="keyword" id="keyword" placeholder="Enter the keyword">
                <label for="major">Major</label>
                <input type="text" name="major" list="selectable_majors" id="major" placeholder="Select a major">
                <label for="outline">Outline</label>
                <textarea id="outline" name="outline" placeholder="Enter the outline"></textarea>
            </div>
            <input type="hidden" name="email" id="email" value="${email}">
            <datalist id="selectable_majors" style="height: 200px"></datalist>
            <p>
                <a id="file_btn" class="dl4csr-button">
                    <input type="file" name="file" accept="application/pdf" id="file">SELECT FILE
                </a>
                <button onclick="checkUpload()" class="dl4csr-button" type="submit">ADD PAPER</button>
            </p>
        </form>
    </div>
</main>

<footer class="dl4csr-footer" id="footer">
    <p class="dl4csr-footer--title">Digital Library for Computer Science Research</p>
    <p class="dl4csr-footer--content">Copyright &copy; 2021 CNSCC.212 Group 5. All rights reserved.</p>
</footer>

<div class="dl4csr-snackbar" id="snackbar">
    <p id="snackbar_info">Unset</p>
    <div style="text-align: right"><a id="snackbar_close">Okay</a></div>
</div>

</body>

</html>
