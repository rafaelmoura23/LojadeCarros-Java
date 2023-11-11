package View;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
import Controller.ClientesControl;
import Controller.ClientesDAO;

import java.awt.*;

import Model.Carros;
import Model.Clientes;

public class ClientesPainel extends JPanel {
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
    private List<Clientes> clientes = new ArrayList<>();
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

        
        // Criar o banco de dados
        new ClientesDAO().criaTabela();

        // incluir os elementos do banco na criação do painel
        atualizarTabela();

        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    inputCpf.setText((String) table.getValueAt(linhaSelecionada, 0));
                    inputNome.setText((String) table.getValueAt(linhaSelecionada, 1));
                    inputTelefone.setText((String) table.getValueAt(linhaSelecionada, 2));
                    inputCidade.setText((String) table.getValueAt(linhaSelecionada, 3));
                }
            }
        });




        ClientesControl operacoes = new ClientesControl(clientes, tableModel, table);



        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputCpf.getText().isEmpty() || inputNome.getText().isEmpty() || inputTelefone.getText().isEmpty() || inputCidade.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ATENÇÃO! \nExistem campos em branco");
                } else {
                    // Chama o método "cadastrar" do objeto operacoes com os valores dos campos de
                    // entrada
                    operacoes.cadastrar(inputCpf.getText(), inputNome.getText(), inputTelefone.getText(),
                            inputCidade.getText());

                    JOptionPane.showMessageDialog(null, "Você Cadastrou o cliente com sucesso! ");

                    // Limpa os campos de entrada após a operação de cadastro
                    inputCpf.setText("");
                    inputNome.setText("");
                    inputTelefone.setText("");
                    inputCidade.setText("");
                }

            }
        });


        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputCpf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione algo para editar");
                } else{
                    operacoes.atualizar((String) table.getValueAt(linhaSelecionada, 0),
                            (String) table.getValueAt(linhaSelecionada, 1),
                            (String) table.getValueAt(linhaSelecionada, 2),
                            (String) table.getValueAt(linhaSelecionada, 3));

                    // Limpa os campos de entrada após a operação de atualização
                    inputCpf.setText("");
                    inputNome.setText("");
                    inputTelefone.setText("");
                    inputCidade.setText("");
                    JOptionPane.showMessageDialog(null, "Informação editada com Sucesso!");
                }
                
            }
        });


        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputCpf.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um cliente para apagar.");
                } else {
                    // Chama o método "apagar" do objeto operacoes com o valor do campo de entrada "placa"
                    operacoes.apagar(inputCpf.getText());
                    JOptionPane.showMessageDialog(null, "O Cliente foi deletado!");

                    // Limpa os campos de entrada após a operação de exclusão
                    inputCpf.setText("");
                    inputNome.setText("");
                    inputTelefone.setText("");
                    inputCidade.setText("");
                }
            }
        });
    }

    // atualizar Tabela de Carros com o Banco de Dados
    private void atualizarTabela() {
        // atualizar tabela pelo banco de dados
        tableModel.setRowCount(0);
        clientes = new ClientesDAO().listarTodos();
        for (Clientes cliente : clientes) {
            tableModel.addRow(new Object[] { cliente.getCpf(), cliente.getNome(), cliente.getTelefone(), cliente.getCidade()});
        }

    }

}
