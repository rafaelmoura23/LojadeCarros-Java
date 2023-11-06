package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

public class CarrosPainel extends JPanel{
    // atributos - componentes
    // campo de texto - JTextField
    private JTextField inputPlaca;
    private JTextField inputMarca;
    private JTextField inputModelo;
    private JTextField inputAno;
    private JTextField inputCor;
    private JTextField inputValor;
    //campo escrito - JLabel
    private JLabel labelPlaca;
    private JLabel labelMarca;
    private JLabel labelModelo;
    private JLabel labelAno;
    private JLabel labelCor;
    private JLabel labelValor;
    //private List<Agenda> agendas = new ArrayList<>();
    private int linhaSelecionada = -1;
    //tabela
    private DefaultTableModel tableModel; //lógica
    private JTable table; //visual
    //botoes
    private JButton cadastrarButton, atualizarButton, apagarButton; 


    // construtor(GUI-JPanel)
    public CarrosPainel() {
        // construir a tabela
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Placa");
        tableModel.addColumn("Marca");
        tableModel.addColumn("Modelo");
        tableModel.addColumn("Ano");
        tableModel.addColumn("Cor");
        tableModel.addColumn("Valor");
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // criar os componentes
        inputPlaca = new JTextField(10);
        inputMarca = new JTextField(10);
        inputModelo = new JTextField(10);
        inputAno = new JTextField(10);
        inputCor = new JTextField(10);
        inputValor = new JTextField(10);

        cadastrarButton = new JButton("Cadastrar");
        atualizarButton = new JButton("Atualizar");
        apagarButton = new JButton("Apagar");

        // adicionar os componentes
        JPanel inputPanel = new JPanel();
        inputPanel.add(labelPlaca);
        inputPanel.add(inputPlaca);
        inputPanel.add(labelMarca);
        inputPanel.add(inputMarca);
        inputPanel.add(labelModelo);
        inputPanel.add(inputModelo);
        inputPanel.add(labelAno);
        inputPanel.add(inputAno);
        inputPanel.add(labelCor);
        inputPanel.add(inputCor);
        inputPanel.add(labelValor);
        inputPanel.add(inputValor);

        inputPanel.add(cadastrarButton);
        inputPanel.add(atualizarButton);
        inputPanel.add(apagarButton);



        // set do layout
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);






        // entrada de dados
        // botões de eventos
        // tabela de carros
    }

    
}
