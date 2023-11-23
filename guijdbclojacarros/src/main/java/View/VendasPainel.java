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

import Controller.CarrosDAO;
import Controller.ClientesDAO;
import Controller.VendasControl;
import Controller.VendasDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private JLabel labelValor;

    private DefaultTableModel tableModel; // lógica
    private JTable table; // visual
    private List<Vendas> vendas = new ArrayList<>();
    private List<Carros> carros;
    private List<Clientes> clientes;
    private int linhaSelecionada = -1;
    private JButton cadastrarButton, apagarButton, atualizarButton;

    JComboBox<String> carrosComboBox;
    JComboBox<String> clientesComboBox;
    JComboBox<String> placasComboBox;


    public VendasPainel() {
        JPanel painel1 = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();
        JPanel buttons = new JPanel();

        carrosComboBox = new JComboBox<>();
        carrosComboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        clientesComboBox = new JComboBox<>();
        clientesComboBox.setFont(new Font("Arial", Font.PLAIN, 16));

        // placasComboBox = new JComboBox<>();

        // construir a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Data");
        tableModel.addColumn("Carro");
        tableModel.addColumn("Valor");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);
        table.setBackground(Color.LIGHT_GRAY);
        table.setFont(new Font("Arial", Font.PLAIN, 16));

        // criar os componentes
        // inputCliente = new JTextField(20);

        inputData = new JTextField(10);
        inputData.setFont(new Font("Arial", Font.PLAIN, 16));

        inputCarro = new JTextField(20);

        inputCliente = new JTextField(20);

        inputValor = new JTextField(10);
        inputValor.setFont(new Font("Arial", Font.PLAIN, 16));

        // criar os componentes - labels
        labelData = new JLabel("Data");
        labelData.setFont(new Font("Arial", Font.PLAIN, 16));

        labelValor = new JLabel("Valor");
        labelValor.setFont(new Font("Arial", Font.PLAIN, 16));

        // botões
        cadastrarButton = new JButton("Comprar");
        cadastrarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        cadastrarButton.setBackground(Color.green);

        apagarButton = new JButton("Apagar");
        apagarButton.setFont(new Font("Arial", Font.PLAIN, 16));
        apagarButton.setBackground(Color.red);
        atualizarButton = new JButton("Atualizar");
        atualizarButton.setFont(new Font("Arial", Font.PLAIN, 16));

        // adicionar os componentes
        inputPanel.add(labelData);
        inputPanel.add(inputData);

        inputPanel.add(labelValor);
        inputPanel.add(inputValor);

        buttons.add(cadastrarButton);
        buttons.add(apagarButton);
        buttons.add(atualizarButton);

        this.add(painel1);
        painel1.add(scrollPane, BorderLayout.CENTER);
        painel1.add(inputPanel, BorderLayout.NORTH);
        painel1.add(buttons, BorderLayout.SOUTH);


        // comboBox guardando todos os carros
        carrosComboBox.addItem("Selecione um Carro");
        carros = new CarrosDAO().listarTodos();
        for (Carros carro : carros) {
            carrosComboBox.addItem(carro.getMarca() + " " + carro.getModelo());
        }

        // comboBox guardando todos dos clientes
        clientesComboBox.addItem("Selecione um cliente");
        clientes = new ClientesDAO().listarTodos();
        // criar um método para atualizar o combobox
        for (Clientes cliente : clientes) {
            clientesComboBox.addItem(cliente.getNome());
        }

        // carros = new CarrosDAO().listarTodos();
        // for (Carros carros1 : carros) {
        //     placasComboBox.addItem(carros1.getPlaca());
        // }



        // adicionando o JComboBox ao Jpainel
        inputPanel.add(clientesComboBox);
        inputPanel.add(carrosComboBox);

        // Criar o banco de dados
        new VendasDAO().criaTabela();

        // incluir os elementos do banco na criação do painel
        atualizarTabela();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    inputData.setText((String) table.getValueAt(linhaSelecionada, 1));
                    inputValor.setText((String) table.getValueAt(linhaSelecionada, 3));
                }
            }
        });

        VendasControl operacoes = new VendasControl(vendas, tableModel, table);

        // evento para o botão cadastrar
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String data = inputData.getText();
                String valor = inputValor.getText();
                String clienteSelecionado = (String) clientesComboBox.getSelectedItem(); // pegar o cliente selecionado no ComboBox
                String carroSelecionado = (String) carrosComboBox.getSelectedItem(); // pegar o carro selecionado noComboBox
                // String placaSelecionada = (String) placasComboBox.getSelectedItem();
                                                            

                // lógica para os campos vazios, incluindo a combobox não selecionada
                if (data.isEmpty() || valor.isEmpty() || clienteSelecionado.equals("Selecione um cliente") || carroSelecionado.equals("Selecione um Carro")) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                } else {
                    if (!valor.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "O campo 'Valor' deve conter apenas números.");
                    } else {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        dateFormat.setLenient(false);

                        try {
                            // Tentar fazer o parse da data para verificar se é uma data válida
                            Date parsedDate = dateFormat.parse(data);
                            if (!data.equals(dateFormat.format(parsedDate))) {
                                throw new ParseException("Formato inválido", 0);
                            }
                            operacoes.cadastrar(data, clienteSelecionado, valor, carroSelecionado);
                            inputData.setText("");
                            inputValor.setText("");
                            clientesComboBox.setSelectedIndex(0);
                            carrosComboBox.setSelectedIndex(0);
                            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");
                            // JOptionPane.showMessageDialog(null, "placa = " + placaSelecionada);

                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null,
                                    "Formato de data inválido. Utilize o formato dd/mm/yyyy.");
                        }
                    }
                }
            }
        });

        // Configura a ação do botão "apagar" para excluir um registro no banco de dados
        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String carroSelecionado = (String) carrosComboBox.getSelectedItem();
                if (carroSelecionado == null || carroSelecionado.isEmpty() || carroSelecionado.equals("Selecione um Carro")) {
                    JOptionPane.showMessageDialog(null, "Selecione uma venda para apagar.");
                } else {
                    int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja apagar os campos?",
                            "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        // Chama o método "apagar" do objeto operacoes com o valor do campo de entrada "placa"
                        operacoes.apagar(carroSelecionado);
                        JOptionPane.showMessageDialog(null, "A venda foi deletada!");
                        // Limpa os campos de entrada após a operação de exclusão
                        inputData.setText("");
                        inputValor.setText("");
                        clientesComboBox.setSelectedIndex(0);
                        carrosComboBox.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "A venda não foi deletada");
                    }
                }
            }
        });

        

        // atualizar as comboBox com os valores atuais
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarComboBoxClientes();
                atualizarComboBoxCarros();
            }
        });

    }

    // atualizar Tabela de Carros com o Banco de Dados
    private void atualizarTabela() {
        // atualizar tabela pelo banco de dados
        tableModel.setRowCount(0);
        vendas = new VendasDAO().listarTodos();
        for (Vendas venda : vendas) {
            tableModel.addRow(
                    new Object[] { venda.getCliente(), venda.getData(), venda.getCarro(), venda.getValor() });
        }

    }

    // Método para atualizar ComboBox de Clientes
    private void atualizarComboBoxClientes() {
        clientesComboBox.removeAllItems();
        clientesComboBox.addItem("Selecione um cliente");
        clientes = new ClientesDAO().listarTodos();
        for (Clientes cliente : clientes) {
            clientesComboBox.addItem(cliente.getNome());
        }
    }

    // Método para atualizar ComboBox de Carros
    private void atualizarComboBoxCarros() {
        carrosComboBox.removeAllItems();
        carrosComboBox.addItem("Selecione um Carro");
        carros = new CarrosDAO().listarTodos();
        for (Carros carro : carros) {
            carrosComboBox.addItem(carro.getMarca() + " " + carro.getModelo());
        }
    }
}
