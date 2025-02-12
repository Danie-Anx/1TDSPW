function abrirModal(idModal) {
    document.getElementById(idModal).style.display = "flex";
}

function fecharModal(idModal) {
    document.getElementById(idModal).style.display = "none";
}

function abrirModal(idModal) {
    document.getElementById(idModal).style.display = "flex";
}

function fecharModal(idModal) {
    document.getElementById(idModal).style.display = "none";
}

const users = [
    { email: "leo@gmail.com", password: "12345", name: "Leonardo" },
    { email: "marcio@gmail.com", password: "5678", name: "Marcio" }
];

function login(event) {
    event.preventDefault();
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");

    emailInput.classList.remove("erro");

    const email = emailInput.value;
    const password = passwordInput.value;

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        emailInput.classList.add("erro");
        const errorElement = document.getElementById("erroLogin");
        errorElement.textContent = "Formato de e-mail inválido";
        setTimeout(() => {
            errorElement.textContent = "";
        }, 5000);
        return;
    }

    const user = users.find(u => u.email === email && u.password === password);

    if (user) {
        sessionStorage.setItem("user", JSON.stringify(user));
        const successMessage = document.getElementById("sucessoLogin");
        if (successMessage) {
            successMessage.textContent = "Login realizado com sucesso!";
            successMessage.classList.add("login-success");
        }
        setTimeout(() => {
            window.location.href = "sucesso.html";
        }, );
    } else {
        const errorElement = document.getElementById("erroLogin");
        errorElement.textContent = "Email ou senha inválidos";
        setTimeout(() => {
            errorElement.textContent = "";
        }, 5000);
    }
}

function logout() {
    sessionStorage.removeItem("user");
    window.location.href = "login.html";
}

function checkAuth() {
    const user = sessionStorage.getItem("user");
    if (!user) {
        window.location.href = "login.html";
    } else {
        const userInfo = JSON.parse(user);
        const userInfoElement = document.getElementById("infoUsuario");
        userInfoElement.innerHTML = `<p>Bem-vindo, ${userInfo.name} (${userInfo.email})</p>`;
        const logoutBtn = document.getElementById("logoutBtn");
        logoutBtn.addEventListener("click", logout);
    }
}

const loginForm = document.getElementById("loginForm");

if (loginForm) {
    loginForm.addEventListener("submit", login);
}

if (window.location.pathname.includes("sucesso.html")) {
    checkAuth();
}