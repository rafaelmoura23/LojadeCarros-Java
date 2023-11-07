package Model;

public class Vendas {

    // atributos
    private String data;
    private String cliente;
    private double valor;
    private String tipoCarro;
    
    // construtor
    public Vendas(String data, String cliente, double valor, String tipoCarro) {
        this.data = data;
        this.cliente = cliente;
        this.valor = valor;
        this.tipoCarro = tipoCarro;
    }

    // getters and setters
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipoCarro() {
        return tipoCarro;
    }

    public void setTipoCarro(String tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

}
