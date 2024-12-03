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
                "SELECT emailOrganizador AS email, nomeOrganizador AS nome " +
                "FROM Organizador WHERE nomeOrganizador LIKE ? " +
                "UNION " +
                "SELECT emailParticipante AS email, nomeParticipante AS nome " +
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
                System.out.println("----------------------------------");
            }
    
            System.out.print("Deseja enviar notificações para esses contatos? (s/n): ");
            String resposta = scanner.nextLine();
    
            if (resposta.equalsIgnoreCase("s")) {
                // Aqui você pode adicionar lógica para enviar notificações.
                System.out.println("Notificações enviadas com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao buscar dados: " + e.getMessage());
        }
    }
}    