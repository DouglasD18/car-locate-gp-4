package services;

import models.TipoVeiculo;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Fluxo {

    private final  Menu menu = new Menu();
    private final Scanner scanner = new Scanner(System.in);

    private int solicitaTrecho(String trecho) {
        try {
            System.out.printf("Digite o %s da devolução:\n", trecho);
            int ano = scanner.nextInt();
            scanner.nextLine();

            return ano;
        } catch (InputMismatchException e) {
            System.out.printf("O %s deve ser um número inteiro!\n", trecho);
            return solicitaTrecho(trecho);
        }
    }

    private LocalDateTime solicitaData(){

        int ano = solicitaTrecho("ano");
        int mes = solicitaTrecho("mês");
        int dia = solicitaTrecho("dia");
        int hora = solicitaTrecho("hora");
        int minuto = solicitaTrecho("minuto");

        return LocalDateTime.of(ano, mes, dia, hora, minuto, 0);
    }

    private String getPlaca() {
        try {
            System.out.println("Digite a placa do Veiculo.");
            String placa = scanner.nextLine();
            if (placa.length() != 7) {
                throw new IllegalArgumentException("A placa não pode estar vazia");
            }

            return placa;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            return getPlaca();
        }

    }

    private TipoVeiculo getTipoVeiculo() {
        try {
            System.out.println("Digite qual o tipo do Veiculo.");
            String tipoVeiculo = scanner.nextLine();

            if (tipoVeiculo.equals("SUV")) {
                return TipoVeiculo.SUV;
            }

            if (tipoVeiculo.equals("Pequeno")) {
                return TipoVeiculo.PEQUENO;
            }

            if (tipoVeiculo.equals("Médio")) {
                return TipoVeiculo.MEDIO;
            }

            throw new IllegalArgumentException("O tipo de Veiculo deve ser Pequeno, Médio ou SUV");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            return getTipoVeiculo();
        }
    }

    private void cadatrarVeiculo() {
        String placa = getPlaca();
        TipoVeiculo tipoVeiculo = getTipoVeiculo();

        menu.adicionarVeiculo(placa, tipoVeiculo);
    }

    public boolean fluxoPrincipal() {

        System.out.println("\n----------------------------------");
        System.out.println("1 - Cadastrar Veiculo");
        System.out.println("2 - Listar Veiculos");
        System.out.println("3 - Cadastrar Cliente");
        System.out.println("4 - Listar Clientes");
        System.out.println("5 - Criar Locação");
        System.out.println("6 - Listar Locações por Cliente");
        System.out.println("7 - Pagar Locação");
        System.out.println("8 - Sair");
        System.out.println("----------------------------------\n");

        System.out.println("Digite o numero da opção desejada: ");

        try {
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    return true;
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Atores ---");
                    return true;
                }
                case 3 -> {
                    return true;
                }
                case 4 -> {
                    System.out.println("\n--- Lista de Diretores ---");
                    return true;
                }
                case 5 -> {
                    return true;
                }
                case 6 -> {
                    System.out.println("\n--- Adicionar Diretor em um Filme ---");
                    return true;
                }
                case 7 -> {
                    System.out.println("\n--- Adicionar Ator em um Filme ---");
                    return true;
                }
                case 8 -> {
                    return false;
                }
                default -> {
                    System.out.println("Opção não encontrada, tente novamente!");
                    return true;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Precisa ser um número de 1 a 8.");
            return true;
        }

    }

    public void closeScanner() {
        scanner.close();
    }

}
