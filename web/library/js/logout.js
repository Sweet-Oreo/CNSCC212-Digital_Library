window.onload = () => {

    let logoutBtn = document.getElementById("logout")

    logoutBtn.onclick = () => {
        let xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP")
        xmlHttp.withCredentials = true
        xmlHttp.open("POST", "/servlet/AjaxSignOut", true)
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
        xmlHttp.send()
        xmlHttp.onreadystatechange = () => {
            if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
                location.reload()
            }
        }
    }

}
