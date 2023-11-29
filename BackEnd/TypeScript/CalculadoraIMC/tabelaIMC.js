"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
function tabela(imc) {
    if (imc < 16.9) {
        console.log("Muito abaixo do peso");
    }
    else if (imc >= 17) {
        console.log("Abaixo do peso");
    }
    else if (imc >= 18.5) {
        console.log("Peso normla");
    }
    else if (imc >= 25) {
        console.log("Acima do peso");
    }
    else if (imc >= 30) {
        console.log("Obesidade grau I");
    }
    else if (imc >= 35 && imc <= 40) {
        console.log("Obesidade grau II");
    }
    else {
        console.log("Obesidade grau III");
    }
}
exports.default = tabela;
