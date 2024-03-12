package Programa;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AgenciaBancaria {

	static ArrayList<Conta> contasBancarias;

	public static void main(String[] args) {
		contasBancarias = new ArrayList<Conta>();
		operacoes();
	}

	public static void operacoes() {
		int operacao =
			Integer.parseInt(JOptionPane.showInputDialog(
					"---------- Selecione uma operação ----------" +
			    	"\n                 1 - Criar Conta" +
				    "\n                 2 - Depositar  " +
			     	"\n                 3 - Sacar      " +
			     	"\n                 4 - Transferir " +
			        "\n                 5 - Listar     " +
				    "\n                 6 - Sair       "));

		switch (operacao) {
		case 1:
			criarConta();
			break;
		case 2:
			depositar();
			break;
		case 3:
			sacar();
			break;
		case 4:
			transferir();
			break;
		case 5:
			listarContas();
			break;
		case 6:
			JOptionPane.showMessageDialog(null, "Obrigado por ser nosso Cliente!");
			System.exit(0);

		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida!");

			operacoes();
			break;
		}
	}

	public static void criarConta() {

		Cliente cliente = new Cliente();

		cliente.setNome(JOptionPane.showInputDialog("Nome:"));
		cliente.setCpf(JOptionPane.showInputDialog("CPF:"));
		cliente.setEmail(JOptionPane.showInputDialog("Email:"));
		cliente.setTelefone(JOptionPane.showInputDialog("Telefone:"));

		Conta conta = new Conta(cliente);

		contasBancarias.add(conta);
		JOptionPane.showMessageDialog(null, "Sua Conta foi criada como Sucesso!");
		operacoes();

	}

	public static Conta encontrarConta(int numeroConta) {
		Conta conta = null;
		if (contasBancarias.size() > 0) {
			for (Conta c : contasBancarias) {
				if (c.getNumeroConta() == numeroConta)
					;
				conta = c;
			}
		}
		return conta;
	}

	public static void depositar() {

		int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da Conta: "));

		Conta conta = encontrarConta(numeroConta);
		if (conta != null) {
			Double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Qual valor que deseja depositar?"));
			conta.depositar(valorDeposito);
			JOptionPane.showMessageDialog(null, "Valor Depositado com Sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Conta Não Encontrada");
		}
		operacoes();
	}

	public static void sacar() {

		int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Número da Conta: "));

		Conta conta = encontrarConta(numeroConta);
		if (conta != null) {	
			Double valorsaque = Double.parseDouble(JOptionPane.showInputDialog("Qual valor que deseja sacar?"));		
			conta.sacar(valorsaque);
			JOptionPane.showMessageDialog(null, "Saque Realizado com Sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Conta Não Encontrada!");
		}
		operacoes();
	}

	public static void transferir() {

		int numeroContaOrigem = Integer.parseInt(JOptionPane.showInputDialog("Informe o Número da Conta de Origem:"));

		Conta contaOrigem = encontrarConta(numeroContaOrigem);
		if (contaOrigem != null) {

			int numeroContaDestino = Integer.parseInt(JOptionPane.showInputDialog("nforme o Número da Conta de Destino:"));
			Conta contaDestino = encontrarConta(numeroContaDestino);

			if (contaDestino != null) {
				Double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o Valor a Transferir:"));
				contaOrigem.transferir(contaDestino, valor);
			}
		}
		operacoes();
	}

	public static void listarContas() {
		if (contasBancarias.size() > 0) {
			for (Conta conta : contasBancarias) {
				JOptionPane.showMessageDialog(null, conta);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não Existe Contas Cadastradas!");
		}
		operacoes();
	}

}