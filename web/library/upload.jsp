<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="#FFFFFF" name="theme-color">
    <title>Digital Library for Computer Science Research</title>
    <link rel="stylesheet" type="text/css" href="css/public.css">
    <link rel="stylesheet" type="text/css" href="css/upload.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">
    <script rel="script" type="text/javascript" src="js/upload.js"></script>
</head>


<body>

<header class="dl4csr-header">
    <ul>
        <li style="float: left;"><a href="${pageContext.request.contextPath}/library/">DL4CSR</a></li>
        <li><a class="material-icons md-24">search</a></li>
        <li><a id="logout">Logout</a></li>
        <c:if test="${identity == 'university'}">
            <li><a href="${pageContext.request.contextPath}/servlet/findMyPapersServlet">Manage</a></li>
            <li><a href="${pageContext.request.contextPath}/library/upload.jsp">Upload</a></li>
        </c:if>
    </ul>
</header>

<main class="dl4csr-main">
    <div>
        <form onsubmit="return false;">
            <input type="hidden" id="email" value="${pageContext.request.session.getAttribute("email")}">
            <p>
                <label for="title">Title</label>
                <input type="text" id="title" placeholder="Enter the title">
            </p>
            <p>
                <label for="author">Author</label>
                <input type="text" id="author" placeholder="Enter the author">
            </p>
            <p>
                <label for="outline">Outline</label>
            </p>
            <p>
                <textarea id="outline" placeholder="Enter the outline"></textarea>
            </p>
            <p>
                <label for="keyword">Keyword</label>
                <input type="text" id="keyword" placeholder="Enter the keyword">
            </p>
            <p>
                <label for="major">Major</label>
                <input type="text" list="selectable_majors" id="major" placeholder="Select a major">
                <datalist id="selectable_majors" style="height: 200px">
                </datalist>
            </p>
            <p>
                <label for="file">File</label>
                <input type="file" accept="application/pdf" id="file" placeholder="Select a pdf file">
            </p>
            <p>
                <button type="submit" id="add-paper">ADD PAPER</button>
            </p>
        </form>
    </div>
</main>

</body>

</html>
