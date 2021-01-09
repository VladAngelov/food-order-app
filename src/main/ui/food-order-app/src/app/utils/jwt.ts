export function getToken() {
    console.log(localStorage.getItem('token'));
    return localStorage.getItem('token');
}

export function saveToken(token) {
    localStorage.setItem('token', token);
}