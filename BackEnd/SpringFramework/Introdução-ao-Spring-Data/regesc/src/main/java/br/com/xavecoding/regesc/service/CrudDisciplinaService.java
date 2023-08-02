package br.com.xavecoding.regesc.service;

import br.com.xavecoding.regesc.orm.Aluno;
import br.com.xavecoding.regesc.orm.Disciplina;
import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.AlunoRepository;
import br.com.xavecoding.regesc.repository.DisciplinaRepository;
import br.com.xavecoding.regesc.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CrudDisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AlunoRepository alunoRepository;

    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual ação você quer executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Cadastrar nova Disciplina");
            System.out.println("2 - Atualizar uma Disciplina");
            System.out.println("3 - Visualizar todos as Disciplinas");
            System.out.println("4 - Deletar uma Disciplina");
            System.out.println("5 - Matricular alunos");

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
                    this.matricularAlunos(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Nome da Disciplina: ");
        String nome = scanner.next();

        System.out.print("Semestre: ");
        Integer semestre = scanner.nextInt();

        System.out.print("Professor ID: ");
        Long professorId = scanner.nextLong();

        Optional<Professor> optional = professorRepository.findById(professorId);
        if(optional.isPresent()) {
            Professor professor = optional.get();
            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplinaRepository.save(disciplina);
            System.out.println("Salvo!\n");
        }
        else {
            System.out.println("Professor ID " + professorId + " invalido");
        }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o Id da Disciplina a ser atualizada: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();

            System.out.print("Nome da Disciplina: ");
            String nome = scanner.next();

            System.out.print("Semestre: ");
            Integer semestre = scanner.nextInt();

            System.out.print("Professor ID: ");
            Long professorId = scanner.nextLong();

            Optional<Professor> optionalProfessor = this.professorRepository.findById(professorId);
            if (optionalProfessor.isPresent()) {
                Professor professor = optionalProfessor.get();

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);

                disciplinaRepository.save(disciplina);
                System.out.println("Atualizado!!!\n");
            }
            else {
                System.out.println("O id do professor informado: " + professorId + " é inválido\n");
            }
        }
        else {
            System.out.println("O id da disciplina informado: " + id + " é inválido\n");
        }
    }

    private void visualizar() {
        Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();
        for (Disciplina disciplina : disciplinas) {
            System.out.println(disciplina);
        }
        System.out.println();
    }


    private void deletar(Scanner scanner) {
        System.out.print("Id: ");
        Long id = scanner.nextLong();
        this.disciplinaRepository.deleteById(id);
        System.out.println("Disciplina Deletada!\n");
    }

    private Set<Aluno> matricular(Scanner scanner) {
        boolean isTrue = true;
        Set<Aluno> alunos = new HashSet<>();

        while (isTrue) {
            System.out.println("Id do aluno a ser matriculado ( digite 0 para sair ): ");
            Long alunoId = scanner.nextLong();

            if(alunoId > 0) {
                System.out.println("alunoId: " + alunoId);
                Optional<Aluno> optional = this.alunoRepository.findById(alunoId);
                if(optional.isPresent()) {
                    alunos.add(optional.get());
                } else {
                    System.out.println("Nenhum aluno possui o id " + alunoId + "!");
                }
            }
            else {
                isTrue = false;
            }
        }
        return alunos;
    }

    private void matricularAlunos(Scanner scanner) {
        System.out.println("Digite o Id da Disciplina para matricular alunos: ");
        Long id = scanner.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        if(optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();
            Set<Aluno> novosAlunos = this.matricular(scanner);
            disciplina.getAlunos().addAll(novosAlunos);
            this.disciplinaRepository.save(disciplina);
        } else {
            System.out.println("Nenhum aluno possui o id " + id + "!");
        }
    }
}
