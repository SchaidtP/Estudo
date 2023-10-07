var matriz = [
    [0, 0, 0],
    [0, 0, 0],
    [0, 0, 0]
];
var contador = 1;


function jogar(vertical, horizontal, posicao) {
    let jogador;
    if(matriz[vertical][horizontal] === 0) {
        if(contador % 2 !== 0) {
            jogador = "X";
        } else {
            jogador = "O";
        }
       
        let local = document.querySelector("#" + posicao);
        local.innerHTML = jogador;
        local.classList.add("nao-selecionar");
        matriz[vertical][horizontal] = jogador;
        contador++;

        if (contador === 10) resultado('E');


        for(let i=0; i<3; i++) {
            if(matriz[i][0] === matriz[i][1] && matriz[i][1] === matriz[i][2] && matriz[i][2] === matriz[i][0]
                && matriz[i][0] !== 0 && matriz[i][1] !== 0 && matriz[i][2] !== 0) {
                    resultado(jogador);
            }

            else if(matriz[0][i] === matriz[1][i] && matriz[1][i] === matriz[2][i] && matriz[2][i] === matriz[0][i]
                && matriz[0][i] !== 0 && matriz[1][i] !== 0 && matriz[2][i] !== 0) {
                    resultado(jogador);
            }

            else if(matriz[0][0] === matriz[1][1] && matriz[1][1] === matriz[2][2] && matriz[2][2] === matriz[0][0]
                && matriz[0][0] !== 0 && matriz[1][1] !== 0 && matriz[2][2] !== 0) {
                    resultado(jogador);
            }

            else if(matriz[0][2] === matriz[1][1] && matriz[1][1] === matriz[2][0] && matriz[2][0] === matriz[0][2]
                && matriz[0][2] !== 0 && matriz[1][1] !== 0 && matriz[2][0] !== 0) {
                    resultado(jogador);
            }
        }
        return;
    }
}


function resultado(jogador) {
    let table = document.querySelector("table");
    let main =document.querySelector("main");
    let div = document.createElement("div");
    let text;

    if(jogador === 'E') {
        text = `Empatou!!!`
    } else {
        text = `O jogador "${jogador}" venceu!!!`
    }
    
    table.style.display = 'none';
    div.innerHTML = `<p>${text}</p>`;
    main.appendChild(div);
    setTimeout(() => clear(div, table), 3000);
}

const clear = (div, table) => {
    let listTd = document.querySelectorAll("td");
    div.style.display = 'none';
    table.style.display = 'block';
    contador = 1;
    for(let x=0; x<3; x++){
        for(let y=0; y<3; y++){
            matriz[x][y] = 0;
         }
    }
    for(let td of listTd){
        td.innerHTML = "";
        td.classList.remove("nao-selecionar");
    }
}