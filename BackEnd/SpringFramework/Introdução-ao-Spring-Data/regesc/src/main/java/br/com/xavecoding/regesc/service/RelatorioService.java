package br.com.xavecoding.regesc.service;

import br.com.xavecoding.regesc.orm.Aluno;
import br.com.xavecoding.regesc.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {

    @Autowired
    private AlunoRepository alunoRepository;

    public void menu(Scanner scanner) {
        boolean isTrue = true;

        while (isTrue) {
            System.out.println("\nQual relatório você deseja?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - Alunos por nome");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> this.alunosPorNome(scanner);
                default -> isTrue = false;
            }
        }
        System.out.println();
    }

    private void alunosPorNome(Scanner scanner) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.next();

        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWith(nome);
        for(Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }

}
