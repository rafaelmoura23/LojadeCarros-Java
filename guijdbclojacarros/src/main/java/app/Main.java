package app;

import Controller.CarrosDAO;
import View.FramePrincipal;
import Controller.CarrosControl;

public class Main {
    public static void main(String[] args) {
        new FramePrincipal().run();
        // Cria o banco de dados caso não tenha sido criado
        new CarrosDAO().criaTabela();
        new CarrosDAO().cadastrar("GM", "Fusca", "2011", "ABC-1234", "20.000");
    }
}