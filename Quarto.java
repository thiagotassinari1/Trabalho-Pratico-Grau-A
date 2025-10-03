package TrabalhoGrauA;

import java.util.ArrayList;

public class Quarto {
    private int numero;
    private char categoria;
    private float diaria;
    private ArrayList<Integer> consumo;

    public Quarto(int numero, char categoria, float diaria) {
        this.numero = numero;
        this.categoria = categoria;
        this.diaria = diaria;
        this.consumo = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public char getCategoria() {
        return categoria;
    }

    public float getDiaria() {
        return diaria;
    }

    public void adicionaConsumo(int codigo) {
        this.consumo.add(codigo);
    }

    public ArrayList<Integer> listaConsumo() {
        return consumo;
    }

    public double valorTotalConsumo(ArrayList<Produto> produtos) {
        double total = 0.0;

        for (int i = 0; i < this.consumo.size(); i++) {
            int codigo = this.consumo.get(i);

            for (int j = 0; j < produtos.size(); j++) {
                Produto produto = produtos.get(j);
                if (produto.getCodigo() == codigo) {
                    total += produto.getPreco();
                    break;
                }
            }
        }
        return total;
    }

    public void limpaConsumo() {
        this.consumo.clear();
    }

    @Override
    public String toString() {
        return  "-------------------------" + "\n" +
                "Número: " + numero + "\n" +
                "Categoria: " + categoria + "\n" +
                "Diária: R$ " +
                String.format("%.2f", diaria) + "\n" +
                "-------------------------";
    }

}
