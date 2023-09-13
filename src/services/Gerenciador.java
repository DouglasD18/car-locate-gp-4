package services;

import java.util.ArrayList;

public interface Gerenciador<T> {

    void cadastrar(T objeto);
    void alterar(T objeto);
    void excluir(T objeto);
    T buscar(String parametro);
    ArrayList<T> listar();

}
