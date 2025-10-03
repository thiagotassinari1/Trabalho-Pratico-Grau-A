package TrabalhoGrauA;

public class Reserva {
    private int diaInicio;
    private int diaFim;
    private String cliente;
    private Quarto quarto;
    private char status;

    public Reserva(int diaInicio, int diaFim, String cliente, Quarto quarto, char status) {
        this.diaInicio = diaInicio;
        this.diaFim = diaFim;
        this.cliente = cliente;
        this.quarto = quarto;
        this.status = status;
    }

    public int getDiaInicio() {
        return diaInicio;
    }

    public int getDiaFim() {
        return diaFim;
    }

    public String getCliente() {
        return cliente;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public char getStatus() {
        return status;
    }

    public void setDiaInicio(int diaInicio) {
        this.diaInicio = diaInicio;
    }

    public void setDiaFim(int diaFim) {
        this.diaFim = diaFim;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "-------------------------" + "\n" +
                "Dia de início: " + diaInicio + "\n" +
                "Dia de fim: " + diaFim + "\n" +
                "Cliente: " + cliente + "\n" +
                "Status: " + status + "\n" +
                "Quarto: " + "\n" + quarto.toString();
    }
}
