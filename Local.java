import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Local{
    int idLocal;
    String nomeLocal;
    String descricaoLocal;
    int vagas;

    public Local(int idLocal, String nomeLocal, String descricaoLocal , int vagas) {
        this.idLocal = idLocal;
        this.nomeLocal = nomeLocal;
        this.descricaoLocal  = descricaoLocal;
        this.vagas = vagas;
    }

    @Override
    public String toString() {
        return "Local{" +
                "idLocal=" + idLocal +
                ", nome='" + nomeLocal + '\'' +
                ", descricao='" + descricaoLocal + '\'' +
                ", vagas='" + vagas + '\'' +
                '}';
    }

    public static Local buscaLocal(int idLocal) {
        final String url = "jdbc:mysql://localhost:3306/evento";
        final String user = "root";
        final String password = "";
        Local local = null;
        String query = "SELECT * FROM Local WHERE idLocal = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement stm = con.prepareStatement(query)) {
            stm.setInt(1, idLocal);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    local = new Local(
                        rs.getInt("idLocal"),
                        rs.getString("nomeLocal"),
                        rs.getString("descricaoLocal"),
                        rs.getInt("vagas")
                    );
                    System.out.println(local);
                } else {
                    throw new RuntimeException("Local não encontrado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar local", e);
        }

        return local;
}
}
    
