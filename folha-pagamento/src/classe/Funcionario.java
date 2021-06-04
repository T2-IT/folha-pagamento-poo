package classe;

import java.time.LocalDate;
import Interface.Calculos;

public class Funcionario extends Pessoa implements Calculos {

	private double salarioBruto;
	private double descontoInss;
	private double salarioLiquido;
	private int dependente;
	private double descontoIR;

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public double getDescontoInss() {
		return descontoInss;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	@Override
	public String toString() {
		return "Funcionario \nSalario Bruto: " + salarioBruto + "\nnome: " + nome + "\n" + dataNascimento + "\n"
				+ salarioLiquido;
	}

	public int getDependente() {
		return dependente;
	}

	public void setDependente(int dependente) {
		this.dependente = dependente;
	}

	@Override
	public double calcularINSS() {
		double taxa = 0;
		double deducao = 0;

		if (salarioBruto >= 1100.01 && salarioBruto <= 2203.48) {
			taxa = 0.09;
			deducao = 16.5;
			return descontoInss = salarioBruto * taxa - deducao;
		} else if (salarioBruto >= 2203.49 && salarioBruto <= 3305.22) {
			taxa = 0.12;
			deducao = 82.61;
			return descontoInss = salarioBruto * taxa - deducao;
		} else if (salarioBruto >= 3305.23 && salarioBruto <= 6433.57) {
			taxa = 0.14;
			deducao = 148.72;
			return descontoInss = salarioBruto * taxa - deducao;
		} else if (salarioBruto > 6433.57) {
			taxa = 0.14;
			deducao = 148.72;
			return descontoInss = 6433.57 * taxa - deducao;
		} else {
			taxa = 0.075;
			deducao = 0;
			return descontoInss = salarioBruto * taxa - deducao;
		}

	}

	@Override
	public double calcularIR() {
		double salarioBase = salarioBruto - (dependente * 189.59) - descontoInss;
		if (salarioBase < 1903.98) {
			return descontoIR = 0;
		} else if (salarioBase >= 1903.98 && salarioBase <= 2826.65) {
			return descontoIR = ((salarioBase) * 0.075) - 142.80;
		} else if (salarioBase >= 2826.66 && salarioBase <= 3751.05) {
			return descontoIR = ((salarioBase) * 0.15) - 354.80;
		} else if (salarioBase >= 3751.06 && salarioBase <= 4664.68) {
			return descontoIR = ((salarioBase) * 0.225) - 646.13;
		} else {
			return descontoIR = ((salarioBase) * 0.275) - 869.36;
		}

	}

	@Override
	public double calcularSalarioLiquido() {
		return salarioLiquido = salarioBruto - descontoInss - descontoIR;
	}

}
