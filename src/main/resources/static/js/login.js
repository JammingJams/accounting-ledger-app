async function login() {
    alert("Login function called");

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({username: username, password: password})
    });

    const data = await response.json();

    localStorage.setItem("jwt", data.token);

    console.log(data);

    if (response.ok) {
        window.location.href = "http://localhost:8080/dashboard.html";
    } else {
        alert("Login failed");
    }
}