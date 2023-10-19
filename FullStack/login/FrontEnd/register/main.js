const API = "http://localhost:8080/api/v1/user";
const email = document.querySelector('#user');
const password = document.querySelector('#password');
const div = document.querySelector('.mensagem-erro');
const btnRegister = document.querySelector('button');

btnRegister.addEventListener('click', async () => {
    try {
        const data = {
            email: email.value,
            password: password.value,
        }

        const response = await fetch(API, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })

        if(response.ok) {
            window.location.href = '../sign-in/index.html'
        } else if (response.status === 400 || response.status === 409) {
            div.innerHTML = `<p>${await response.text()}</p>`
        } else {        
            let textMensagem = await response.json()
            div.innerHTML = `<p>${textMensagem.body}</p>`
        }
        email.value = '';
        password.value = '';
        setTimeout(() => {
            div.innerHTML = "";
        }, 2500)
    } catch (error) {
        console.log('Error: ', error);
    }
})