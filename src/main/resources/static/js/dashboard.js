let allDeposits = [];
let allPayments = [];

async function loadDashboard() {

    allDeposits = await getDeposits();

    displayDeposits(allDeposits);

    allPayments = await getPayments();

    displayPayments(allPayments);
}

function displayDeposits(deposits) {

    const depositsDiv =
        document.getElementById("deposits");

    depositsDiv.innerHTML = "";

    deposits.forEach(deposit => {

        depositsDiv.innerHTML += `
            <div>
                <h3>${deposit.depositName}</h3>
                <p>Vendor: ${deposit.vendorName}</p>
                <p>Date: ${deposit.depositDate}</p>
                <p>Time: ${deposit.depositTime}</p>
                <p>Amount: $${deposit.depositAmount}</p>
            </div>
        `;
    });
}

function displayPayments(payments) {

    const paymentsDiv =
        document.getElementById("payments");

    paymentsDiv.innerHTML = "";

    payments.forEach(payment => {

        paymentsDiv.innerHTML += `
            <div>
                <h3>${payment.paymentName}</h3>
                <p>Vendor: ${payment.vendorName}</p>
                <p>Date: ${payment.paymentDate}</p>
                <p>Time: ${payment.paymentTime}</p>
                <p>Amount: $${payment.paymentAmount}</p>
            </div>
        `;
    })
}

function getFilters() {

    return {
        minAmount: document.getElementById("minAmount").value,
        maxAmount: document.getElementById("maxAmount").value,
        startDate: document.getElementById("startDate").value,
        endDate: document.getElementById("endDate").value,
        vendor: document.getElementById("vendorFilter").value,
        transactionName: document.getElementById("transactionName").value,
        transactionType: document.getElementById("transactionType").value
    };
}

function filterDeposits(filters) {

    return allDeposits.filter(deposit => {

        if (
            filters.transactionName &&
            !deposit.depositName
                .toLowerCase()
                .includes(filters.transactionName.toLowerCase())
        ) {
            return false;
        }

        if (
            filters.vendor &&
            !deposit.vendorName
                .toLowerCase()
                .includes(filters.vendor.toLowerCase())
        ) {
            return false;
        }

        if (
            filters.startDate &&
            deposit.depositDate < filters.startDate
        ) {
            return false;
        }

        if (
            filters.endDate &&
            deposit.depositDate > filters.endDate
        ) {
            return false;
        }

        if (
            filters.minAmount &&
            deposit.depositAmount < Number(filters.minAmount)
        ) {
            return false;
        }

        if (
            filters.maxAmount &&
            deposit.depositAmount > Number(filters.maxAmount)
        ) {
            return false;
        }

        return true;
    });
}

function filterPayments(filters) {

    return allPayments.filter(payment => {

        if (
            filters.transactionName &&
            !payment.paymentName
                .toLowerCase()
                .includes(filters.transactionName.toLowerCase())
        ) {
            return false;
        }

        if (
            filters.vendor &&
            !payment.vendorName
                .toLowerCase()
                .includes(filters.vendor.toLowerCase())
        ) {
            return false;
        }

        if (
            filters.startDate &&
            payment.paymentDate < filters.startDate
        ) {
            return false;
        }

        if (
            filters.endDate &&
            payment.paymentDate > filters.endDate
        ) {
            return false;
        }

        if (
            filters.minAmount &&
            payment.paymentAmount < Number(filters.minAmount)
        ) {
            return false;
        }

        if (
            filters.maxAmount &&
            payment.paymentAmount > Number(filters.maxAmount)
        ) {
            return false;
        }

        return true;
    });
}

function applyFilters() {

    const filters = getFilters();

    const depositsSection =
        document.querySelector(".ledger-card:first-child");

    const paymentsSection =
        document.querySelector(".ledger-card:last-child");

    if (filters.transactionType === "deposits") {

        depositsSection.style.display = "block";
        paymentsSection.style.display = "none";

        displayDeposits(
            filterDeposits(filters)
        );

        return;
    }

    if (filters.transactionType === "payments") {

        depositsSection.style.display = "none";
        paymentsSection.style.display = "block";

        displayPayments(
            filterPayments(filters)
        );

        return;
    }

    depositsSection.style.display = "block";
    paymentsSection.style.display = "block";

    displayDeposits(
        filterDeposits(filters)
    );

    displayPayments(
        filterPayments(filters)
    );
}

function resetFilters() {

    document.getElementById("transactionType").value =
        "all";
    document.getElementById("startDate").value = "";
    document.getElementById("endDate").value = "";
    document.getElementById("vendorFilter").value = "";
    document.getElementById("minAmount").value = "";
    document.getElementById("maxAmount").value = "";

    displayDeposits(allDeposits);
    displayPayments(allPayments);
}

function showDepositForm() {

    const form =
        document.getElementById("depositForm");

    form.style.display = "block";
}

function hideDepositForm() {

    const form =
        document.getElementById("depositForm");

    form.style.display = "none";
}

function showPaymentForm() {

    const form =
        document.getElementById("paymentForm");
        
    form.style.display = "block";
}

function hidePaymentForm() {

    const form =
        document.getElementById("paymentForm");

    form.style.display = "none";
}