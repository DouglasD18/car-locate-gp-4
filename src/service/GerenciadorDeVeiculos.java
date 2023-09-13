package service;

import models.Veiculo;

import java.util.ArrayList;
import java.util.List;


public class GerenciadorDeVeiculos implements Gerenciador<Veiculo>{
    List<Veiculo> veiculos = new ArrayList<>();

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

    }

    @Override
    public void excluir(Veiculo veiculo) {
     veiculos.add(veiculo);
    }

    @Override
    public Veiculo buscar(String placa) {
        for(Veiculo veiculo: veiculos){
            if(veiculo.getPlaca().equals(placa)){
                return veiculo;
            }
        }
        return null;
    }
}
