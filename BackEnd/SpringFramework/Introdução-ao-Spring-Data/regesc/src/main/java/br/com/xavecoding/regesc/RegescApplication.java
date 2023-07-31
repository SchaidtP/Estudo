package br.com.xavecoding.regesc;

import br.com.xavecoding.regesc.service.CrudProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {

	@Autowired
	CrudProfessorService professorService;

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);

		while (isTrue) {
			System.out.println("Qual entidade você deseja interagir:");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");
			int opcao = scanner.nextInt();

			switch (opcao) {
				case 1:
					this.professorService.menu(scanner);
					break;

				case 0:
					System.out.println("Saindo...");
					isTrue = false;
					break;

				default:
					System.out.println("Opcao Invalida");
					break;
			}
		}
	}
}
