import output from "./output";
import tabela from "./tabelaIMC";

async function main() {
    
    const peso: number = parseFloat(await output("Digite seu peso: "));
    const altura: number = parseFloat(await output("Digite sua altura: "));
    tabela(peso/altura);
}

main();