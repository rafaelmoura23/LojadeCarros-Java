package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Connection.ConnectionFactory;
import Model.Carros;

public class CarrosDAO {

    // atributos
    private Connection connection;
    private List<Carros> carros;

    // construtor
    public CarrosDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    // criar Tabela
    public void criaTabela() {

        String sql = "CREATE TABLE IF NOT EXISTS carros_lojacarros (MARCA VARCHAR(255),MODELO VARCHAR(255),ANO VARCHAR(255),PLACA VARCHAR(255) PRIMARY KEY, VALOR VARCHAR(255))";
        //consulta sql para criar uma nova tabela
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabela criada com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela: " + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(this.connection);
        }
    }

    // Listar todos os valores cadastrados
    public List<Carros> listarTodos() {
        PreparedStatement stmt = null; // Declaração do objeto PreparedStatement para executar a consulta
        ResultSet rs = null; // Declaração do objeto ResultSet para armazenar os resultados da consulta
        carros = new ArrayList<>(); // Cria uma lista para armazenar os carros recuperados do banco de dados
    
        try {
            stmt = connection.prepareStatement("SELECT * FROM carros_lojacarros"); 
            // consulta SQL para selecionar todos as rows da tabela
            rs = stmt.executeQuery(); 
            // Executa a consulta e armazena os resultados no ResultSet
    
            while (rs.next()) {
                // Para cada registro no ResultSet, cria um objeto Carros com os valores do registro
                Carros carro = new Carros(
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("ano"),
                    rs.getString("placa"),
                    rs.getString("valor")
                );
                carros.add(carro); // Adiciona o objeto Carros à lista de carros
            }
        } catch (SQLException ex) {
            System.out.println(ex); // Em caso de erro durante a consulta, imprime o erro
        } finally {
            ConnectionFactory.closeConnection(connection, stmt, rs); // Fecha todas as conexões aberta anteriormente
        }
        return carros; // Retorna a lista de carros recuperados do banco de dados
    }

    // Cadastrar Carro no banco de dados
    public void cadastrar(String marca, String modelo, String ano, String placa, String valor) {
        PreparedStatement stmt = null;
        // Consulta SQL para cadastrar na tabela
        String sql = "INSERT INTO carros_lojacarros (marca, modelo, ano, placa, valor) VALUES (?, ?, ?, ?, ?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, marca);
            stmt.setString(2, modelo);
            stmt.setString(3, ano);
            stmt.setString(4, placa);
            stmt.setString(5, valor);
            stmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso");
            JOptionPane.showMessageDialog(null, "Você Cadastrou o carro com sucesso✅");
        } catch (SQLException e) {
            //exception para caso o usuario tentar cadastrar um carro com uma placa que já existe no banco de dados
            if (e.getSQLState().equals("23505")) {
                JOptionPane.showMessageDialog(null, "\"Erro: A placa inserida já existe na tabela.\"");
            } else {
                throw new RuntimeException("Erro ao inserir dados no banco de dados.", e);
            }
        } finally {
            ConnectionFactory.closeConnection(connection,stmt);
        }
    }

    //Atualizar dados no banco
    public void atualizar(String marca, String modelo, String ano, String placa, String valor) {
        PreparedStatement stmt = null;
        // Consulta SQL para atualizar dados pela placa
        String sql = "UPDATE carros_lojacarros SET marca = ?, modelo = ?, ano = ?, valor = ? WHERE placa = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, marca);
            stmt.setString(2, modelo);
            stmt.setString(3, ano);
            stmt.setString(4, valor);
            // placa é chave primaria não pode ser alterada, tem que estar de acordo com a ordem da consulta
            stmt.setString(5, placa);
            stmt.executeUpdate();
            System.out.println("Dados atualizados com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt); //independente de qualquer coisa, fecha as conexões
        }
    }

    // Apagar dados do banco
    public void apagar(String placa) {
        PreparedStatement stmt = null;
        // SQl para apagar dados pela placa
        String sql = "DELETE FROM carros_lojacarros WHERE placa = ?"; // SQL para deletar uma row pela placa
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, placa);
            stmt.executeUpdate(); // Executa a instrução SQL
            System.out.println("Dado apagado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao apagar dados no banco de dados.", e);
        } finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

}