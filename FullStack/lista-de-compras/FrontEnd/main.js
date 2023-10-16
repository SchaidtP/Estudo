const API = "http://localhost:8080/api/v1/product";
const produto = document.querySelector('#produto');
const quantidade = document.querySelector('#quantidade');
const unidadeDeMedida = document.querySelector('#unidade');

const btnAddProduto = document.querySelector('.btn-add-produto');
btnAddProduto.addEventListener('click', async () => {
    try {
        const data = {
            name: produto.value,
            amount: quantidade.value,
            type: unidadeDeMedida.value
        }

        const response = await fetch(API, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        })
        location.reload();
    } catch (error) {
        console.log('Erro: ', error)
    }
})

window.addEventListener('load', async () => {
    try {
        const ul = document.querySelector('ul');
        const response = await fetch(API, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'}
        })

        if(!response.ok){
            return null;
        }

        const listProduto = await response.json();
        console.log(listProduto);
        
        listProduto.forEach(produto => {
            const li = document.createElement('li');
            li.id = produto.id
            li.innerHTML = `
                <p class="ls-produto">${produto.name}</p>
                <p class="ls-quantidade">${produto.amount} <span>${produto.type}</span></p>
                <div class="buttons">
                    <button class="btn-editar">Editar</button>
                    <button class="btn-excluir-produto">Excluir</button>
                </div>`;
            ul.appendChild(li);

            const btnEditarProduto = li.querySelector('.btn-editar');
            btnEditarProduto.addEventListener('click', () => {
                li.style.display = 'none';
                editarProduto(produto);
            })

            const btnExcluirProduto = li.querySelector('.btn-excluir-produto');
            btnExcluirProduto.addEventListener('click', () => {
                excluirProduto(produto.id);
            })
        });
    } catch (error) {
        console.log('Erro: ', error)
    }
})

async function excluirProduto(id) {
    try {
        const response = await fetch(API + `/${id}`, {
            method: 'DELETE',
            headers: {'Content-Type': 'application/json'}
        });

        if(response.status !== 204){
            return null;
        }
        location.reload();
    } catch (error) {
        console.log('Erro: ', error)
    }
}

function editarProduto(produto) {
    const section = document.querySelector('section')
    section.children[0].style.display = "none";
    section.innerHTML = `
        <div class="add-produto">
            <input type="text" value="${produto.name}" id="produto">
            <input type="number" value="${produto.amount}" id="quantidade">
            <select id="unidade">
                <option disabled selected>Unidade de medida</option>
                <option value="kg">Quilogramas (kg)</option>
                <option value="g">Gramas (g)</option>
                <option value="L">Litros (L)</option>
                <option value="mL">Mililitros (mL)</option>
                <option value="Un">Unidade (Un)</option>
            </select>
            <button class="btn-editar-produto"><i class="mdi mdi-basket-check-outline"></i></button>
        </div>`;

    const select = section.querySelector('select');
    select.value = produto.type;

    const btnEditar = section.querySelector('.btn-editar-produto');
    btnEditar.addEventListener('click', () => {
        const data = {
            name: section.querySelector('#produto').value,
            amount : section.querySelector('#quantidade').value,
            type: select.value
        }
        editar(produto.id, data);
    })

    
}


async function editar(id, produto) {
    try {
        const response = await fetch(API + `/${id}`, {
            method: 'PATCH',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(produto)
        });

        if(!response.ok){
            return null;
        }
        location.reload();
    } catch (error) {
        console.log('Erro: ', error)
    }
}


