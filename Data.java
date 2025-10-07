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


    // true = conflito
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

    public static boolean reservasConflitam(int inicioReservaExistente, int fimReservaExistente, int inicioNovaReserva, int fimNovaReserva) {
        
        if (fimNovaReserva >= inicioNovaReserva) {
            for (int i = inicioNovaReserva; i <= fimNovaReserva; i++) {
                
                if (diaEstaNoIntervalo(i, inicioReservaExistente, fimReservaExistente)) {
                    return true;
                }
            }
        } else { 
            for (int i = inicioNovaReserva; i <= 30; i++) {
                if (diaEstaNoIntervalo(i, inicioReservaExistente, fimReservaExistente)) {
                    return true;
                }
            }
            for (int i = 1; i <= fimNovaReserva; i++) {
                if (diaEstaNoIntervalo(i, inicioReservaExistente, fimReservaExistente)) {
                    return true;
                }
            }
        }

        // se não conflitar com nada
        return false;
    }
}