package services;

import models.Veiculo;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class GerenciadorDeVeiculos implements Gerenciador<Veiculo> {
    ArrayList<Veiculo> veiculos = new ArrayList<>();

    @Override
    public void cadastrar(Veiculo veiculo) {
        if(buscar(veiculo.getPlaca()) == null){
            veiculos.add(veiculo);
        }else{
            System.out.println("Veículo já está cadastrado!");
        }

    }

    @Override
    public void alterar(Veiculo veiculo) {
        this.veiculos = (ArrayList<Veiculo>) this.veiculos.stream().map(elemente -> {
            if (elemente.getPlaca().equals(veiculo.getPlaca())) {
                return veiculo;
            } else {
                return elemente;
            }

        }).collect(Collectors.toList());
    }

    @Override
    public void excluir(Veiculo veiculo) {
     veiculos.remove(veiculo);
    }

    @Override
    public Veiculo buscar(String placa) {
        return this.veiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    @Override
    public ArrayList<Veiculo> listar() {
        return this.veiculos;
    }

    public void setarDisponibilidade(String placa, boolean disponibilidade) {
        Veiculo veiculo = this.veiculos.stream().filter(elemente -> elemente.getPlaca().equals(placa)).findFirst().orElse(null);

        if (veiculo == null) {
            System.out.println("O veículo não existe.");
        } else {
            this.veiculos = (ArrayList<Veiculo>) this.veiculos.stream().peek(elemente -> {
                if (elemente.getPlaca().equals(placa)) {
                    elemente.setEstaDisponivel(disponibilidade);
                }

            }).collect(Collectors.toList());
        }
    }
}
