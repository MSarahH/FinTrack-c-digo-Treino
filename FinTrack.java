/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fintrack;

/*import java.time.LocalDate;*/

/**
 *
 * @author Maria Sarah de Oliveira André
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class FinTrack {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FinTracker tracker = new FinTracker();

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("\n===== FINTRACK - SEU CONTROLE FINANCEIRO =====");
            System.out.println("1. Adicionar nova transação");
            System.out.println("2. Listar transações");
            System.out.println("3. Mostrar saldo atual");
            System.out.println("4. Remover transação");
            System.out.println("5. Sair");

            try {

                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {

                    case 1:

                        System.out.print("Descrição: ");
                        String descricao = scanner.nextLine();

                        System.out.print("Valor: ");
                        double valor = scanner.nextDouble();

                        System.out.print("É receita? (true/false): ");
                        boolean receita = scanner.nextBoolean();

                        Transacao t = new Transacao(descricao, valor, receita, LocalDate.now());

                        tracker.adicionarTransacao(t);

                        System.out.println("Transação adicionada!");

                        break;

                    case 2:

                        tracker.listarTransacoes();

                        break;

                    case 3:

                        double saldo = tracker.calcularSaldoTotal();

                        System.out.println("Saldo atual: R$ " + saldo);

                        break;

                    case 4:

                        System.out.print("Número da transação: ");
                        int index = scanner.nextInt();

                        tracker.removerTransacao(index);

                        break;

                    case 5:

                        System.out.println("Saindo do sistema...");

                        break;

                    default:

                        System.out.println("Opção inválida.");

                }

            } catch (Exception e) {

                System.out.println("Entrada inválida!");
                scanner.nextLine();

            }

        }

    }
}

class Transacao {

    private String descricao;
    private double valor;
    private boolean ehReceita;
    private LocalDate data;

    public Transacao(String descricao, double valor, boolean receita, LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.ehReceita = receita;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public boolean isReceita() {
        return ehReceita;
    }

    public LocalDate getData() {
        return data;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

class TransacaoMensal extends Transacao {

    public TransacaoMensal(String descricao, double valor, boolean receita, LocalDate data) {
        super(descricao, valor, receita, data);
    }

}

class FinTracker {

    private ArrayList<Transacao> transacoes = new ArrayList<>();

    public void adicionarTransacao(Transacao t) {

        transacoes.add(t);

    }

    public void listarTransacoes() {

        if (transacoes.isEmpty()) {

            System.out.println("Nenhuma transação cadastrada.");
            return;

        }

        for (int i = 0; i < transacoes.size(); i++) {

            Transacao t = transacoes.get(i);

            String tipo;

            if (t.isReceita()) {
                tipo = "Receita";
            } else {
                tipo = "Despesa";
            }

            System.out.println(i + " - " + tipo + " | " + t.getDescricao() + " | R$ " + t.getValor());

        }

    }

    public void removerTransacao(int index) {

        if (index >= 0 && index < transacoes.size()) {

            transacoes.remove(index);
            System.out.println("Transação removida!");

        } else {

            System.out.println("Transação não encontrada.");

        }

    }

    public double calcularSaldoTotal() {

        double saldo = 0;

        for (Transacao t : transacoes) {

            if (t.isReceita()) {

                saldo += t.getValor();

            } else {

                saldo -= t.getValor();

            }

        }

        return saldo;

    }

}