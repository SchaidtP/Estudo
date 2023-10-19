const API = "http://localhost:8080/api/v1/user/users";
const btnSair = document.querySelector('button');
const ul = document.querySelector('ul');

btnSair.addEventListener('click', () => {
    localStorage.removeItem('token');
    window.location.href = '../sign-in/index.html';
})

window.addEventListener('load', async () => {
    try {
        const response = await fetch(API, {
            method: 'GET',
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem('token')
            },
        })
        
        if(response.ok) {
            const listUsers = await response.json();
            listUsers.forEach(user => {
                const li = document.createElement('li');
                li.innerHTML = `
                    <h2><span>Username:</span> <span class="username">${user.username}</span></h2>
                    <p><span>Enabled:</span> ${user.enabled}</p>
                    <p><span>Credentials Non Expired:</span> ${user.credentialsNonExpired}</p>
                    <p><span>Account Non Locked:</span> ${user.accountNonLocked}</p>
                    <p><span>Account Non Expired:</span> ${user.accountNonExpired}</p>
                `;
                ul.appendChild(li);
            });
        } else {
            throw new Error('Acesso Negado')
        }
    } catch (error) {
        console.log('ERROR: ', error)
    }
})