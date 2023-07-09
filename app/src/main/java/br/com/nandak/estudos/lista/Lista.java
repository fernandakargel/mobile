package br.com.nandak.estudos.lista;

public class Lista implements java.io.Serializable {

    private int id;
    private String nome;

    public Lista() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
