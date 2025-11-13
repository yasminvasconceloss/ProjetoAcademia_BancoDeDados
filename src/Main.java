// Projeto Academia feito por: Luana Severini e Yasmin Vasconcelos

import database.ConnectionFactory;
import Models.Aluno;
import Models.Plano;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

// Programa principal que testa a integração com o banco
public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("=== Sistema da Academia ===");

            while (true) {
                System.out.println("\n1 - Adicionar Aluno");
                System.out.println("2 - Buscar Aluno por CPF");
                System.out.println("3 - Listar Alunos");
                System.out.println("4 - Deletar Aluno");
                System.out.println("5 - Listar Planos");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                String op = scanner.nextLine();

                switch (op) {
                    case "1" -> {
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String tel = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Tipo do plano (número): ");
                        int tipo = Integer.parseInt(scanner.nextLine());
                        Aluno.create(conn, new Aluno(cpf, nome, tel, email, tipo));
                        System.out.println("Aluno adicionado com sucesso!");
                    }
                    case "2" -> {
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        Aluno a = Aluno.read(conn, cpf);
                        if (a != null)
                            System.out.printf("Nome: %s | Plano: %d\n", a.nome, a.planoTipo);
                        else
                            System.out.println("Aluno não encontrado.");
                    }
                    case "3" -> {
                        List<Aluno> alunos = Aluno.readAll(conn);
                        for (Aluno a : alunos)
                            System.out.printf("CPF: %s | Nome: %s | Plano: %d\n", a.cpf, a.nome, a.planoTipo);
                    }
                    case "4" -> {
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        Aluno.delete(conn, cpf);
                        System.out.println("Aluno removido!");
                    }
                    case "5" -> {
                        List<Plano> planos = Plano.readAll(conn);
                        for (Plano p : planos)
                            System.out.printf("Plano %d - Valor: %s | Descrição: %s\n", p.tipoPlano, p.valor, p.descricao);
                    }
                    case "0" -> {
                        System.out.println("Saindo...");
                        System.exit(0);
                    }
                    default -> System.out.println("Opção inválida!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}