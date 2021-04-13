<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="#FFFFFF" name="theme-color">
    <title>Digital Library for Computer Science Research</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">
    <script rel="script" type="text/javascript" src="js/login.js"></script>
    <script rel="script" type="text/javascript" src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.js"></script>
</head>


<body>

<main class="dl4csr-main">
    <div class="dl4csr-main--content">
        <div class="dl4csr-main--content--left">
            <h1>Digital Library for Computer Science Research</h1>
        </div>
        <div class="dl4csr-main--content--right">
            <h1 id="title">Sign in</h1>
            <div class="dl4csr-main--selector">
                <button id="university_selector" style="border-bottom-color: #212121;">As University</button>
                <button id="reviewer_selector">As Reviewer</button>
                <button id="reader_selector">As Reader</button>
            </div>
            <form action="${pageContext.request.contextPath}/servlet/loginServlet" method="post" onsubmit="return checkSubmit();">
                <input type="hidden" id="action" name="action" value="sign_in">
                <input type="hidden" id="identity" name="identity" value="university">
                <p>
                    <label for="email"></label>
                    <input type="email" id="email" name="email" placeholder="Enter the email">
                </p>
                <p id="input_name" style="display: none;">
                    <label for="name"></label>
                    <input type="text" id="name" name="name" placeholder="Enter the user name">
                </p>
                <p id="input_major" style="display: none;">
                    <label for="major"></label>
                    <input type="text" list="selectable_majors" id="major" name="major" placeholder="Select a major">
                    <datalist id="selectable_majors" style="height: 200px">
                        <option value="Artificial Intelligence"></option>
                        <option value="Computer Algorithms and Theories"></option>
                        <option value="Biosystems and Computational Biology"></option>
                        <option value="Big Data"></option>
                        <option value="Computer Architecture"></option>
                        <option value="Computer Graphics and Visualization"></option>
                        <option value="Database System"></option>
                        <option value="Distributed Systems"></option>
                        <option value="High-Performance Computing"></option>
                        <option value="Human-Computer Interaction"></option>
                        <option value="Networking and Systems"></option>
                        <option value="Programming Language"></option>
                        <option value="Scientific Computing and Numerical Analysis"></option>
                        <option value="Security and Cryptography"></option>
                        <option value="Software Engineering"></option>
                    </datalist>
                </p>
                <p>
                    <label for="raw_password"></label>
                    <input type="password" id="raw_password" placeholder="Enter the password">
                    <input type="hidden" id="password" name="password">
                    <span class="material-icons vis" id="show_password">visibility</span>
                </p>
                <p>
                    <button type="submit" id="submit">SIGN IN</button>
                </p>
            </form>
            <p style="text-align: center; padding-top: 12px;">
                <a id="switch_sign">Don't have account? Click to sign up.</a>
            </p>
        </div>
    </div>
    <div class="dl4csr-main--copyright">
        <p>Copyright &copy; 2021 CNSCC.212 Group 5. All rights reserved.</p>
    </div>
</main>

<div class="dl4csr-snackbar" id="snackbar">
    <p id="snackbar_info">Unset</p>
    <div style="text-align: right"><a id="snackbar_close">Okay</a></div>
</div>

</body>

</html>
