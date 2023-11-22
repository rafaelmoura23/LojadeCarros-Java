package View;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.CarrosControl;
import Controller.CarrosDAO;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.*;

import Model.Carros;

import java.lang.Object;

public class CarrosPainel extends JPanel {
    // Atributos(componentes)
    private JButton cadastrar, apagar, editar;
    private JTextField carMarcaField, carModeloField, carAnoField, carPlacaField, carValorField;
    private List<Carros> carros;
    private JTable table;
    private DefaultTableModel tableModel;
    private int linhaSelecionada = -1;

    // Construtor(GUI-JPanel)
    public CarrosPainel() {
        super();

        // entrada de dados
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Marca"));
        carMarcaField = new JTextField(20);
        inputPanel.add(carMarcaField);
        inputPanel.add(new JLabel("Modelo"));
        carModeloField = new JTextField(20);
        inputPanel.add(carModeloField);
        inputPanel.add(new JLabel("Ano"));
        carAnoField = new JTextField(20);
        inputPanel.add(carAnoField);
        inputPanel.add(new JLabel("Placa"));
        carPlacaField = new JTextField(20);
        inputPanel.add(carPlacaField);
        inputPanel.add(new JLabel("Valor"));
        carValorField = new JTextField(20);
        inputPanel.add(carValorField);
        add(inputPanel);
        JPanel botoes = new JPanel();
        botoes.add(cadastrar = new JButton("Cadastrar"));
        botoes.add(editar = new JButton("Editar"));
        botoes.add(apagar = new JButton("Apagar"));

        cadastrar.setFont(new Font("Arial", Font.PLAIN, 16));
        cadastrar.setBackground(Color.green);
        editar.setFont(new Font("Arial", Font.PLAIN, 16));
        apagar.setFont(new Font("Arial", Font.PLAIN, 16));
        apagar.setBackground(Color.red);

        carAnoField.setFont(new Font("Arial", Font.PLAIN, 16));
        carMarcaField.setFont(new Font("Arial", Font.PLAIN, 16));
        carModeloField.setFont(new Font("Arial", Font.PLAIN, 16));
        carValorField.setFont(new Font("Arial", Font.PLAIN, 16));


        add(botoes);
        // tabela de carros
        JScrollPane jSPane = new JScrollPane();
        add(jSPane);
        tableModel = new DefaultTableModel(new Object[][] {},
                new String[] { "Marca", "Modelo", "Ano", "Placa", "Valor" });
        table = new JTable(tableModel);
        jSPane.setViewportView(table);
        table.setBackground(Color.LIGHT_GRAY);
        table.setFont(new Font("Arial", Font.PLAIN, 16));

        // Cria o banco de dados caso não tenha sido criado

        new CarrosDAO().criaTabela();

        // incluindo elementos do banco na criação do painel
        atualizarTabela();

        // tratamento de Eventos
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    carMarcaField.setText((String) table.getValueAt(linhaSelecionada, 0));
                    carModeloField.setText((String) table.getValueAt(linhaSelecionada, 1));
                    carAnoField.setText((String) table.getValueAt(linhaSelecionada, 2));
                    carPlacaField.setText((String) table.getValueAt(linhaSelecionada, 3));
                    carValorField.setText((String) table.getValueAt(linhaSelecionada, 4));
                }
            }
        });

        // Cria um objeto operacoes da classe CarrosControl para executar operações no bd
        CarrosControl operacoes = new CarrosControl(carros, tableModel, table);

        // Configura a ação do botão "cadastrar" para adicionar um novo registro no bd
        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if para verificar se os campos estão vazios
                if (carMarcaField.getText().isEmpty() || carModeloField.getText().isEmpty() || carAnoField.getText().isEmpty()
                    || carPlacaField.getText().isEmpty() || carValorField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ATENÇÃO! \nExistem campos em branco");
                } else {
                    // if para verificar se o campo ano contém apenas números, falta verificar length
                    if (!carAnoField.getText().matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "O campo 'Ano' deve conter apenas números.");
                    } else if(!carValorField.getText().matches("[0-9]+")){
                        // if para verificar se o campo valor contém apenas números, falta verificar length
                        JOptionPane.showMessageDialog(null, "O campo 'Valor' deve conter apenas números.");
                    } else{
                        // Chama o método "cadastrar" do objeto operacoes com os valores dos campos de entrada
                    operacoes.cadastrar(carMarcaField.getText(), carModeloField.getText(), carAnoField.getText(), carPlacaField.getText(), carValorField.getText());
                    // Limpa os campos de entrada após a operação de cadastro
                    carMarcaField.setText("");
                    carModeloField.setText("");
                    carAnoField.setText("");
                    carPlacaField.setText("");
                    carValorField.setText("");
                    }
                    
                }
            }
        });

        // Configura a ação do botão "editar" para atualizar um registro no banco de dados
        editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carPlacaField.getText().isEmpty()) {
                    // verifica se alguma row está selecionada
                    JOptionPane.showMessageDialog(null, "Selecione algo para editar");
                } else{
                    operacoes.atualizar(carMarcaField.getText(), carModeloField.getText(), carAnoField.getText(), carPlacaField.getText(), carValorField.getText());
                    // Limpa os campos de entrada após a operação de atualização
                    carMarcaField.setText("");
                    carModeloField.setText("");
                    carAnoField.setText("");
                    carPlacaField.setText("");
                    carValorField.setText("");
                    JOptionPane.showMessageDialog(null, "Informação editada com Sucesso!");
                }
                
            }
        });

        // Configura a ação do botão "apagar" para excluir um registro no banco de dados
        apagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // verifica se tem alguma linha selecionada
                if (carPlacaField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Selecione um carro para apagar.");
                } else {
                    int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja apagar os campos?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                    // Chama o método "apagar" do objeto operacoes com o valor do campo de entrada "placa"
                    operacoes.apagar(carPlacaField.getText());
                    JOptionPane.showMessageDialog(null, "O Carro " + carModeloField.getText() + " de placa " + carPlacaField.getText() + " foi deletado!");
                    // Limpa os campos de entrada após a operação de exclusão
                    carMarcaField.setText("");
                    carModeloField.setText("");
                    carAnoField.setText("");
                    carPlacaField.setText("");
                    carValorField.setText("");
                } else{
                    JOptionPane.showMessageDialog(null, "O carro não foi deletado!");
                }
            }
        }
        });

    }

    // atualizar Tabela de Carros com o Banco de Dados
    private void atualizarTabela() {
        // atualizar tabela pelo banco de dados
        tableModel.setRowCount(0);
        carros = new CarrosDAO().listarTodos();
        for (Carros carro : carros) {
            tableModel.addRow(new Object[] { carro.getMarca(), carro.getModelo(), carro.getAno(), carro.getPlaca(), carro.getValor() });
        }

    }

}