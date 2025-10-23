const overlay = document.getElementById('imageHolder');
const container = document.getElementById('flexBox');

function showRegister() {
    container.classList.add('rotate');
    overlay.classList.add('moveLeft');
}

function showLogin() {
    container.classList.add('rotate');
    overlay.classList.remove('moveLeft');
}

// Reset rotation after animation so it can rotate again next time
container.addEventListener('transitionend', (e) => {
    if (e.propertyName === 'transform') {
        container.classList.remove('rotate');
    }
});


const registerForm = document.querySelector('#registerBox form');

function setFormState(activeForm, inactiveForm) {
    // Enable inputs in active form
    activeForm.querySelectorAll('input, button[type="submit"]').forEach(el => {
        el.disabled = false;
        if (el.tagName.toLowerCase() === 'input') el.required = true;
    });
    // Disable inputs in inactive form
    inactiveForm.querySelectorAll('input, button[type="submit"]').forEach(el => {
        el.disabled = true;
        if (el.tagName.toLowerCase() === 'input') el.required = false;
    });
}

function showMessageAndRedirect() {
   alert("Registration Successful! Redirecting to login page...");
   setTimeout(function(){ showLogin(); }, 2000);
}


function showRegister() {
    container.classList.add('rotate');
    overlay.classList.add('moveLeft');
    setFormState(registerForm, loginForm);
}

const btn = document.getElementById("forgot");

btn.addEventListener("click", function(event) {
  event.preventDefault();
  alert("Even I dont know 😁 , SORRY!");
});

function showLogin() {
    container.classList.add('rotate');
    overlay.classList.remove('moveLeft');
    setFormState(loginForm, registerForm);
}
// On page load, set login form active by default
setFormState(loginForm, registerForm);
