// This JavaScript is only for user logout using AJAX
// DO NOT ADD ANY OTHER METHODS HERE

// If a page needs to be able to logout, since the window.onload will be executed only once:
// !!! Must add the following logout() method to window.onload in other scripts !!!
// !!! Must add the following logout() method to window.onload in other scripts !!!
// !!! Must add the following logout() method to window.onload in other scripts !!!

function logout() {

    // When the logout button is clicked
    document.getElementById("logout").onclick = () => {
        // Create XMLHTTP object according to the browser
        let xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP")
        // Only when the withCredentials argument is true, the server can get the session
        xmlHttp.withCredentials = true
        // Actually, using GET method is also available
        xmlHttp.open("POST", "/servlet/AjaxSignOut", true)
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
        xmlHttp.send()
        // Listen the change of readyStatus
        xmlHttp.onreadystatechange = () => {
            // Once received HTTP 200 code and ready status number 4, reload the page
            if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
                // So far, the session of the client has been reset
                // Thus, the reload request will be redirected to the index page by the server
                location.reload()
                // Or use JavaScript's widow.location.href to redirect to the index page
                // window.location.href = "/library/"
            }
        }
    }

}
