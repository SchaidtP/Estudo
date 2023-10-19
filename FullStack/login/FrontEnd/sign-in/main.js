const API = "http://localhost:8080/api/v1/user/login";
const email = document.querySelector('#user');
const password = document.querySelector('#password');
const div = document.querySelector('.mensagem-erro');
const btnLogin = document.querySelector('button');

btnLogin.addEventListener('click', async () => {
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
            localStorage.setItem('token', `Bearer ${await response.text()}`)
            console.log(localStorage.getItem('token'));
            window.location.href = '../menu/index.html'
        } else if (response.status === 401) {
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