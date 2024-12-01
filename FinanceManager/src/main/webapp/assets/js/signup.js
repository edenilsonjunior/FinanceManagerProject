window.onload = initPage;

function initPage() {
    let form;
    form = document.getElementById('signup-form');

    form.noValidate = true;
    form.addEventListener('submit', function (e) {
        let valid = processValidity(this);
        if(!valid){
            e.preventDefault();
        }
    });
}

function processValidity(form) {
    let valid;
    valid = applyValidity(form);
    return valid;
}

function applyValidity(form) {

    if(
        !validateName(document.getElementById('full-name')) ||
        !validateBirthDate(document.getElementById('birth-date')) ||
        !validateEmail(document.getElementById('email')) ||
        !validatePassword(document.getElementById('password'), document.getElementById('confirmPassword'))
    ){
        return false;
    }
    
    return true;
}

function validateName(element) {
    let valid = true;
    let span = document.getElementById('full-name-error');
    if(element.value.trim() === ""){
        span.innerHTML = "O nome não pode estar vazio.";
        valid = false;
    } else {
        span.innerHTML = "";
    }
    return valid;
}

function validateBirthDate(element) {
    let valid = true;
    let span = document.getElementById('birth-date-error');
    let birthDate = new Date(element.value);
    let today = new Date();
    if(!element.value || birthDate >= today){
        span.innerHTML = "A data de nascimento deve ser anterior à data atual.";
        valid = false;
    } else {
        span.innerHTML = "";
    }
    return valid;
}

function validateEmail(element) {
    let valid = true;
    let span = document.getElementById('email-error');
    let regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if(!regex.test(element.value)){
        span.innerHTML = "O e-mail informado não é válido.";
        valid = false;
    } else {
        span.innerHTML = "";
    }
    return valid;
}

function validatePassword(password, confirmPassword) {
    let valid = true;
    let span = document.getElementById('password-error');

    if(password.value.trim() === ""){
        span.innerHTML = 'A senha não pode estar vazia.';
        valid = false;
    }
    else if(password.value !== confirmPassword.value){
        span.innerHTML = 'Os valores dos campos de senha e confirmação ' +
                         'de senha são diferentes.';
        valid = false;
    }else{
        span.innerHTML = '';
    }

    return valid;
}