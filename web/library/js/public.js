// This function should be added into window.onload of the page
function listenLogout(logoutBtn) {
    logoutBtn.onclick = () => {
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

// This function should be added into window.onload of the page
function listenSearch(searchBar, searchBtn, closeBtn) {
    searchBar.style.top = -searchBar.offsetHeight + "px"
    searchBtn.onclick = () => {
        searchBar.style.top = "0"
        closeBtn.style.transform = "rotate(180deg)"
    }
    closeBtn.onclick = () => {
        searchBar.style.top = -searchBar.offsetHeight + "px"
        closeBtn.style.transform = "rotate(0)"
    }
}

// This function should be added into window.onload of the page
function listenBackToTop(backToTopBtn) {
    backToTopBtn.style.bottom = -backToTopBtn.offsetHeight + "px"
    window.onscroll = () => {
        if (document.body.onscroll > 500 || document.documentElement.scrollTop > 500) {
            backToTopBtn.style.bottom = "3%"
        } else {
            backToTopBtn.style.bottom = -backToTopBtn.offsetHeight + "px"
        }
    }
    backToTopBtn.onclick = () => {
        window.scrollTo({top: 0, behavior: "smooth"})
    }
}

// This function can be called directly
function checkSearch() {
    let input = document.getElementById("search_input")
    return input.value !== ""
}
