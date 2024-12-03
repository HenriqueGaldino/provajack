import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Evento {
    int idEvento;
    String nomeEvento;
    int idOrganizador;
    int idLocal;
    String dataEvento;
    String descricao;


    public Evento(int idEvento, String nomeEvento, int idOrganizador, int idLocal, String dataEvento, String descricao ) {
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.idOrganizador = idOrganizador;
        this. idLocal =  idLocal;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", nome='" + nomeEvento + '\'' +
                ", idOrganizador='" + idOrganizador + '\'' +
                ", idLocal='" + idLocal + '\'' +
                ", data='" + dataEvento + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public static Evento buscaEvento(int idEvento) {
        final String url = "jdbc:mysql://localhost:3306/evento";
        final String user = "root";
        final String password = "";
        Evento evento = null;
        String query = "SELECT * FROM Evento WHERE idEvento = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stm = con.prepareStatement(query)) {
            stm.setInt(1, idEvento);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    evento = new Evento(
                        rs.getInt("idEvento"),
                        rs.getString("nomeEvento"),
                        rs.getInt("idOrganizador"),
                        rs.getInt("idLocal"),
                        rs.getString("dataEvento"),
                        rs.getString("descricaoEvento")
                    );
                    System.out.println(evento);
                } else {
                    throw new RuntimeException("Evento não encontrado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar evento", e);
        }

        return evento;
}
}