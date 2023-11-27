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

## 2. Controller
## 3. Model
## 4. View
