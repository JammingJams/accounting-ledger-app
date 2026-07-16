function logout() {
    localStorage.removeItem("jwt");
    window.location.href = "/index.html";
}

function getToken() {
    return localStorage.getItem("jwt");
}

function isLoggedIn() {
    return getToken() !== null;
}