import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Organizador {
    int idOrganizador;
    String nomeOrganizador;
    String emailOrganizador;
    String telefoneOrganizador;

    public Organizador(int idOrganizador, String nomeOrganizador, String emailOrganizador, String telefoneOrganizador) {
        this.idOrganizador = idOrganizador;
        this.nomeOrganizador = nomeOrganizador;
        this.emailOrganizador = emailOrganizador;
        this.telefoneOrganizador = telefoneOrganizador;
    }

    @Override
    public String toString() {
        return "Organizador{" +
                "idOrganizador=" + idOrganizador +
                ", nome='" + nomeOrganizador + '\'' +
                ", email='" + emailOrganizador + '\'' +
                ", telefone='" + telefoneOrganizador + '\'' +
                '}';
    }

    public static Organizador buscaOrganizador(int idOrganizador) {
        final String url = "jdbc:mysql://localhost:3306/evento";
        final String user = "root";
        final String password = "";
        Organizador organizador = null;
        String query = "SELECT * FROM Organizador WHERE idOrganizador = ?"; 
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stm = con.prepareStatement(query)) {
            stm.setInt(1, idOrganizador);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    organizador = new Organizador(
                        rs.getInt("idOrganizador"),
                        rs.getString("nomeOrganizador"),
                        rs.getString("emailOrganizador"),
                        rs.getString("telefoneOrganizador")
                    );
                    System.out.println(organizador);
                } else {
                    throw new RuntimeException("Organizador não encontrado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar organizador", e);
        }

        return organizador;
    }

    public static void main(String[] args) {
        try {
            Organizador organizador = buscaOrganizador(1); 
            if (organizador != null) {
                System.out.println("Organizador encontrado: " + organizador);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
