package Controller;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Carros;

public class CarrosControl {

    // atributos
    private List<Carros> carros;
    private DefaultTableModel tableModel;
    private JTable table;

    // construtor
    public CarrosControl(List<Model.Carros> carros, DefaultTableModel tableModel, JTable table) {
        this.carros = carros;
        this.tableModel = tableModel;
        this.table = table;
    }

    // métodos CRUD

    // Atualizar Tabela
    private void atualizarTabela() {
        // atualizar tabela pelo banco de dados
        tableModel.setRowCount(0);
        //carros = new CarrosDAO().listarTodos();
        for (Carros carro : carros) {
            tableModel.addRow(new Object[] { carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPlaca(),
                    carro.getValor() });
        }
    }

    // Cadastrar Carros
    public void cadastrarCarros(String marca, String modelo, String ano, String Cor, String placa, String valor) {
        // new CarrosDAO().inserir(marca, modelo, ano, placa, valor);
        Carros carro = new Carros(marca, modelo, ano, Cor, placa, valor);
        carros.add(carro);
        atualizarTabela();
    }

    public void atualizarCarros(int linhaSelecionada, String marca, String modelo, String ano, String Cor, String placa,
            String valor) {
        if (linhaSelecionada != -1) {
            Carros carro = new Carros(marca, modelo, ano, Cor, placa, valor);
            carros.add(carro);
            atualizarTabela();
        }
    }

}
