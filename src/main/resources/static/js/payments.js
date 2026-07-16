async function getPayments() {
    const token = localStorage.getItem("jwt");

    const response = await fetch("http://localhost:8080/payments", {
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

async function postPayment() {
    alert("Post Payment function called");

    const paymentName = document.getElementById("paymentName").value;
    const vendorName = document.getElementById("vendorPaymentName").value;
    const paymentDate = document.getElementById("paymentDate").value;
    const paymentTime = document.getElementById("paymentTime").value;
    const paymentAmount = document.getElementById("paymentAmount").value;
    const token = localStorage.getItem("jwt");

    const response = await fetch("http://localhost:8080/payments", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({paymentName: paymentName, vendorName: vendorName, paymentDate: paymentDate, 
            paymentTime: paymentTime, paymentAmount: paymentAmount})
    });

    const data = await response.json();

    console.log(data);

    loadDashboard();

    hidePaymentForm();

    return data;
}