async function register() {
    alert("Register function called");

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
        alert("Passwords do not match");
        return;
    }

    const response = await fetch("http://localhost:8080/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({username: username, password: password, confirmPassword: confirmPassword, role: "USER"})
    });

    const data = await response.json();

    localStorage.setItem("jwt", data.token);

    console.log(data);

    fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({username: username, password: password})
    }).then(response => {
        if (response.ok) {
            window.location.href = "http://localhost:8080/login.html";
        } else {
            alert("Login failed after registration");
        }
    }).catch(error => {
        console.error("Error during login after registration:", error);
        alert("An error occurred during login after registration");
    })

}