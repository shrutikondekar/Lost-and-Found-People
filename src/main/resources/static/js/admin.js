document.getElementById("testAdminBtn").addEventListener("click", () => {
    console.log("Admin request fired…");

    fetch("/api/admin/test")
        .then(res => {
            if (!res.ok) throw new Error(`HTTP ${res.status}`);
            return res.json();
        })
        .then(data => {
            document.getElementById("output").textContent =
                JSON.stringify(data, null, 2);
        })
        .catch(err => {
            document.getElementById("output").textContent =
                `Error: ${err.message}`;
        });
});
