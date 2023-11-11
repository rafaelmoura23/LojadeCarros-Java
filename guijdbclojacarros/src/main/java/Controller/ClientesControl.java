package Controller;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Carros;
import Model.Clientes;

public class ClientesControl {

    // atributos
    private List<Clientes> clientes;
    private DefaultTableModel tableModel;
    private JTable table;

    // construtor
    public ClientesControl(List<Clientes> clientes, DefaultTableModel tableModel, JTable table) {
        this.clientes = clientes;
        this.tableModel = tableModel;
        this.table = table;
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0); // Limpa todas as linhas existentes na tabela
        clientes = new ClientesDAO().listarTodos(); // Obtém os carros atualizados do banco de dados
        for (Clientes cliente : clientes) {
            // Adiciona os dados de cada carro como uma nova linha na tabela Swing
            tableModel.addRow(new Object[] { cliente.getCpf(), cliente.getNome(), cliente.getTelefone(), cliente.getCidade()});
        }
    }

    public void cadastrar(String cpf, String nome, String telefone, String cidade) {
        new ClientesDAO().cadastrar(cpf, nome, telefone, cidade);
        // Chama o método de cadastro no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após o cadastro
    }

    public void atualizar(String cpf, String nome, String telefone, String cidade) {
        new ClientesDAO().atualizar(cpf, nome, telefone, cidade); 
        // Chama o método de atualização no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a atualização
    }

    public void apagar(String cpf) {
        new ClientesDAO().apagar(cpf); 
        // Chama o método de exclusão no banco de dados
        atualizarTabela(); // Atualiza a tabela de exibição após a exclusão
    }
}
