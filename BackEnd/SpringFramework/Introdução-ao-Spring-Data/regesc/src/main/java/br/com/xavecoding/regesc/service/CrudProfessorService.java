package br.com.xavecoding.regesc.service;

import br.com.xavecoding.regesc.orm.Disciplina;
import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
//@Transactional // Pra quando o fetch = FetchType.LAZY
public class CrudProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Transactional // Pra quando o fetch = FetchType.LAZY
    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar novo Professor");
            System.out.println("2 - Atualizar um Professor");
            System.out.println("3 - Visualizar todos os Professores");
            System.out.println("4 - Deletar um Professor");
            System.out.println("5 - Visualizar um Professor");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
                case 2:
                    this.atualizar(scanner);
                    break;
                case 3:
                    this.visualizar();
                    break;
                case 4:
                    this.deletar(scanner);
                    break;
                case 5:
                    this.visualizarProfessor(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.next();

        System.out.print("Digite o prontuario do professor: ");
        String prontuario = scanner.next();

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor salvo no Banco!!!\n");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o Id do Professor a ser atualizado: ");
        Long id = scanner.nextLong();

        Optional<Professor> optional = this.professorRepository.findById(id);

        if (optional.isPresent()) {
            System.out.print("Digite o nome do professor: ");
            String nome = scanner.next();

            System.out.print("Digite o prontuario do professor: ");
            String prontuario = scanner.next();

            Professor professor = optional.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);

            professorRepository.save(professor);
            System.out.println("Professor atualizado com sucesso!!!\n");
        }
        else {
            System.out.println("O id do professor informado: " + id + " é inválido\n");
        }
    }

    private void visualizar() {
        Iterable<Professor> professores = this.professorRepository.findAll();
        // ALTERNATIVA 1
        for (Professor professor : professores) {
            System.out.println(professor);
        }

        // ALTERNATIVA 2
//        professores.forEach(professor -> {
//            System.out.println(professor);
//        });

        // ALTERNATIVA 3
//        professores.forEach(System.out::println);
        System.out.println();
    }


    private void deletar(Scanner scanner) {
        System.out.print("Digite o Id do Professor a ser deletado: ");
        Long id = scanner.nextLong();
        this.professorRepository.deleteById(id);  // lançará uma exception se não achar o ID passado na tabela
        System.out.println("Professor Deletado!\n");
    }


    @Transactional // Pra quando o fetch = FetchType.LAZY
    private void visualizarProfessor(Scanner scanner) {
        System.out.println("Id do Professor: ");
        Long id = scanner.nextLong();

        Optional<Professor> optional = this.professorRepository.findById(id);
        if(optional.isPresent()) {
            Professor professor = optional.get();

            System.out.println("Professor: {");
            System.out.println("Id: " + professor.getId());
            System.out.println("Nome: " + professor.getNome());
            System.out.println("Prontuario: " + professor.getProntuario());
            System.out.println("Disciplinas: [: ");
            for (Disciplina disciplina: professor.getDisciplinas()) {
                System.out.println("\tId: " + disciplina.getId());
                System.out.println("\tNome: " + disciplina.getNome());
                System.out.println("\tSemestre: " + disciplina.getSemestre());
                System.out.println();
            }
            System.out.println("]\n}");
        }
    }
}
