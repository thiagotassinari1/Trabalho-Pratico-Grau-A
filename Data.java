package TrabalhoGrauA;

public class Data {

    public static int calcularTotalDiarias(int diaInicio, int diaFim) {
        if (diaFim >= diaInicio) {
            return (diaFim - diaInicio) + 1;
        } else {
            int diasNoPrimeiroMes = (30 - diaInicio) + 1;
            int diasNoSegundoMes = diaFim;
            return diasNoPrimeiroMes + diasNoSegundoMes;
        }
    }

    public static boolean diaEstaNoIntervalo(int diaConsulta, int diaInicio, int diaFim) {
        if (diaFim >= diaInicio) {

            if (diaConsulta >= diaInicio && diaConsulta <= diaFim) {
                return true;
            } else {
                return false;
            }

        } else {
            if (diaConsulta >= diaInicio || diaConsulta <= diaFim) {
                return true;
            } else {
                return false;
            }
        }
    }
}