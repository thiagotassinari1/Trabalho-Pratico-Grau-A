package TrabalhoGrauA;

public class Data {

    public static int calcularTotalDiarias(int diaInicio, int diaFim) {
        // Caso 1: A reserva é dentro do mesmo mês (ex: do dia 5 ao 10)
        if (diaFim >= diaInicio) {
            return (diaFim - diaInicio) + 1;
        } 
        // Caso 2: A reserva "vira o mês" (ex: do dia 28 ao 3)
        else {
            int diasNoPrimeiroMes = (30 - diaInicio) + 1; // +1 para incluir o dia de início
            int diasNoSegundoMes = diaFim;
            return diasNoPrimeiroMes + diasNoSegundoMes;
        }
    }

    public static boolean diaEstaNoIntervalo(int diaConsulta, int diaInicio, int diaFim) {
        // Caso 1: Intervalo no mesmo mês (ex: 15 a 20)
        if (diaFim >= diaInicio) {
            return diaConsulta >= diaInicio && diaConsulta <= diaFim;
        }
        // Caso 2: Intervalo vira o mês (ex: 28 a 5)
        else {
            // A data está ou no final do primeiro mês (>=28) OU no começo do segundo (<=5)
            return diaConsulta >= diaInicio || diaConsulta <= diaFim;
        }
    }
}