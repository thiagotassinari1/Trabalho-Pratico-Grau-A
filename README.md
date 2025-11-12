# 🏨 Sistema de Gerenciamento de Pousada

![Status](https://img.shields.io/badge/status-conclu%C3%ADdo-green)

Este projeto é um sistema básico para o gerenciamento de uma pousada, permitindo o controle de quartos, reservas, consumo e faturamento. Foi desenvolvido como o **Trabalho Prático do Grau A** na cadeira de **Programação Orientada a Objetos** da Escola Politécnica da **Universidade do Vale do Rio dos Sinos (Unisinos)**.

O objetivo central do projeto é aplicar conceitos de Programação Orientada a Objetos e implementar a **persistência de dados em arquivos de texto tabulados**.

---

## ⚙️ Estrutura de Classes

A arquitetura do sistema é baseada em quatro classes principais que modelam o domínio do problema:

1.  **`Pousada`**: A classe central que gerencia as listas de quartos, reservas e produtos, além de orquestrar as operações principais.
2.  **`Quarto`**: Representa um quarto da pousada, contendo seu número, categoria (Standard, Master, Premium), diária e o controle de consumo.
3.  **`Reserva`**: Modela uma reserva, ligando um cliente a um quarto por um período e mantendo um status (Ativa, Cancelada, Check-In, Check-Out).
4.  **`Produto`**: Representa um item do da copa, com código, nome e preço.

---

## 💾 Persistência de Dados

O sistema não utiliza um banco de dados; toda a persistência é feita em arquivos de texto:

* **Carregamento (`carregaDados()`):** Ao iniciar, o programa lê e carrega os dados dos arquivos `pousada.txt`, `quarto.txt`, `reserva.txt` e `produto.txt`.
* **Salvamento (`salvaDados()`):** Ao sair (opção 0) ou ao escolher a opção "Salvar" (opção 8), o sistema salva o estado atual das reservas e quartos nos arquivos. Reservas canceladas ou com check-out são excluídas no processo de salvamento.
* **Formato:** Foram utilizados métodos `serializar()` e `deserializar()` para padronizar a conversão dos objetos para o formato de texto tabulado.

---

## 📋 Funcionalidades do Menu

O sistema é operado por um menu de console que implementa todas as funcionalidades exigidas:

1.  **Consultar disponibilidade:** Verifica se um quarto está livre em uma data específica.
2.  **Consultar reserva:** Busca reservas ativas por data, cliente ou quarto.
3.  **Realizar reserva:** Aloca um quarto para um cliente em um período, após verificar a disponibilidade.
4.  **Cancelar reserva:** Altera o status de uma reserva ativa para "Cancelada".
5.  **Realizar check-in:** Confirma a entrada de um cliente com reserva ativa.
6.  **Realizar check-out:** Finaliza a estadia de um cliente em check-in, calculando o valor total (diárias + consumo) e limpando o consumo do quarto.
7.  **Registrar consumo:** Adiciona produtos à conta de um cliente que está em check-in.
8.  **Salvar:** Salva manualmente o estado atual dos dados nos arquivos.
0.  **Sair:** Encerra a aplicação, salvando os dados automaticamente se necessário.
