package br.com.nandak.estudos.palavra;

public class Palavra implements java.io.Serializable {

    private int id, id_lista;
    private String palavra, descricao, pronuncia, exemplo;

    public Palavra() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPronuncia() {
        return pronuncia;
    }

    public void setPronuncia(String pronuncia) {
        this.pronuncia = pronuncia;
    }

     public String getExemplo() {
        return exemplo;
    }

    public void setExemplo(String exemplo) {
        this.exemplo = exemplo;
    }
}
