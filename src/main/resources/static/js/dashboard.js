document.addEventListener('DOMContentLoaded', () => {
    const user = JSON.parse(localStorage.getItem('user'));
    
    if (user) {
        document.getElementById('username').textContent = user.username;
        
        if (user.role === 'ADMIN') {
            const adminLinkContainer = document.getElementById('admin-link-container');
            adminLinkContainer.style.display = 'block';
        }
    } else {
        // This case should ideally not happen due to the auth guard
        // but as a fallback, we clear storage and redirect.
        logout();
    }
});
