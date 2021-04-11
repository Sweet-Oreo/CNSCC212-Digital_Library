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

    snackbar.style.bottom = -snackbar.offsetHeight + "px"

    snackbarClose.onclick = () => {
        clearTimeout(timeoutContent)
        snackbar.style.bottom = -snackbar.offsetHeight + "px"
    }

    uniSelector.onclick = () => {
        identity.value = "university"
        uniSelector.style.borderBottomColor = "#212121"
        revSelector.style.borderBottomColor = reaSelector.style.borderBottomColor = "white"
        inputMajor.style.display = "none"
    }

    revSelector.onclick = () => {
        identity.value = "reviewer"
        revSelector.style.borderBottomColor = "#212121"
        uniSelector.style.borderBottomColor = reaSelector.style.borderBottomColor = "white"
        if (action.value === "sign_up") {
            inputMajor.style.display = "inherit"
        }
    }

    reaSelector.onclick = () => {
        identity.value = "reader"
        reaSelector.style.borderBottomColor = "#212121"
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
            showPassword.innerHTML = "visibility_off"
        } else {
            rawPassword.setAttribute("type", "password")
            showPassword.innerHTML = "visibility"
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


function checkSubmit() {

    let action = document.getElementById("action")
    let identity = document.getElementById("identity")
    let name = document.getElementById("name")
    let rawPassword = document.getElementById("raw_password")
    let password = document.getElementById("password")
    let email = document.getElementById("email")
    let major = document.getElementById("major")

    if (action.value === "sign_in") {
        if (email.value === "") {
            timeoutContent = snackbarAlert("Please enter the email!", timeoutContent)
            email.style.borderBottomColor = "red"
            return false
        }
        if (rawPassword.value === "") {
            timeoutContent = snackbarAlert("Please enter the password!", timeoutContent)
            rawPassword.style.borderBottomColor = "red"
            return false
        }
        password.value = md5(rawPassword.value)
        return true
    } else {
        if (email.value === "") {
            timeoutContent = snackbarAlert("Please enter the email!", timeoutContent)
            email.style.borderBottomColor = "red"
            return false
        }
        if (name.value === "") {
            timeoutContent = snackbarAlert("Please enter the user name!", timeoutContent)
            name.style.borderBottomColor = "red"
            return false
        }
        if (identity.value === "reviewer") {
            if (major.value === "") {
                timeoutContent = snackbarAlert("Please select a major!", timeoutContent)
                major.style.borderBottomColor = "red"
                return false
            }
        }
        if (rawPassword.value === "") {
            timeoutContent = snackbarAlert("Please enter the password!", timeoutContent)
            rawPassword.style.borderBottomColor = "red"
            return false
        }
        password.value = md5(rawPassword.value)
        return true
    }

}


function snackbarAlert(content, timeoutContent) {
    clearTimeout(timeoutContent)
    let snackbar = document.getElementById("snackbar")
    let snackbarInfo = document.getElementById("snackbar_info")
    snackbarInfo.innerHTML = content
    snackbar.style.bottom = "0"
    return setTimeout(() => {
        snackbar.style.bottom = -snackbar.offsetHeight + "px"
    }, 4000)
}
