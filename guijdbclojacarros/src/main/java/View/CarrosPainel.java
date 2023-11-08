package View;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.events.MouseEvent;

import java.util.ArrayList;
import java.util.List;

import Model.Carros;
import Controller.CarrosControl;
import Controller.CarrosDAO;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class CarrosPainel extends JPanel {
    // atributos - componentes
    // campo de texto - JTextField
    private JTextField inputPlaca;
    private JTextField inputMarca;
    private JTextField inputModelo;
    private JTextField inputAno;
    private JTextField inputCor;
    private JTextField inputValor;
    // campo escrito - JLabel
    private JLabel labelPlaca;
    private JLabel labelMarca;
    private JLabel labelModelo;
    private JLabel labelAno;
    private JLabel labelCor;
    private JLabel labelValor;
    private DefaultTableModel tableModel; // lógica
    private JTable table; // visual
    private List<Carros> carrosVenda = new ArrayList<>();
    private int linhaSelecionada = -1;
    private JButton cadastrarButton, atualizarButton, apagarButton, editarButton;

    // construtor(GUI-JPanel)
    public CarrosPainel() {

        JPanel painel1 = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();
        JPanel buttons = new JPanel();

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
        scrollPane.setViewportView(table);

        // criar os componentes
        inputPlaca = new JTextField(10);
        inputMarca = new JTextField(10);
        inputModelo = new JTextField(10);
        inputAno = new JTextField(10);
        inputCor = new JTextField(10);
        inputValor = new JTextField(10);

        // criar os componentes - labels
        labelPlaca = new JLabel("Placa");
        labelMarca = new JLabel("Marca");
        labelModelo = new JLabel("Modelo");
        labelAno = new JLabel("Ano");
        labelCor = new JLabel("Cor");
        labelValor = new JLabel("Valor");

        // botões
        cadastrarButton = new JButton("Cadastrar");
        atualizarButton = new JButton("Atualizar");
        apagarButton = new JButton("Apagar");
        editarButton = new JButton("Editar");

        // adicionar os componentes
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

        buttons.add(cadastrarButton);
        buttons.add(editarButton);
        buttons.add(atualizarButton);
        buttons.add(apagarButton);

        this.add(painel1);
        painel1.add(scrollPane, BorderLayout.CENTER);
        painel1.add(inputPanel, BorderLayout.NORTH);
        painel1.add(buttons, BorderLayout.SOUTH);

        //Cria o banco de dados caso não tenha sido criado 
        new CarrosDAO().criaTabela();

        // incluindo elementos do banco na criação do painel
        atualizarTabela();

         // tratamento de Eventos
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                linhaSelecionada = table.rowAtPoint(evt.getPoint());
                if (linhaSelecionada != -1) {
                    inputPlaca.setText((String) table.getValueAt(linhaSelecionada, 0));
                    inputMarca.setText((String) table.getValueAt(linhaSelecionada, 1));
                    inputModelo.setText((String) table.getValueAt(linhaSelecionada, 2));
                    inputAno.setText((String) table.getValueAt(linhaSelecionada, 3));
                    inputCor.setText((String) table.getValueAt(linhaSelecionada, 4));
                    inputValor.setText((String) table.getValueAt(linhaSelecionada, 5));
                }
            }
        });

         // Cria um objeto operacoes da classe CarrosControl para executar operações no banco de dados
         CarrosControl operacoes = new CarrosControl(carros, tableModel, table);


}
}
