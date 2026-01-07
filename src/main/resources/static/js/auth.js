const BASE_URL = "http://localhost:8080/api/auth"; // update if needed

// ---------- REGISTER ----------
function registerUser(event) {
    event.preventDefault();

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!name || !email || !password) {
        alert("All fields are mandatory.");
        return;
    }

    const payload = {
        name: name,
        email: email,
        password: password
    };

    fetch(`${BASE_URL}/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Registration failed");
            }
            return response.json();
        })
        .then(data => {
            alert("Registration successful. Please login.");
            window.location.href = "login.html";
        })
        .catch(error => {
            console.error(error);
            alert("Registration failed. Try again.");
        });
}

// ---------- LOGIN ----------
function loginUser(event) {
    event.preventDefault();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!email || !password) {
        alert("Email and password are required.");
        return;
    }

    const payload = {
        email: email,
        password: password
    };

    fetch(`${BASE_URL}/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Login failed");
            }
            return response.json();
        })
        .then(data => {
            // Assuming backend sends JWT token
            localStorage.setItem("token", data.token);
            alert("Login successful");
            // redirect to dashboard later
        })
        .catch(error => {
            console.error(error);
            alert("Invalid credentials");
        });
}

// ---------- LOGOUT ----------
function logoutUser() {
    localStorage.removeItem("token");
    alert("Logged out successfully");
    window.location.href = "login.html";
}
