package TrabalhoGrauA;

import java.util.Scanner;

public class Main {

    public static void consultaDisponibilidade(Scanner sc, Pousada pousada) {
        System.out.print("Informe uma data para consulta: ");
        int data = sc.nextInt();

        System.out.print("Informe um número de quarto para consulta (101 a 110): ");
        int quarto = sc.nextInt();

        pousada.consultaDisponibilidade(data, quarto);
    }

    public static void consultaReserva(Scanner sc, Pousada pousada) {
        System.out.println("Informe uma data e/ou nome do cliente e/ou número do quarto para consulta:");
        System.out.println("Pressione Enter para ignorar um campo.");

        System.out.print("Informe a data (1 a 30) (Digite 0 para ignorar): ");
        int data = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Informe o nome do cliente (Pressione Enter para ignorar): ");
        String cliente = sc.nextLine().trim();

        System.out.print("Informe o número do quarto (Digite 0 para ignorar): ");
        int quarto = sc.nextInt();
        sc.nextLine(); 

        pousada.consultaReserva(data, cliente, quarto);
    }

    public static void realizaReserva(Scanner sc, Pousada pousada) {
        System.out.println("Informe uma data inical, data final, nome do cliente e número do quarto para reserva:");
        System.out.print("Data inicial (1 a 30): ");
        int diaInicio = sc.nextInt();

        System.out.print("Data final (1 a 30): ");
        int diaFim = sc.nextInt();

        System.out.print("Nome do cliente: ");
        sc.nextLine();
        String cliente = sc.nextLine().trim();

        System.out.print("Número do quarto (101 a 110): ");
        int quarto = sc.nextInt();

        pousada.realizaReserva(diaInicio, diaFim, cliente, quarto);
    }

    public static void cancelaReserva(Scanner sc, Pousada pousada) {
        System.out.print("Informe o nome do cliente para cancelar a reserva: ");
        sc.nextLine();
        String cliente = sc.nextLine().trim();

        pousada.cancelaReserva(cliente);
    }

    public static void realizaCheckIn(Scanner sc, Pousada pousada) {
        System.out.print("Informe o nome do cliente para fazer o check-in: ");
        sc.nextLine();
        String cliente = sc.nextLine().trim();

        pousada.realizaCheckIn(cliente);
    }

    public static void registrarConsumo(Pousada pousada, Scanner sc) {
        System.out.print("Informe o nome do cliente para registrar o consumo: ");
        sc.nextLine();
        String cliente = sc.nextLine().trim();

        Reserva reservaCheckIn = pousada.buscarReservaCheckIn(cliente);

        if (reservaCheckIn == null) {
            System.out.println("\nNão há check-in ativo para esse cliente.");
        } else {
            System.out.println("\nSelecione um produto para registrar o consumo:");
            pousada.listarProdutos();
            System.out.println("0 - Voltar ao menu principal.");

            int escolha = -1;

            while (escolha != 0) {
                System.out.print("\nEscolha: ");
                escolha = sc.nextInt();
                sc.nextLine();

                if (escolha > 0) {
                    pousada.registrarConsumo(reservaCheckIn, escolha);

                    System.out.println("\nSelecione outro produto para registrar o consumo: ");
                    pousada.listarProdutos();
                    System.out.println("0 - Voltar ao menu principal.");
                } else if (escolha != 0) {
                    System.out.println("Opção desconhecida.");
                }
            }

            System.out.println("Voltando ao menu principal.");
        }

    }

    public static void realizaCheckOut(Scanner sc, Pousada pousada) {
        System.out.print("Informe o nome do cliente para fazer o check-out: ");
        sc.nextLine();
        String cliente = sc.nextLine().trim();

        pousada.realizaCheckOut(cliente);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Pousada pousada = new Pousada();

        System.out.println("Carregando dados iniciais...");

        pousada.carregarDados();

        System.out.println("Carregamento de dados concluído.");

        int escolha = -1;
        while (escolha != 0) {
            System.out.println("\nMenu de opções:");
            System.out.println("1 - Consultar disponibilidade;");
            System.out.println("2 - Consultar reserva;");
            System.out.println("3 - Realizar reserva;");
            System.out.println("4 - Cancelar reserva;");
            System.out.println("5 - Realizar check-in;");
            System.out.println("6 - Realizar check-out;");
            System.out.println("7 - Registrar consumo;");
            System.out.println("8 - Salvar;");
            System.out.println("0 - Sair.");
            System.out.print("\nEscolha uma opção: ");
            escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    consultaDisponibilidade(sc, pousada);
                    break;
                case 2:
                    consultaReserva(sc, pousada);
                    break;
                case 3:
                    realizaReserva(sc, pousada);
                    break;
                case 4:
                    cancelaReserva(sc, pousada);
                    break;
                case 5:
                    realizaCheckIn(sc, pousada);
                    break;
                case 6:
                    realizaCheckOut(sc, pousada);
                    break;
                case 7:
                    registrarConsumo(pousada, sc);
                    break;
                case 8:
                    pousada.salvaDados();
                    break;
                case 0:
                    System.out.println("Saindo do programa.");
                    pousada.salvaDados();
                    break;
                default:
                    System.out.print("Opção desconhecida.");
                    break;
            }
        }

        sc.close();
    }
}