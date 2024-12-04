import java.sql.*;
import java.util.Scanner;

public class Notificacao {
    private String url;
    private String user;
    private String password;

    public Notificacao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void buscarEExibirOpcoes(String nomeBusca, Scanner scanner) {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            PreparedStatement stm = con.prepareStatement(
                "SELECT emailOrganizador AS email, telefoneOrganizador AS telefone, nomeOrganizador AS nome " +
                "FROM Organizador WHERE nomeOrganizador LIKE ? " +
                "UNION " +
                "SELECT emailParticipante AS email, telefoneParticipante AS telefone, nomeParticipante AS nome " +
                "FROM Participante WHERE nomeParticipante LIKE ?");
            
            stm.setString(1, "%" + nomeBusca + "%");
            stm.setString(2, "%" + nomeBusca + "%");
            
            ResultSet rs = stm.executeQuery();
            
            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhum resultado encontrado.");
                return;
            }
    
            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("----------------------------------");
            }
    
            System.out.print("Deseja enviar notificações para esses contatos? (s/n): ");
            String resposta = scanner.nextLine();
    
            if (resposta.equalsIgnoreCase("s")) {
                System.out.print("Escolha o tipo de notificação (1 - Email, 2 - Telefone): ");
                int tipoNotificacao = scanner.nextInt();
                scanner.nextLine();
    
                if (tipoNotificacao == 1) {
                    System.out.println("Notificações por email enviadas com sucesso!");
                } else if (tipoNotificacao == 2) {
                    System.out.println("Notificações por telefone enviadas com sucesso!");
                } else {
                    System.out.println("Tipo de notificação inválido. Operação cancelada.");
                }
            } else {
                System.out.println("Operação cancelada.");
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao buscar dados: " + e.getMessage());
        }
    }
}
