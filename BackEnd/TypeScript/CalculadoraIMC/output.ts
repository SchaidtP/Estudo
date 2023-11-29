import * as readline from 'readline';

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

const output = (pergunta: string): Promise<string> => {
    return new Promise((resolve, reject) => {
        rl.question(pergunta, (resposta: string) => {
            //rl.close();
            resolve(resposta);
        });
    });
};

export default output;