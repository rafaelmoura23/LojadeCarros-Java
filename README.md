<h1 align="center"> Loja de Carros em JAVA 🚗 </h1> 


<h1 align="center"> I. Manual de utilização para o Usuário: <h2/>
  
### 1. Ao iniciar o aplicativo, a tela abaixo será visualizada, para navegar para as próximas páginas, clique no botão `Go to the Car store!`
<img src="img\initial_panel.gif" >

### 2. Na tela abaixo está sendo mostrada a `página de cadastro de carros`. Quando todos os campos estão preenchidos e o botão `cadastrar` é clicado, o carro aparece na tabela, sendo cadastrado também em um banco de dados. `obs: preencha os dados corretamente de acordo com as validações.`
<img src="img\carros.gif" >

### 3. Na tela de `cadastro de clientes`, quando todos os campos foram preenchidos corretamente e o botão `cadastrar` é clicado, o cliente aparece na tabela também sendo cadastrado no banco de dados. `obs: preencha os dados corretamente de acordo com as validações.`
<img src="img\clientes.gif">

### 4. Na tela de `transações/vendas` podemos efetuar a `venda de um carro para determinado cliente`, também é necessário colocar uma data de acordo com as normas dd/mm/yyyy, e inserir um valor.
<img src="img\vendas.gif">

<h1 align="center"> II. Documentação do Código: <h2/>

<h2> Connection </h2>

  - Este código Java contém uma classe ConnectionFactory que fornece métodos para gerenciar conexões com um banco de dados PostgreSQL. 

  - Atributos:
    - Define atributos estáticos para a URL do banco de dados, nome de usuário e senha para se conectar ao banco de dados PostgreSQL.

  - Método getConnection():
    - Retorna uma conexão ativa com o banco de dados usando o DriverManager e os detalhes de URL, usuário e senha fornecidos.
    - Lança uma exceção RuntimeException se ocorrer um erro ao obter a conexão.
  
  - Método closeConnection(Connection connection):
    - Fecha a conexão com o banco de dados, se estiver aberta.
    - Trata exceções associadas ao fechamento da conexão, mas apenas imprime a rastreabilidade do erro.

  - Método closeConnection(Connection connection, PreparedStatement stmt):
    - Além de fechar a conexão, também fecha um objeto PreparedStatement associado, se existir.
    - Trata exceções associadas ao fechamento da conexão e do PreparedStatement.
  
  - Método closeConnection(Connection connection, PreparedStatement stmt, ResultSet rs):
    - Adicionalmente ao fechamento da conexão e do PreparedStatement, fecha um ResultSet, se existir.
    - Trata exceções associadas ao fechamento da conexão, do PreparedStatement e do ResultSet.

  - Resumidamente, essa classe oferece métodos estáticos para obter conexões com o banco de dados PostgreSQL, bem como para fechar conexões, declarações preparadas (PreparedStatement) e resultados (ResultSet). Essa abordagem visa a reutilização de código e o encapsulamento de lógica de gerenciamento de conexão, facilitando o desenvolvimento e manutenção de aplicações Java que interagem com bancos de dados PostgreSQL.

<h2> Classe DAO </h2>
  - A classe DAO atua como uma camada de acesso a dados para interagir com um banco de dados relacionado aos registros de carros. Aqui está um resumo das principais funcionalidades dessa classe:

  - Atributos:
    - connection: Armazena a conexão com o banco de dados através da ConnectionFactory.
    - carros: Lista para armazenar objetos do tipo Carros.

  - Método criaTabela():
    - Cria uma tabela no banco de dados usando SQL.
    - Usa uma instrução CREATE TABLE para criar a tabela carros_lojacarros se ela não existir.
 
  - Método listarTodos():
    - Recupera todos os registros da tabela carros_lojacarros do banco de dados.
    - Utiliza uma consulta SQL para selecionar todos os registros.
    - Cria objetos Carros com os dados recuperados e os armazena em uma lista para posterior uso.
  
  - Método cadastrar(String marca, String modelo, String ano, String placa, String valor):
    - Insere um novo registro na tabela carros_lojacarros do banco de dados.
    - Utiliza uma instrução INSERT INTO com parâmetros para realizar a inserção.
    - Controla exceções para evitar inserções com placas duplicadas.
  
  - Método atualizar(String marca, String modelo, String ano, String placa, String valor):
    - Atualiza um registro na tabela carros_lojacarros baseado na placa.
    - Utiliza uma instrução UPDATE com parâmetros para modificar os dados do carro.
 
  - Método apagar(String placa):
    - Remove um registro da tabela carros_lojacarros com base na placa fornecida.
    - Utiliza uma instrução DELETE FROM para excluir o registro.
  
  - Essa classe encapsula a lógica para manipular o banco de dados relacionado aos registros de carros (Carros) por meio de operações de inserção, atualização, exclusão e recuperação de dados da tabela carros_lojacarros. Além disso, controla as conexões com o banco de dados para garantir sua abertura e fechamento adequados.

<h2> Controller </h2>

  - A classe Control controla as interações entre a interface de usuário (UI) e operações relacionadas a carros armazenados em um banco de dados. 

  - Atributos:
    - carros: Armazena uma lista de objetos do tipo Carros.
    - tableModel: Representa o modelo da tabela Swing para exibir os dados.
    - table: É a tabela Swing onde os dados dos carros são mostrados.

  - Método atualizarTabela():
    - Limpa as linhas existentes na tabela.
    - Obtém os dados atualizados dos carros a partir do banco de dados usando um CarrosDAO.
    - Popula a tabela Swing com os dados dos carros, adicionando cada carro como uma nova linha na tabela.

  - Método cadastrar(String marca, String modelo, String ano, String placa, String valor):
    - Chama o método de cadastro no banco de dados usando um CarrosDAO.
    - Após o cadastro, atualiza a tabela de exibição chamando o método atualizarTabela().
  
  - Método atualizar(String marca, String modelo, String ano, String placa, String valor):
    - Chama o método de atualização no banco de dados usando um CarrosDAO.
    - Após a atualização, atualiza a tabela de exibição chamando o método atualizarTabela().
  
  - Método apagar(String placa):
    - Chama o método de exclusão no banco de dados usando um CarrosDAO.
    - Após a exclusão, atualiza a tabela de exibição chamando o método atualizarTabela().
  
  - Em resumo, a classe Control atua como um controlador entre a interface do usuário e as operações de manipulação de dados no banco de dados. Ela utiliza um objeto para realizar operações de cadastro, atualização e exclusão no banco de dados, ao mesmo tempo que atualiza a tabela Swing com os dados atualizados após cada operação.


<h2> Model </h2>
<h2> View </h2>
