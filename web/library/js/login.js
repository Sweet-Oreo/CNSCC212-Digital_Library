let majorListNotLoaded = true
let timeoutContent

window.onload = () => {

    let uniSelector = document.getElementById("university_selector")
    let revSelector = document.getElementById("reviewer_selector")
    let reaSelector = document.getElementById("reader_selector")
    let identity = document.getElementById("identity")
    let action = document.getElementById("action")
    let switchSign = document.getElementById("switch_sign")
    let submit = document.getElementById("submit")
    let title = document.getElementById("title")
    let inputName = document.getElementById("input_name")
    let inputMajor = document.getElementById("input_major")
    let showPassword = document.getElementById("show_password")
    let rawPassword = document.getElementById("raw_password")
    let snackbar = document.getElementById("snackbar")
    let snackbarClose = document.getElementById("snackbar_close")
    let email = document.getElementById("email")
    let major = document.getElementById("major")
    let name = document.getElementById("name")
    let majorList = document.getElementById("selectable_majors")

    snackbar.style.bottom = -snackbar.offsetHeight + "px"

    snackbarClose.onclick = () => {
        clearTimeout(timeoutContent)
        snackbar.style.bottom = -snackbar.offsetHeight + "px"
    }

    uniSelector.onclick = () => {
        identity.value = "university"
        uniSelector.style.borderBottomColor = "#1E88E5"
        revSelector.style.borderBottomColor = reaSelector.style.borderBottomColor = "white"
        inputMajor.style.display = "none"
    }

    revSelector.onclick = () => {
        identity.value = "reviewer"
        revSelector.style.borderBottomColor = "#1E88E5"
        uniSelector.style.borderBottomColor = reaSelector.style.borderBottomColor = "white"
        if (action.value === "sign_up") {
            inputMajor.style.display = "inherit"
            if (majorListNotLoaded) {
                getMajorList(majorList)
            }
        }
    }

    reaSelector.onclick = () => {
        identity.value = "reader"
        reaSelector.style.borderBottomColor = "#1E88E5"
        uniSelector.style.borderBottomColor = revSelector.style.borderBottomColor = "white"
        inputMajor.style.display = "none"
    }

    switchSign.onclick = () => {
        if (action.value === "sign_in") {
            switchSign.innerHTML = "Already have an account? Click to sign in."
            action.value = "sign_up"
            submit.innerHTML = "SIGN UP"
            title.innerHTML = "Sign up"
            inputName.style.display = "inherit"
            if (identity.value === "reviewer") {
                inputMajor.style.display = "inherit"
                if (majorListNotLoaded) {
                    getMajorList(majorList)
                }
            } else {
                inputMajor.style.display = "none"
            }
        } else {
            switchSign.innerHTML = "Don't have account? Click to sign up."
            action.value = "sign_in"
            submit.innerHTML = "SIGN IN"
            title.innerHTML = "Sign in"
            inputName.style.display = inputMajor.style.display = "none"
        }
    }

    showPassword.onclick = () => {
        if (rawPassword.getAttribute("type") === "password") {
            rawPassword.setAttribute("type", "text")
            showPassword.innerHTML = "visibility"
        } else {
            rawPassword.setAttribute("type", "password")
            showPassword.innerHTML = "visibility_off"
        }
    }

    email.onclick = () => {
        email.style = null
    }

    rawPassword.onclick = () => {
        rawPassword.style = null
    }

    name.onclick = () => {
        name.style = null
    }

    major.onclick = () => {
        major.style = null
    }

}


function getMajorList(majorList) {
    let xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP")
    xmlHttp.onreadystatechange = () => {
        if (xmlHttp.readyState === 4 && xmlHttp.status === 200) {
            majorList.innerHTML = xmlHttp.responseText
            majorListNotLoaded = false
        }
    }
    xmlHttp.open("POST", "/servlet/AjaxGetMajors", true)
    xmlHttp.send()
}


function checkSubmit() {

    let action = document.getElementById("action")
    let identity = document.getElementById("identity")
    let name = document.getElementById("name")
    let rawPassword = document.getElementById("raw_password")
    let email = document.getElementById("email")
    let major = document.getElementById("major")

    if (action.value === "sign_in") {
        if (checkEmail(email) && checkRawPassword(rawPassword)) {
            signIn(identity, email, rawPassword)
        }
    } else {
        if (checkEmail(email) && checkName(name) && checkRawPassword(rawPassword) && checkMajor(identity, major)) {
            signUp(identity, email, name, rawPassword, major)
        }
    }

}


function checkEmail(email) {
    if (email.value === "") {
        timeoutContent = snackbarAlert("Please enter the email", timeoutContent)
        email.style.color = "red"
        email.style.borderBottomColor = "red"
        return false
    }
    return true
}


function checkName(name) {
    if (name.value === "") {
        timeoutContent = snackbarAlert("Please enter the user name", timeoutContent)
        name.style.color = "red"
        name.style.borderBottomColor = "red"
        return false
    }
    return true
}


function checkMajor(identity, major) {
    if (identity.value === "reviewer" && major.value === "") {
        timeoutContent = snackbarAlert("Please select a major", timeoutContent)
        major.style.color = "red"
        major.style.borderBottomColor = "red"
        return false
    }
    return true
}


function checkRawPassword(rawPassword) {
    if (rawPassword.value === "") {
        timeoutContent = snackbarAlert("Please enter the password", timeoutContent)
        rawPassword.style.color = "red"
        rawPassword.style.borderBottomColor = "red"
        return false
    }
    return true
}


function signIn(identity, email, rawPassword) {
    let xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP")
    xmlHttp.withCredentials = true
    xmlHttp.open("POST", "/servlet/AjaxSignIn", true)
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xmlHttp.send("identity=" + identity.value + "&email=" + email.value + "&password=" + md5(rawPassword.value))
    xmlHttp.onreadystatechange = () => {
        if (xmlHttp.readyState === 4) {
            if (xmlHttp.status === 200) {
                switch (xmlHttp.responseText) {
                    case "succeed": {
                        window.location.href = "/servlet/findPaperByPageServlet"
                        break
                    }
                    case "passwordError": {
                        snackbarAlert("Wrong password or user not exist", timeoutContent)
                        rawPassword.style.color = "red"
                        rawPassword.style.borderBottomColor = "red"
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
}


function signUp(identity, email, name, rawPassword, major) {
    let xmlHttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP")
    xmlHttp.open("POST", "/servlet/AjaxSignUp", true)
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xmlHttp.send("identity=" + identity.value + "&email=" + email.value + "&name=" + name.value + "&password=" + md5(rawPassword.value) + "&major=" + major.value)
    xmlHttp.onreadystatechange = () => {
        if (xmlHttp.readyState === 4) {
            if (xmlHttp.status === 200) {
                switch (xmlHttp.responseText) {
                    case "succeed": {
                        snackbarAlert("User has been created successfully", timeoutContent)
                        document.getElementById("switch_sign").click()
                        break
                    }
                    case "emailError": {
                        timeoutContent = snackbarAlert("Email " + email.value + " has been registered", timeoutContent)
                        email.style.color = "red"
                        email.style.borderBottomColor = "red"
                        break
                    }
                    case "majorError": {
                        timeoutContent = snackbarAlert("The major \"" + major.value + "\" is invalid", timeoutContent)
                        major.style.color = "red"
                        major.style.borderBottomColor = "red"
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
}
