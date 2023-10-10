let btnPalavraSecreta = document.querySelector("#btn-palavra-secreta");
let btnJogar = document.querySelector(".btn-jogar");

let inputPalavraSecreta = document.querySelector("#palavra_secreta");
let inputDica = document.querySelector("#dica");

btnPalavraSecreta.addEventListener('click', mostrarPalavraSecreta)

inputDica.addEventListener("keyup", (event) => {
    if(event.keyCode === 13) {
        event.preventDefault();
        inputPalavraSecreta.focus();
    }
})

inputPalavraSecreta.addEventListener("keyup", function(event){
    if(event.keyCode === 13) {
        event.preventDefault();
        btnJogar.click();
    }
})

btnJogar.addEventListener('click', () => {
    let valueInputDica = inputDica.value;
    let valueInputPalavraSecreta = inputPalavraSecreta.value;
    if(valueInputDica !== "" && valueInputDica !== null && valueInputDica !== undefined 
        && valueInputPalavraSecreta !== "" && valueInputPalavraSecreta !== null && valueInputPalavraSecreta !== undefined ) {
        localStorage.setItem('dica', valueInputDica);
        localStorage.setItem('palavraSecreta', valueInputPalavraSecreta);
        window.location.assign("./jogo.html");
    } else {
        alert('Por favor, preencha todos os elementos!!');
    }   
})

function mostrarPalavraSecreta() {
    btnPalavraSecreta.children[0].classList.remove("mdi-eye-off-outline");
    btnPalavraSecreta.children[0].classList.add("mdi-eye-outline");
    btnPalavraSecreta.removeEventListener('click', mostrarPalavraSecreta);
    btnPalavraSecreta.addEventListener('click', esconderPalavraSecreta)
    inputPalavraSecreta.setAttribute("type", "text");
}

function esconderPalavraSecreta() {
    btnPalavraSecreta.children[0].classList.add("mdi-eye-off-outline");
    btnPalavraSecreta.children[0].classList.remove("mdi-eye-outline");
    btnPalavraSecreta.removeEventListener('click', esconderPalavraSecreta);
    btnPalavraSecreta.addEventListener('click', mostrarPalavraSecreta)
    inputPalavraSecreta.setAttribute("type", "password");
}