import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Participante {
    int idParticipante;
    String nomeParticipante;
    int telefoneParticipante;
    String emailParticipante;
    int idEvento;

    public Participante(int idParticipante, String nomeParticipante, int telefoneParticipante, String emailParticipante, int idEvento) {
        this.idParticipante = idParticipante;
        this.nomeParticipante = nomeParticipante;
        this.telefoneParticipante = telefoneParticipante;
        this.emailParticipante = emailParticipante;
        this.idEvento = idEvento;
    }

    @Override
    public String toString() {
        return  "ID: " + this.idParticipante
            + "\nNome: " + this.nomeParticipante
            + "\ntelefone: " + this.telefoneParticipante
            + "\nEmail: " + this.emailParticipante
            + "\nID Evento: " + this.idEvento
            + "\n===================================";
    }

    public static Participante buscaParticipante(int idParticipante) {
        final String url = "jdbc:mysql://localhost:3306/evento";
        final String user = "root";
        final String password = "";
        Participante participante = null;
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stm = con.createStatement()) {
             
            ResultSet rs = stm.executeQuery("SELECT * FROM Participante WHERE idParticipante = " + idParticipante);
            
            if (rs.next()) {
                participante = new Participante(
                    rs.getInt("idParticipante"),
                    rs.getString("nomeParticipante"),
                    rs.getInt("telefoneParticipante"),
                    rs.getString("emailParticipante"),
                    rs.getInt("idEvento")
                );
                System.out.println(participante);
            } else {
                throw new RuntimeException("Participante não encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar participante");
        }
        return participante;
    }

    public static void main(String[] args) {
        Participante participante = buscaParticipante(1); 
    }
}
