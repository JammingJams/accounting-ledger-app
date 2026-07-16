async function getDeposits() {
    const token = localStorage.getItem("jwt");

    const response = await fetch("http://localhost:8080/deposits", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
    });

    const data = await response.json();

    console.log(data);

    return data;
}

async function postDeposit() {
    alert("Post Deposit function called");

    const depositName = document.getElementById("depositName").value;
    const vendorName = document.getElementById("vendorName").value;
    const depositDate = document.getElementById("depositDate").value;
    const depositTime = document.getElementById("depositTime").value;
    const depositAmount = document.getElementById("depositAmount").value;
    const token = localStorage.getItem("jwt");

    const response = await fetch("http://localhost:8080/deposits", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({depositName: depositName, vendorName: vendorName, depositDate: depositDate, 
            depositTime: depositTime, depositAmount: depositAmount})
    });

    const data = await response.json();

    console.log(data);

    hideDepositForm();

    loadDashboard();

    return data;
}
