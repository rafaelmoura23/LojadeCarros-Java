package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CarrosControl;
import Controller.CarrosDAO;
import Controller.ClientesDAO;

import java.awt.*;

import Model.Carros;
import Model.Vendas;
import Model.Clientes;


public class VendasPainel extends JPanel {
    // atributos - componentes
    // campo de texto - JTextField
    private JTextField inputData;
    private JTextField inputCliente;
    private JTextField inputValor;
    private JTextField inputCarro;

    // campo escrito - JLabel
    private JLabel labelData;
    private JLabel labelCliente;
    private JLabel labelValor;
    private JLabel labelCarro;


    private DefaultTableModel tableModel; // lógica
    private JTable table; // visual
    //private List<Vendas> vendas = new ArrayList<>();
    private List<Carros> carros;
    private List<Clientes> clientes;
    private int linhaSelecionada = -1;
    private JButton cadastrarButton, apagarButton, editarButton;


    JComboBox<String> carrosComboBox;
    JComboBox<String> clientesComboBox;

    public VendasPainel() {
        JPanel painel1 = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();
        JPanel buttons = new JPanel();

        carrosComboBox = new JComboBox<>();
        clientesComboBox = new JComboBox<>();

        // construir a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Data");
        tableModel.addColumn("Carro");
        tableModel.addColumn("Valor");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);

        // criar os componentes
        inputCliente = new JTextField(20);
        inputData = new JTextField(10);
        inputCarro = new JTextField(20);
        inputValor = new JTextField(10);

        // criar os componentes - labels
        labelCliente = new JLabel("Cliente");
        labelData = new JLabel("Data");
        labelCarro = new JLabel("Carro");
        labelValor = new JLabel("Valor");

        // botões
        cadastrarButton = new JButton("Cadastrar");
        apagarButton = new JButton("Apagar");
        editarButton = new JButton("Editar");

        // adicionar os componentes
        inputPanel.add(labelCliente);
        inputPanel.add(inputCliente);
        inputPanel.add(labelData);
        inputPanel.add(inputData);

        inputPanel.add(labelValor);
        inputPanel.add(inputValor);

        buttons.add(cadastrarButton);
        buttons.add(editarButton);
        buttons.add(apagarButton);

        this.add(painel1);
        painel1.add(scrollPane, BorderLayout.CENTER);
        painel1.add(inputPanel, BorderLayout.NORTH);
        painel1.add(buttons, BorderLayout.SOUTH);

        carros = new CarrosDAO().listarTodos();
        for (Carros carro : carros) {
            carrosComboBox.addItem(carro.getMarca() + " " + carro.getModelo());
        }

        clientes = new ClientesDAO().listarTodos();
        for (Clientes cliente : clientes) {
            clientesComboBox.addItem(cliente.getNome() + " " + cliente.getCpf());
        }

        inputPanel.add(clientesComboBox);
        inputPanel.add(carrosComboBox);
    }
}
