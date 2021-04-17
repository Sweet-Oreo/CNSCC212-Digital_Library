window.onload = () => {

    let searchBar = document.getElementById("search_bar")
    let searchBtn = document.getElementById("search_btn")
    let closeBtn = document.getElementById("search_close")

    searchBar.style.top = -searchBar.offsetHeight + "px"

    searchBtn.onclick = () => {
        searchBar.style.top = "0"
        closeBtn.style.transform = "rotate(180deg)"
    }

    closeBtn.onclick = () => {
        searchBar.style.top = -searchBar.offsetHeight + "px"
        closeBtn.style.transform = "rotate(0)"
    }

    document.getElementById("logout").onclick = () => logout()

}


function checkSearch() {
    let input = document.getElementById("search_input")
    return input.value !== ""
}