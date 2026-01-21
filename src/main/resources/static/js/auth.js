document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    if (loginForm) {
        loginForm.addEventListener('submit', handleLogin);
    }

    const registerForm = document.getElementById('register-form');
    if (registerForm) {
        registerForm.addEventListener('submit', handleRegister);
    }
});

async function handleLogin(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    const response = await fetch_api('/auth/login', {
        method: 'POST',
        body: JSON.stringify(data),
    });

    if (response && response.data && response.data.token) {
        localStorage.setItem('token', response.data.token);
        // Fetch user details to get the role
        await fetchAndStoreUser();
    }
}

async function handleRegister(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // Basic client-side validation
    if (data.password !== data.confirmPassword) {
        alert("Passwords do not match!");
        return;
    }

    const response = await fetch_api('/auth/register', {
        method: 'POST',
        body: JSON.stringify(data),
    });

    if (response) {
        alert('Registration successful! Please log in.');
        window.location.href = '/login.html';
    }
}

async function fetchAndStoreUser() {
    const userResponse = await fetch_api('/auth/me');
    if (userResponse && userResponse.data) {
        localStorage.setItem('user', JSON.stringify(userResponse.data));
        
        // Redirect based on role
        if (userResponse.data.role === 'ADMIN') {
            window.location.href = '/admin.html';
        } else {
            window.location.href = '/dashboard.html';
        }
    } else {
        // Handle case where /me fails after login
        alert('Could not verify user details. Logging out.');
        logout();
    }
}