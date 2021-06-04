package classe;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import Enum.Parentesco;
import Exception.DependenteException;

public class Dependente extends Pessoa {
	private Parentesco parentesco;

	public Dependente() {
		// TODO Auto-generated constructor stub
	}

	public Dependente(String nome, String cpf, LocalDate dataNascimento, Parentesco parentesco) {
		super(nome, cpf, dataNascimento);
		this.parentesco = parentesco;
	}

	@Override
	public String toString() {
		return "Dependente \nparentesco: " + parentesco + "\nnome: " + nome + "\ncpf: " + cpf + "\ndataNascimento: "
				+ dataNascimento;
	}

	public void idadeDependente() throws DependenteException {
		idade = Period.between(dataNascimento, LocalDate.now());
		if (idade.getYears() > 18) {
			throw new DependenteException("O dependente " + nome
					+ " é maior de idade, então não pode ser registrado.\n-----------------------------------\nPROGRAMA FINALIZADO.");
		}
	}

}
