let timeoutContent

window.onload = () => {

    listenBackToTop(document.getElementById("back_to_top"))
    listenSearch(document.getElementById("search_bar"), document.getElementById("search_btn"), document.getElementById("search_close"))
    listenLogout(document.getElementById("logout"))

    let snackbar = document.getElementById("snackbar")
    let snackbarClose = document.getElementById("snackbar_close")
    snackbar.style.bottom = -snackbar.offsetHeight + "px"
    snackbarClose.onclick = () => {
        clearTimeout(timeoutContent)
        snackbar.style.bottom = -snackbar.offsetHeight + "px"
    }

    let xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP")
    xmlHttp.onreadystatechange = () => {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            document.getElementById("selectable_majors").innerHTML = xmlHttp.responseText
            majorListNotLoaded = false
        }
    }
    xmlHttp.open("POST", "/servlet/AjaxGetMajors", true)
    xmlHttp.send()

    let title = document.getElementById("title")
    title.onclick = () => resetStyle(title)

    let author = document.getElementById("author")
    author.onclick = () => resetStyle(author)

    let keyword = document.getElementById("keyword")
    keyword.onclick = () => resetStyle(keyword)

    let major = document.getElementById("major")
    major.onclick = () => resetStyle(major)

    let outline = document.getElementById("outline")
    outline.onclick = () => resetStyle(outline)

    let fileBtn = document.getElementById("file_btn")
    fileBtn.onclick = () => resetStyle(fileBtn)

}


function checkUpload() {

    let form = document.getElementById("form")
    let title = document.getElementById("title")
    let author = document.getElementById("author")
    let keyword = document.getElementById("keyword")
    let major = document.getElementById("major")
    let outline = document.getElementById("outline")
    let file = document.getElementById("file")
    let fileBtn = document.getElementById("file_btn")

    if (checkTitle(title) && checkAuthor(author) && checkKeyword(keyword) && checkMajor(major) && checkOutline(outline) && checkFile(file, fileBtn)) {
        let xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP")
        let formData = new FormData(form)
        xmlHttp.open("POST", "/servlet/AjaxUploadPaperServlet", false)
        xmlHttp.send(formData)
        while (xmlHttp.readyState !== 4) {
        }
        if (xmlHttp.status === 200) {
            switch (xmlHttp.responseText) {
                case "succeed": {
                    timeoutContent = snackbarAlert("Paper uploaded successfully", timeoutContent)
                    form.reset()
                    break
                }
                case "reviewerError": {
                    timeoutContent = snackbarAlert("Cannot find available reviewer, please upload later", timeoutContent)
                    break
                }
                case "majorError": {
                    timeoutContent = snackbarAlert("Major " + major.value + " is not available", timeoutContent)
                    major.style.color = "red"
                    major.style.borderBottomColor = "red"
                    break
                }
                case "uploadError": {
                    timeoutContent = snackbarAlert("An error occurred when uploading file", timeoutContent)
                    break
                }
                default: {
                    timeoutContent = snackbarAlert("An unexpected error occurred", timeoutContent)
                    break
                }
            }
        } else {
            timeoutContent = snackbarAlert("Error on connecting to server", timeoutContent)
        }
    }

}


function checkTitle(title) {
    if (title.value === "") {
        timeoutContent = snackbarAlert("Please enter the title", timeoutContent)
        title.style.color = "red"
        title.style.borderBottomColor = "red"
        return false
    }
    return true
}


function checkAuthor(author) {
    if (author.value === "") {
        timeoutContent = snackbarAlert("Please enter the author", timeoutContent)
        author.style.color = "red"
        author.style.borderBottomColor = "red"
        return false
    }
    return true
}


function checkKeyword(keyword) {
    if (keyword.value === "") {
        timeoutContent = snackbarAlert("Please enter the keyword", timeoutContent)
        keyword.style.color = "red"
        keyword.style.borderBottomColor = "red"
        return false
    }
    return true
}


function checkMajor(major) {
    if (major.value === "") {
        timeoutContent = snackbarAlert("Please enter the major", timeoutContent)
        major.style.color = "red"
        major.style.borderBottomColor = "red"
        return false
    }
    return true
}


function checkOutline(outline) {
    if (outline.value === "") {
        timeoutContent = snackbarAlert("Please enter the outline", timeoutContent)
        outline.style.color = "red"
        outline.style.borderColor = "red"
        return false
    }
    return true
}


function checkFile(file, fileBtn) {
    if (file.value === "") {
        timeoutContent = snackbarAlert("Please select a pdf file", timeoutContent)
        fileBtn.style.backgroundColor = "red"
        return false
    }
    return true
}


