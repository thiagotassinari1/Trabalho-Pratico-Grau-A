package TrabalhoGrauA;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Pousada {
    private String nome;
    private String contato;
    private ArrayList<Quarto> quartos;
    private ArrayList<Reserva> reservas;
    private ArrayList<Produto> produtos;

    public Pousada() {
        this.nome = "Sem nome";
        this.contato = "Sem contato";
        this.quartos = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.produtos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getContato() {
        return contato;
    }

    public void carregarDados() {

        String arquivoPousada = "C:\\Users\\User\\Desktop\\JavaUnisinos\\SegundoSemestre\\TrabalhoGrauA\\pousada.txt";
        String arquivoQuartos = "C:\\Users\\User\\Desktop\\JavaUnisinos\\SegundoSemestre\\TrabalhoGrauA\\quarto.txt";
        String arquivoProdutos = "C:\\Users\\User\\Desktop\\JavaUnisinos\\SegundoSemestre\\TrabalhoGrauA\\produto.txt";
        String arquivoReservas = "C:\\Users\\User\\Desktop\\JavaUnisinos\\SegundoSemestre\\TrabalhoGrauA\\reserva.txt";

        // Carregar pousada
        try (FileReader reader = new FileReader(arquivoPousada);
                Scanner sc = new Scanner(reader).useLocale(Locale.ENGLISH).useDelimiter(";|\\n")) {

            // Descarta a linha do cabeçalho
            sc.nextLine();

            if (sc.hasNext()) {
                nome = sc.next();
                contato = sc.next();
                System.out.println("Dados da pousada lidos com sucesso!");

            } else {
                System.out.println("Arquivo de pousada vazio. Não foi possível carregar os dados.");

            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }

        // Carregar quartos
        try (FileReader reader = new FileReader(arquivoQuartos);
                Scanner sc = new Scanner(reader).useLocale(Locale.ENGLISH).useDelimiter(";|\\n")) {

            // Descarta a linha do cabeçalho
            sc.nextLine();

            while (sc.hasNext()) {
                int numero = sc.nextInt();
                char categoria = sc.next().charAt(0);
                float diaria = sc.nextFloat();
                String consumoStr = sc.next();

                // Cria o quarto
                Quarto novoQuarto = new Quarto(numero, categoria, diaria);
                quartos.add(novoQuarto);

                // caso o consumo não esteja vazio
                if (!consumoStr.trim().isEmpty()) {
                    String[] codigosConsumo = consumoStr.split(",");

                    for (int i = 0; i < codigosConsumo.length; i++) {
                        String codigoStr = codigosConsumo[i];

                        int codigoConsumo = Integer.parseInt(codigoStr.trim());
                        novoQuarto.adicionaConsumo(codigoConsumo);
                    }
                }

            }
            System.out.println("Dados dos quartos lidos com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de quartos: " + e.getMessage());
            e.printStackTrace();
        }

        // Carregar produtos
        try (FileReader reader = new FileReader(arquivoProdutos);
                Scanner sc = new Scanner(reader).useLocale(Locale.ENGLISH).useDelimiter(";|\\n")) {

            // Descarta a linha do cabeçalho
            sc.nextLine();

            while (sc.hasNext()) {
                int codigo = sc.nextInt();
                String nome = sc.next();
                float preco = sc.nextFloat();

                // Cria o produto
                Produto novoProduto = new Produto(codigo, nome, preco);
                produtos.add(novoProduto);

            }
            System.out.println("Dados dos produtos lidos com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de produtos: " + e.getMessage());
            e.printStackTrace();
        }

        // Carregar reservas
        try (FileReader reader = new FileReader(arquivoReservas);
                Scanner sc = new Scanner(reader).useLocale(Locale.ENGLISH).useDelimiter(";|\\n")) {

            // Descarta a linha do cabeçalho
            sc.nextLine();

            while (sc.hasNext()) {
                int diaInicio = sc.nextInt();
                int diaFim = sc.nextInt();
                String cliente = sc.next().trim();
                int numeroQuarto = sc.nextInt();
                char status = sc.next().charAt(0);

                // Encontrar o quarto reservado
                Quarto quartoReservado = null;
                for (int i = 0; i < quartos.size(); i++) {
                    if (quartos.get(i).getNumero() == numeroQuarto) {
                        // atribui o objeto Quarto encontrado ao 'quartoReservado'
                        // o 'quartoReservado', passa a armazedar um objeto inteiro
                        quartoReservado = quartos.get(i);
                        break;
                    }
                }

                // Só cria a reserva se encontrar um quarto válido pra reserva
                if (quartoReservado != null) {
                    // Cria a reserva com o objeto Quarto correto
                    Reserva novaReserva = new Reserva(diaInicio, diaFim, cliente, quartoReservado, status);
                    reservas.add(novaReserva);

                } else {
                    System.err.println(
                            "Erro: Quarto com número " + numeroQuarto + " não encontrado. Reserva não carregada.");
                }

            }
            System.out.println("Dados das reservas lidos com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de reservas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void salvaDados() {
        String arquivoPousada = "C:\\Users\\User\\Desktop\\JavaUnisinos\\SegundoSemestre\\TrabalhoGrauA\\pousada.txt";
        String arquivoQuartos = "C:\\Users\\User\\Desktop\\JavaUnisinos\\SegundoSemestre\\TrabalhoGrauA\\quarto.txt";
        String arquivoProdutos = "C:\\Users\\User\\Desktop\\JavaUnisinos\\SegundoSemestre\\TrabalhoGrauA\\produto.txt";
        String arquivoReservas = "C:\\Users\\User\\Desktop\\JavaUnisinos\\SegundoSemestre\\TrabalhoGrauA\\reserva.txt";

        // Salvar pousada
        try {
            FileWriter writer = new FileWriter(arquivoPousada);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("nome;contato");
            buffer.write("\n");

            String linha = String.format("%s;%s",
                    getNome(),
                    getContato());

            buffer.write(linha);
            buffer.write("\n");

            System.out.println("Dados da pousada salvos com sucesso!");
            buffer.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo da pousada: " + e.getMessage());
            e.printStackTrace();
        }

        // Salvar quartos
        try {
            Locale.setDefault(Locale.ENGLISH);
            FileWriter writer = new FileWriter(arquivoQuartos);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("numero;categoria;diaria;consumo");
            buffer.write("\n");

            for (int i = 0; i < quartos.size(); i++) {
                Quarto quarto = quartos.get(i);
                String linha = String.format(Locale.ENGLISH, "%d;%c;%.2f",
                        quarto.getNumero(),
                        quarto.getCategoria(),
                        quarto.getDiaria());

                ArrayList<Integer> consumo = quarto.listaConsumo();
                StringBuilder consumoStr = new StringBuilder();
                
                for (int j = 0; j < consumo.size(); j++) {
                    consumoStr.append(consumo.get(j));

                    // lógica pra adicionar a "," na string
                    if (j < consumo.size() - 1) {
                        consumoStr.append(",");
                    }
                }

                String linhaCompleta = linha + ";" + consumoStr.toString();

                buffer.write(linhaCompleta);
                buffer.write("\n");
            }

            System.out.println("Dados dos quartos salvos com sucesso!");
            buffer.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo dos quartos: " + e.getMessage());
            e.printStackTrace();
        }

        // Salvar produtos
        try {
            FileWriter writer = new FileWriter(arquivoProdutos);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("codigo;nome;preco");
            buffer.write("\n");

            for (int i = 0; i < produtos.size(); i++) {
                Produto produto = produtos.get(i);
                String linha = String.format(Locale.ENGLISH, "%d;%s;%.2f",
                        produto.getCodigo(),
                        produto.getNome(),
                        produto.getPreco());

                buffer.write(linha);
                buffer.write("\n");
            }

            System.out.println("Dados dos produtos salvos com sucesso!");
            buffer.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo dos produtos: " + e.getMessage());
            e.printStackTrace();
        }

        // Salvar reservas
        try {
            FileWriter writer = new FileWriter(arquivoReservas);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("diaInicio;diaFim;cliente;numeroQuarto;status");
            buffer.write("\n");

            for (int i = 0; i < reservas.size(); i++) {
                Reserva reserva = reservas.get(i);

                String linha = String.format("%d;%d;%s;%d;%c",
                        reserva.getDiaInicio(),
                        reserva.getDiaFim(),
                        reserva.getCliente(),
                        // getQuarto retorna um objeto quarto, e getNumero pega o numero do objeto
                        // quarto
                        reserva.getQuarto().getNumero(),
                        reserva.getStatus());

                buffer.write(linha);
                buffer.write("\n");
            }

            System.out.println("Dados das reservas salvos com sucesso!");
            buffer.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo das reservas: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void listarProdutos() {

        System.out.println("----- Produtos no Catálogo -----");

        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(produtos.get(i).toString());
        }

        System.out.println("-------------------------------");
    }

    public void consultaDisponibilidade(int data, int quarto) {

        // procura um quarto com número correspondente à entrada do usuário
        Quarto quartoEncontrado = null;
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getNumero() == quarto) {
                quartoEncontrado = quartos.get(i);
                break;
            }
        }

        // caso não encontre nenhum quarto com aquele número
        if (quartoEncontrado == null) {
            System.out.println("\nQuarto não encontrado.");
            return;
        }

        // verificar disponibilidade do quarto encontrado
        boolean disponivel = true;

        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);

            if (data >= reserva.getDiaInicio() && data <= reserva.getDiaFim()) {
                if (reserva.getQuarto().getNumero() == quarto
                        && (reserva.getStatus() == 'A' || reserva.getStatus() == 'I')) {
                    disponivel = false;
                    break;
                }
            }
        }

        if (disponivel) {
            System.out.println("\nQuarto disponível para a data informada.");
            System.out.println(quartoEncontrado.toString());
        } else {
            System.out.println("\nQuarto já reservado para a data informada.");
        }
    }

    public void consultaReserva(int data, String cliente, int quarto) {
        Reserva reservaEncontrada = null;
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if ((data >= reserva.getDiaInicio() && data <= reserva.getDiaFim())
                    && cliente.trim().equalsIgnoreCase(reserva.getCliente().trim())
                    && quarto == reserva.getQuarto().getNumero()) {
                reservaEncontrada = reserva;
                System.out.println(reservaEncontrada.toString());
                return;
            }
        }

        if (reservaEncontrada == null) {
            System.out.println("\nReserva não encontrada.");
            return;
        }
    }

    public void realizaReserva(int diaInicio, int diaFim, String cliente, int quarto) {

        Quarto quartoEncontrado = null;
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getNumero() == quarto) {
                quartoEncontrado = quartos.get(i);
                break;
            }
        }

        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if ((diaInicio >= reserva.getDiaInicio() && diaInicio <= reserva.getDiaFim())
                    || (diaFim >= reserva.getDiaInicio() && diaFim <= reserva.getDiaFim())) {
                if (reserva.getQuarto().getNumero() == quarto
                        && (reserva.getStatus() == 'A' || reserva.getStatus() == 'I')) {
                    System.out.println("\nQuarto já reservado para a data informada.");
                    return;
                }
            }

            if (reserva.getCliente().equalsIgnoreCase(cliente)
                    && (reserva.getStatus() == 'A' || reserva.getStatus() == 'I')) {
                System.out.println("\nCliente já possui uma reserva.");
                return;
            }

        }

        if (quartoEncontrado == null) {
            System.out.println("\nQuarto não encontrado.");
            return;
        }

        Reserva novaReserva = new Reserva(diaInicio, diaFim, cliente, quartoEncontrado, 'A');
        reservas.add(novaReserva);
        System.out.println("\nReserva realizada com sucesso!");
        salvaDados();
    }

    // tem que colocar de limpar o consumo caso cancele a reserva
    public void cancelaReserva(String cliente) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);

            if (reserva.getCliente().equalsIgnoreCase(cliente)
                    && (reserva.getStatus() == 'A' || reserva.getStatus() == 'I')) {
                System.out.println("\nReserva cancelada com sucesso!\n");
                reserva.setStatus('C');
                salvaDados();
                return;
            } else if (reserva.getCliente().equalsIgnoreCase(cliente)
                    && reserva.getStatus() == 'C') {
                System.out.println("\nReserva já cancelada.");
                return;
            } else if (reserva.getCliente().equalsIgnoreCase(cliente)
                    && reserva.getStatus() == 'O') {
                System.out.println("\nCheck-Out já realizado.");
                return;
            }
        }

        // procedimento padrão caso não caia no if do for
        System.out.println("\nNenhuma reserva encontrada.");
    }

    // acho que ta parando na primeira reserva que acha, ai se tiver duas reservas e
    // a primeira for C, ele nao faz o check in da outra
    // acho q tem q fazer aqla parada de tipo reservaAtiva == null, ai faz toda
    // verificação e substitui, e nao para na primeira
    public void realizaCheckIn(String cliente) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);

            if (reserva.getCliente().equalsIgnoreCase(cliente)
                    && reserva.getStatus() == 'A') {
                System.out.println("\nCheck-in realizado com sucesso!\n");
                reserva.setStatus('I');

                int qtdDias = (reserva.getDiaFim() - reserva.getDiaInicio()) + 1;
                float vlrTotalDiarias = reserva.getQuarto().getDiaria() * qtdDias;

                salvaDados();

                System.out
                        .println("\nData inicial: " + reserva.getDiaInicio() + "\nData Final: " + reserva.getDiaFim());
                System.out.println("Quantidade de dias reservados: %d".formatted(qtdDias));
                System.out.println("Valor total das diárias: %.2f".formatted(vlrTotalDiarias));
                System.out.println(reserva.getQuarto().toString());

                return;
            } else if (reserva.getCliente().equalsIgnoreCase(cliente)
                    && reserva.getStatus() == 'C') {
                System.out.println("\nA reserva não está ativa.");
                return;
            } else if (reserva.getCliente().equalsIgnoreCase(cliente)
                    && reserva.getStatus() == 'O') {
                System.out.println("\nCheck-Out já realizado.");
                return;
            } else if (reserva.getCliente().equalsIgnoreCase(cliente)
                    && reserva.getStatus() == 'I') {
                System.out.println("\nCheck-In já realizado.");
                return;
            }
        }

        // procedimento padrão caso não caia no if do for
        System.out.println("\nNenhuma reserva encontrada.");
    }

    public void realizaCheckOut(String cliente) {
        Reserva reservaAtiva = null;
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);
            if (reserva.getCliente().equalsIgnoreCase(cliente) && reserva.getStatus() == 'I') {
                reservaAtiva = reserva;
                break;
            }
        }

        if (reservaAtiva == null) {
            System.out.println("\nErro: Não existe check-in ativo para o cliente " + cliente + ".");
            return;
        }

        // 1. Calcular o valor total das diárias
        int qtdDias = (reservaAtiva.getDiaFim() - reservaAtiva.getDiaInicio()) + 1;
        float vlrTotalDiarias = reservaAtiva.getQuarto().getDiaria() * qtdDias;

        // 2. Calcular o valor total dos consumos, usando o método do Quarto
        double vlrTotalConsumos = reservaAtiva.getQuarto().valorTotalConsumo(produtos);
        double vlrTotalGeral = vlrTotalDiarias + vlrTotalConsumos;

        // 3. Exibir o resumo para o cliente
        System.out.println("\n--- Check-Out de " + cliente + " ---");
        System.out.println("Diárias: R$ " + String.format("%.2f", vlrTotalDiarias));
        System.out.println("Consumos: R$ " + String.format("%.2f", vlrTotalConsumos));
        System.out.println("TOTAL GERAL: R$ " + String.format("%.2f", vlrTotalGeral));
        System.out.println("-------------------------------------");

        // 4. Mudar o status da reserva e limpar o consumo do quarto
        reservaAtiva.setStatus('O'); // 'O' de "Out" (check-out)
        reservaAtiva.getQuarto().limpaConsumo();

        // 5. Salvar os dados atualizados
        salvaDados();

        System.out.println("\nCheck-Out realizado com sucesso! Quarto liberado.");
    }

    public void registrarConsumo(Reserva reservaCheckIn, int codigoProduto) {
        Produto produtoConsumido = null;
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getCodigo() == codigoProduto) {
                produtoConsumido = produto;
                break;
            }
        }

        if (produtoConsumido == null) {
            System.out.println("Produto com código " + codigoProduto + " não encontrado.");
            return;
        }

        Quarto quartoDaReserva = reservaCheckIn.getQuarto();
        quartoDaReserva.adicionaConsumo(codigoProduto);

        System.out.println("\nConsumo de " + produtoConsumido.getNome() + " registrado com sucesso para o cliente "
                + reservaCheckIn.getCliente() + ".");

        salvaDados();
    }

    public Reserva buscarReservaCheckIn(String cliente) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva reserva = reservas.get(i);

            if (reserva.getCliente().equalsIgnoreCase(cliente) && reserva.getStatus() == 'I') {
                return reserva;
            }
        }

        // Retorno padrão caso nao ache uma reserva com status 'I'
        return null;
    }

}