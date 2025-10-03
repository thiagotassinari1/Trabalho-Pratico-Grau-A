package TrabalhoGrauA;

public class Produto {
    private int codigo;
    private String nome;
    private float preco;

    public Produto(int codigo, String nome, float preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\n" +
                "Nome: " + nome + "\n" +
                "Preço: R$ " +
                String.format("%.2f", preco) + "\n" +
                "-------------------------";
    }
}
