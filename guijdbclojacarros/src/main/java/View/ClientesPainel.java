package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import Model.Clientes;

public class ClientesPainel extends JPanel{
    // atributos - componentes
    // campo de texto - JTextField
    private JTextField inputCpf;
    private JTextField inputNome;
    private JTextField inputTelefone;
    private JTextField inputCidade;
    // campo escrito - JLabel
    private JLabel labelCpf;
    private JLabel labelNome;
    private JLabel labelTelefone;
    private JLabel labelCidade;

    private DefaultTableModel tableModel; // lógica
    private JTable table; // visual
    private List<Clientes> clientesCompra = new ArrayList<>();
    private int linhaSelecionada = -1;
    private JButton cadastrarButton, atualizarButton, apagarButton, editarButton;

    public ClientesPainel() {
        JPanel painel1 = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();
        JPanel buttons = new JPanel();

        // construir a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Cpf");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Telefone");
        tableModel.addColumn("Cidade");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);

        // criar os componentes
        inputCpf = new JTextField(20);
        inputNome = new JTextField(20);
        inputTelefone = new JTextField(20);
        inputCidade = new JTextField(20);

        // criar os componentes - labels
        labelCpf = new JLabel("Cpf");
        labelNome = new JLabel("Nome");
        labelTelefone = new JLabel("Telefone");
        labelCidade = new JLabel("Cidade");

        // botões
        cadastrarButton = new JButton("Cadastrar");
        atualizarButton = new JButton("Atualizar");
        apagarButton = new JButton("Apagar");
        editarButton = new JButton("Editar");

        // adicionar os componentes
        inputPanel.add(labelCpf);
        inputPanel.add(inputCpf);
        inputPanel.add(labelNome);
        inputPanel.add(inputNome);
        inputPanel.add(labelTelefone);
        inputPanel.add(inputTelefone);
        inputPanel.add(labelCidade);
        inputPanel.add(inputCidade);

        buttons.add(cadastrarButton);
        buttons.add(editarButton);
        buttons.add(atualizarButton);
        buttons.add(apagarButton);

        this.add(painel1);
        painel1.add(scrollPane, BorderLayout.CENTER);
        painel1.add(inputPanel, BorderLayout.NORTH);
        painel1.add(buttons, BorderLayout.SOUTH);

        // entrada de dados
        // botões de eventos
        // tabela de carros

    }
    
}
