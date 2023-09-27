package br.com.xavecoding.regesc.service;

import br.com.xavecoding.regesc.orm.Aluno;
import br.com.xavecoding.regesc.orm.Disciplina;
import br.com.xavecoding.regesc.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudAlunoService {

    private final AlunoRepository alunoRepository;

    public CrudAlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional // Pra quando o fetch = FetchType.LAZY
    public void menu(Scanner scanner) {
        boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo Aluno");
            System.out.println("2 - Atualizar um Aluno");
            System.out.println("3 - Visualizar todos os Alunos");
            System.out.println("4 - Deletar um Aluno");
            System.out.println("5 - Visualizar um Aluno");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> this.cadastrar(scanner);
                case 2 -> this.atualizar(scanner);
                case 3 -> this.visualizar();
                case 4 -> this.deletar(scanner);
                case 5 -> this.visualizarAluno(scanner);
                default -> isTrue = false;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.next();

        System.out.print("Digite a Idade: ");
        Integer idade = scanner.nextInt();

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        this.alunoRepository.save(aluno);
        System.out.println("Aluno salvo no Banco!!!\n");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o Id do Aluno a ser atualizado: ");
        Long id = scanner.nextLong();

        Optional<Aluno> optional = this.alunoRepository.findById(id);

        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            System.out.print("Digite o nome do aluno: ");
            String nome = scanner.next();

            System.out.print("Digite a Idade: ");
            Integer idade = scanner.nextInt();

            aluno.setNome(nome);
            aluno.setIdade(idade);
            this.alunoRepository.save(aluno);
            System.out.println("Atualizado atualizado com sucesso!!!\n");
        }
        else {
            System.out.println("O id do aluno informado: " + id + " é inválido\n");
        }
    }

    private void visualizar() {
        Iterable<Aluno> alunos = this.alunoRepository.findAll();
        // ALTERNATIVA 1
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
        System.out.println();
    }


    private void deletar(Scanner scanner) {
        System.out.print("Digite o Id do Aluno a ser deletado: ");
        Long id = scanner.nextLong();
        this.alunoRepository.deleteById(id);  // lançará uma exception se não achar o ID passado na tabela
        System.out.println("Aluno Deletado!\n");
    }


    @Transactional // Pra quando o fetch = FetchType.LAZY
    private void visualizarAluno(Scanner scanner) {
        System.out.println("Id do Aluno: ");
        Long id = scanner.nextLong();

        Optional<Aluno> optional = this.alunoRepository.findById(id);
        if(optional.isPresent()) {
            Aluno aluno = optional.get();

            System.out.println("Id: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Idade: " + aluno.getIdade());
            System.out.println("Disciplinas: [: ");

            if(aluno.getDisciplinas() != null) {
                for (Disciplina disciplina: aluno.getDisciplinas()) {
                    System.out.println("\tNome: " + disciplina.getNome());
                    System.out.println("\tSemestre: " + disciplina.getSemestre());
                    System.out.println();
                }
            }
            System.out.println("]");
        }
        else {
            System.out.println("O id do aluno informado: " + id + " é inválido\n");
        }
    }
}
