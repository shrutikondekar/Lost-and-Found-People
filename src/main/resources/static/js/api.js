const API_BASE_URL = 'http://localhost:8080';

async function fetch_api(endpoint, options = {}) {
    const url = `${API_BASE_URL}${endpoint}`;
    const token = localStorage.getItem('token');

    const headers = {
        'Content-Type': 'application/json',
        ...options.headers,
    };

    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }

    const config = {
        ...options,
        headers,
    };

    try {
        const response = await fetch(url, config);
        const data = await response.json();

        if (!response.ok) {
            handleApiError(data);
            return null;
        }
        
        return data;
    } catch (error) {
        console.error('API call failed:', error);
        alert('An unexpected error occurred. Please try again later.');
        return null;
    }
}

function handleApiError(error) {
    console.error('API Error:', error);

    if (error.status === 401) {
        // Unauthorized
        alert('Your session has expired. Please log in again.');
        logout();
    } else if (error.status === 403) {
        // Forbidden
        alert('Access Denied: You do not have permission to perform this action.');
        window.location.href = '/dashboard.html'; // Redirect to a safe page
    } else if (error.status === 400 && error.data) {
        // Validation errors
        displayValidationErrors(error.data);
    } 
    else {
        alert(error.message || 'An error occurred.');
    }
}

function displayValidationErrors(errors) {
    // Clear previous errors
    document.querySelectorAll('.error-message').forEach(el => el.remove());

    for (const field in errors) {
        const input = document.querySelector(`[name="${field}"]`);
        if (input) {
            const errorElement = document.createElement('p');
            errorElement.className = 'error-message';
            errorElement.style.color = 'red';
            errorElement.textContent = errors[field];
            input.parentElement.appendChild(errorElement);
        }
    }
}

function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = '/login.html';
}
