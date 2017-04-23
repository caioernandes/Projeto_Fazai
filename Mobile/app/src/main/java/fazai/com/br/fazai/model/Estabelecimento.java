package fazai.com.br.fazai.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Estabelecimento {
    @SerializedName("id")
    public int id;
    @SerializedName("cnpj")
    public String cnpj;
    @SerializedName("nome")
    public String nome;
    @SerializedName("razaoSocial")
    public String razaoSocial;
    @SerializedName("especialidade")
    public String especialidade;
    @SerializedName("endereco")
    public Endereco endereco;
    @SerializedName("foto")
    public String foto;
    @SerializedName("telefone")
    public String telefone;
    @SerializedName("rating")
    public int rating;
    @SerializedName("cardapio")
    public List<Cardapio> cardapioList;

    public Estabelecimento() {
        endereco = new Endereco();
        cardapioList = new ArrayList<>();
    }

    public Estabelecimento(int id, String cnpj, String nome, String razaoSocial, String especialidade, String foto, List<Cardapio> cardapios, String telefone) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.especialidade = especialidade;
        this.foto = foto;
        this.cardapioList = cardapios;
        this.telefone = telefone;
    }
}
