const dica = localStorage.getItem('dica').toUpperCase();
const palavraSecreta = localStorage.getItem('palavraSecreta').toUpperCase();
localStorage.removeItem('dica');
localStorage.removeItem('palavraSecreta');
let inputLetra = document.querySelector('#letra-escolhida');
let palavra = document.querySelector('#palavra');
let vidasPerdidas = 0;
let letrasQueJaForam = [];

document.addEventListener("DOMContentLoaded", () => {
    var palavraCodificada = "";
    for(let letra of palavraSecreta) {
        if(letra === " ") {
            palavraCodificada += "-"
        } else {
            palavraCodificada += "_"
        }
    }
    palavra.textContent = palavraCodificada;
})

const h1 = document.querySelector('h1')
h1.innerText = `Dica: ${dica}`;


inputLetra.addEventListener("keyup", (event) => {
    if(event.keyCode === 13) {
        event.preventDefault();
        verificarLetra();
    }
})


function verificarLetra() {
    let letra = inputLetra.value.toUpperCase();
    if(!letrasQueJaForam.includes(letra)) {
        let p = palavra.textContent.split(''); // Converte a string em um array de caracteres   
        if(palavraSecreta.includes(letra)) {
            for(let i=0; i<p.length; i++) {
                if(palavraSecreta[i] === letra) {
                    p[i] = letra;
                    palavra.textContent = p.join(''); // Converte o array de caracteres de volta para uma string
                }            
            }
            if (!p.includes('_')){
                vencedor(1);
            }
        } else {
            vidasPerdidas++;
            let img = document.querySelector('img');
            img.src = `../img/forca${vidasPerdidas}.jpg`
        }
        let letrasJaForam = document.querySelector('#letras-que-ja-foram');
        if(letrasJaForam.textContent === ""){
            letrasJaForam.textContent += letra
        } else {
            letrasJaForam.textContent += ", " + letra
        }
    
        if(vidasPerdidas === 6) {
            vencedor(0);
        }
        letrasQueJaForam.push(letra);
    } else {
        alert('Essa letra já foi!!');
    }
    inputLetra.value = "";
}

function vencedor(num) {
    let main = document.querySelector('main');
    main.style.display = 'none';

    let div = document.createElement('div');
    div.classList.add('vencedor');
    if(num === 0) {
        div.innerText = "Desafiante Ganhou!!";
    } else {
        div.innerText = "Desafiante Perdeu!!";
    }

    let container = document.querySelector('.container');
    container.appendChild(div);
    setTimeout(() => {
        window.location.assign("./menu.html");
    }, 5000);
}