package services;

import models.Locacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu<ContaDevedora> {

    private GerenciamentoLocacao gerenciamentoLocacao = new GerenciamentoLocacao();

    public int solicitaTrecho(Scanner input, String trecho) {
        try {
            System.out.printf("Digite o %s da devolução:\n", trecho);
            int ano = input.nextInt();
            input.nextLine();

            return ano;
        } catch (InputMismatchException e) {
            System.out.printf("O %s deve ser um número inteiro!\n", trecho);
            return solicitaTrecho(input, trecho);
        }
    }

    public LocalDateTime solicitaData(){
        Scanner input = new Scanner(System.in);

        int ano = solicitaTrecho(input, "ano");
        int mes = solicitaTrecho(input, "mês");
        int dia = solicitaTrecho(input, "dia");
        int hora = solicitaTrecho(input, "hora");
        int minuto = solicitaTrecho(input, "minuto");

        input.close();

        return LocalDateTime.of(ano, mes, dia, hora, minuto, 0);
    }

    public void listarLocações() {
        ArrayList<Locacao> locacoes = gerenciamentoLocacao.listarLocacoes();

        if (locacoes.isEmpty()) {
            System.out.println("Ainda não existem locações!");
        } else {
            locacoes.forEach(locacao -> System.out.println(locacao.toString()));
        }
    }

    public boolean clienteExiste(String identificador) {
        // Irá receber o CNF ou CPNJ  e usar o GerenciadorClientes para ver verificar se existe

        return true;
    }

    public boolean veiculoExiste(String placa) {
        // Irá receber a placa  e usar o GerenciadorVeiculos para ver verificar se existe

        return true;
    }

    public void listarLocacoesPorCliente(String identificador) {
        if (!clienteExiste(identificador)) {
            throw new IllegalArgumentException("O cliente não existe!");
        }

        ArrayList<Locacao> locacoes = gerenciamentoLocacao.listarPorLocatario(identificador);

        if (locacoes.isEmpty()) {
            System.out.println("O cliente ainda tem locações.");
        }

        locacoes.forEach(locacao -> System.out.println(locacao.toString()));
    }

    public void criarLocacao(String placa, String identificador) {
        if (!veiculoExiste(placa)) {
            throw new IllegalArgumentException("O veiculo não existe!");
        }

        if (!clienteExiste(identificador)) {
            throw new IllegalArgumentException("O cliente não existe!");
        }

        gerenciamentoLocacao.criarLocacao(placa, identificador);
    }

    // public void listarClientes() {}
    // public void listarVeiculos() {}
    // public void adicionarCliente() {}
    // public void adicionarVeiculo() {}
    // public void pagarLocacao() {}

}
