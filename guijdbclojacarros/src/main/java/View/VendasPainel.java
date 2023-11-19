package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Controller.CarrosControl;
import Controller.CarrosDAO;
import Controller.ClientesControl;
import Controller.ClientesDAO;
import Controller.VendasControl;
import Controller.VendasDAO;

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
    private List<Vendas> vendas = new ArrayList<>();
    private List<Carros> carros;
    private List<Clientes> clientes;
    private int linhaSelecionada = -1;
    private JButton cadastrarButton, apagarButton, editarButton, atualizarButton;

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
        labelData = new JLabel("Data");
        labelCarro = new JLabel("Carro");
        labelValor = new JLabel("Valor");

        // botões
        cadastrarButton = new JButton("Comprar");
        apagarButton = new JButton("Apagar");
        editarButton = new JButton("Editar");
        atualizarButton = new JButton("Atualizar");

        // adicionar os componentes
        inputPanel.add(labelData);
        inputPanel.add(inputData);

        inputPanel.add(labelValor);
        inputPanel.add(inputValor);

        buttons.add(cadastrarButton);
        buttons.add(editarButton);
        buttons.add(apagarButton);
        buttons.add(atualizarButton);

        this.add(painel1);
        painel1.add(scrollPane, BorderLayout.CENTER);
        painel1.add(inputPanel, BorderLayout.NORTH);
        painel1.add(buttons, BorderLayout.SOUTH);

        carrosComboBox.addItem("Selecione um Carro");
        carros = new CarrosDAO().listarTodos();
        for (Carros carro : carros) {
            carrosComboBox.addItem(carro.getMarca() + " " + carro.getModelo());
        }

        clientesComboBox.addItem("Selecione um cliente");
        clientes = new ClientesDAO().listarTodos();
        // criar um método para atualizar o combobox
        for (Clientes cliente : clientes) {
            clientesComboBox.addItem(cliente.getNome() + " " + cliente.getCpf());
        }

        // adicionando o JComboBox ao painel
        inputPanel.add(clientesComboBox);
        inputPanel.add(carrosComboBox);

        // Criar o banco de dados
        new VendasDAO().criaTabela();

        // incluir os elementos do banco na criação do painel
        // atualizarTabela();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    inputData.setText((String) table.getValueAt(linhaSelecionada, 0));
                    inputCliente.setText((String) table.getValueAt(linhaSelecionada, 1));
                    inputValor.setText((String) table.getValueAt(linhaSelecionada, 2));
                    inputCarro.setText((String) table.getValueAt(linhaSelecionada, 3));
                }
            }
        });

        VendasControl operacoes = new VendasControl(vendas, tableModel, table);

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = inputData.getText(); // Obtém a data do JTextField
                String valor = inputValor.getText(); // Obtém o valor do JTextField
                String clienteSelecionado = (String) clientesComboBox.getSelectedItem(); // Obtém o cliente selecionado no JComboBox
                String carroSelecionado = (String) carrosComboBox.getSelectedItem(); // Obtém o carro selecionado no JComboBox
        
                if (data.isEmpty() || valor.isEmpty() || clienteSelecionado.equals("Selecione um cliente") || carroSelecionado.equals("Selecione um Carro")) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                } else {
                    // Aqui você pode separar o cliente e o carro selecionados para obter apenas os valores relevantes
                    String cliente = clienteSelecionado.split(" ")[0]; // Obtém o nome do cliente
                    String carro = carroSelecionado.split(" ")[0]; // Obtém a marca e modelo do carro
        
                    // Chama o método "cadastrar" do objeto operacoes com os valores obtidos
                    operacoes.cadastrar(data, cliente, valor, carro);
                    // Limpa os campos de entrada após a operação de cadastro
                    inputData.setText("");
                    inputValor.setText("");
                    clientesComboBox.setSelectedIndex(0); // Volta a seleção para o item padrão
                    carrosComboBox.setSelectedIndex(0); // Volta a seleção para o item padrão
        
                    JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
                }
            }
        });
        




    }
}
