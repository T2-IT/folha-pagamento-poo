package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import Enum.Parentesco;
import Exception.DependenteException;
import classe.Dependente;
import classe.Funcionario;

public class TestesSalario {

	public static void main(String[] args) {
		try {
			Scanner ler = new Scanner(System.in);
			System.out.println("Insira o caminho do arquivo: ");
			String lerArquivo = ler.next();
			File arquivo = new File(lerArquivo);
			Scanner scanner = new Scanner(arquivo);

			Set<Funcionario> SetFuncionario = new HashSet<Funcionario>();
			Set<Dependente> SetDependente = new HashSet<Dependente>();
			ler.close();

			FileOutputStream arquivoSaida = new FileOutputStream("ArquivoSaida.csv");
			BufferedWriter gravarArquivo = new BufferedWriter(new FileWriter("ArquivoSaida.csv"));

			Funcionario funcionario = new Funcionario();
			Dependente dependente = new Dependente();
			int cont = 0;
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				if (!linha.isEmpty()) {
					String[] dados = linha.split(";");
					if (!dados[3].equals("FILHO") && !dados[3].equals("SOBRINHO") && !dados[3].equals("OUTROS")) {
						funcionario = new Funcionario(dados[0], dados[1],
								LocalDate.parse(dados[2], DateTimeFormatter.BASIC_ISO_DATE),
								Double.parseDouble(dados[3]));
						SetFuncionario.add(funcionario);

					} else {
						dependente = new Dependente(dados[0], dados[1],
								LocalDate.parse(dados[2], DateTimeFormatter.BASIC_ISO_DATE),
								Parentesco.valueOf(dados[3]));

						SetDependente.add(dependente);
						dependente.idadeDependente();
						cont++;
						funcionario.setDependente(cont);
					}

				} else {
					cont = 0;
				}

			}

			for (Funcionario funcionarios : SetFuncionario) {
				gravarArquivo.write(funcionarios.getNome() + ";");
				gravarArquivo.write(funcionarios.getCpf() + ";");
				gravarArquivo.write(String.format("%.2f", funcionarios.calcularINSS()) + ";");
				gravarArquivo.write(String.format("%.2f", funcionarios.calcularIR()) + ";");
				gravarArquivo.write(String.format("%.2f", funcionarios.calcularSalarioLiquido()) + "\n");
			}
			System.out.println("Lendo arquivo de entrada...");
			Thread.sleep(3000);
			System.out.println("-----------------------------------");
			System.out.println("Gerando arquivo de saida...");
			Thread.sleep(3000);
			System.out.println("-----------------------------------");
			System.out.println("Arquivo de saída gerado.\n-----------------------------------\nPROGRAMA FINALIZADO.");
			gravarArquivo.close();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		} catch (DependenteException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
