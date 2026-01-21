(function() {
    const token = localStorage.getItem('token');
    const user = JSON.parse(localStorage.getItem('user'));

    // Exclude auth pages from the guard
    if (window.location.pathname.includes('login.html') || window.location.pathname.includes('register.html')) {
        // If user is already logged in, redirect them away from auth pages
        if (token && user) {
            if (user.role === 'ADMIN') {
                window.location.href = '/admin.html';
            } else {
                window.location.href = '/dashboard.html';
            }
        }
        return;
    }

    // If no token, redirect to login
    if (!token) {
        window.location.href = '/login.html';
        return;
    }

    // Role-based protection for admin pages
    if (window.location.pathname.includes('admin')) {
        if (!user || user.role !== 'ADMIN') {
            alert('Access Denied: You do not have permission to view this page.');
            window.location.href = '/dashboard.html';
        }
    }
})();
