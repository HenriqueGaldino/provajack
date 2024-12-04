import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class Sistema{
    public static void main(String[] args) throws Exception {
        final String url = "jdbc:mysql://localhost:3306/evento";
        final String user = "root";
        final String password = "";
        System.out.println("Sistema de Eventos");
        Scanner scanner = new Scanner(System.in);
        int opt = 0;
 
        do {
            System.out.println("1 - Cadastrar Organizador");
            System.out.println("2 - Cadastrar Local");
            System.out.println("3 - Cadastrar Evento");
            System.out.println("4 - Cadastrar Participante");
            System.out.println("5 - Listar Organizadores");
            System.out.println("6 - Listar Locais");
            System.out.println("7 - Listar Eventos");
            System.out.println("8 - Listar Participantes");
            System.out.println("9 - Notificacoes");
            System.out.println("10 - Alterar Organizador");
            System.out.println("11 - Deletar Organizador");
            System.out.println("12 - Alterar Local");
            System.out.println("13 - Deletar Local");
            System.out.println("14 - Alterar Evento");
            System.out.println("15 - Deletar Evento");
            System.out.println("16 - Alterar Participante");
            System.out.println("17 - Deletar Participante");
            System.out.println("18 - Sair");
            try {
                opt = scanner.nextInt();
            } catch (Exception e) {
                opt = 0;
            }
 
            switch (opt) {
                case 1:
                    try {
                        System.out.println("Cadastrar Organizador");
                        System.out.println("Digite o nome do organizador: ");
                        String nomeOrganizador = scanner.next();
                        System.out.println("Digite o email do organizador: ");
                        String emailOrganizador = scanner.next();
                        System.out.println("Digite o telefone do organizador: ");
                        String telefoneOrganizador = scanner.next();
 
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("INSERT INTO Organizador "
                        + "(nomeOrganizador, emailOrganizador, telefoneOrganizador) VALUES "
                        + "(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        stm.setString(1, nomeOrganizador);
                        stm.setString(2, emailOrganizador);
                        stm.setString(3, telefoneOrganizador);
                        if (stm.executeUpdate() > 0) {
                            ResultSet rs = stm.getGeneratedKeys();
 
                            if (rs.next()) {
                                int idOrganizador = rs.getInt(1);
                                new Organizador(idOrganizador, nomeOrganizador , emailOrganizador, telefoneOrganizador);
                            }
                        }
                        con.close();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    break;
 
                    case 2:
                    try {
                        System.out.println("Cadastrar Local");
                        System.out.println("Digite o nome do local: ");
                        String nomeLocal = scanner.next();
                        System.out.println("Digite a descricao do local: ");
                        String descricaoLocal = scanner.next();
                        System.out.println("Digite a capacidade do local: ");
                        int vagas = scanner.nextInt();
 
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("INSERT INTO Local "
                        + "(nomeLocal, descricaoLocal, vagas) VALUES "
                        + "(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                        stm.setString(1, nomeLocal);
                        stm.setString(2, descricaoLocal);
                        stm.setInt(3, vagas);
                        if (stm.executeUpdate() > 0) {
                            ResultSet rs = stm.getGeneratedKeys();
 
                            if (rs.next()) {
                                int idLocal = rs.getInt(1);
                                new Local(idLocal, nomeLocal, descricaoLocal, vagas);
                            }
                        }
                        con.close();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
 
                        break;
 
 
                    case 3:{
                        try {
                            System.out.println("Cadastrar Evento");
                            System.out.println("Digite o nome do evento: ");
                            String nomeEvento = scanner.next();
                            System.out.println("Digite o id do organizador: ");
                            int idOrganizador = scanner.nextInt();
                            System.out.println("Digite o id do local: ");
                            int idLocal = scanner.nextInt();
                            System.out.println("Digite a data do evento: ");
                            String dataEvento = scanner.next();
                            System.out.println("Digite a descricao do evento: ");
                            String descricaoEvento = scanner.next();
 
                            Connection con = DriverManager.getConnection(url, user, password);
                            PreparedStatement stm = con.prepareStatement("INSERT INTO Evento "
                            + "(nomeEvento, idOrganizador, idLocal, dataEvento, descricaoEvento) VALUES "
                            + "(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                            stm.setString(1, nomeEvento);
                            stm.setInt(2, idOrganizador);
                            stm.setInt(3, idLocal);
                            stm.setString(4, dataEvento);
                            stm.setString(5, descricaoEvento);
                            if (stm.executeUpdate() > 0) {
                                ResultSet rs = stm.getGeneratedKeys();
 
                                if (rs.next()) {
                                    int idEvento = rs.getInt(1);
                                    new Evento(idEvento, nomeEvento , idOrganizador, idLocal, dataEvento, descricaoEvento);
                                }
                            }
                            con.close();
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
 
                    break;
                }
 
                case 4:
                try {
                    System.out.println("Cadastrar Participante");
                    System.out.println("Digite o nome do participante: ");
                    String nomeParticipante = scanner.next();
                    System.out.println("Digite o telefone do participante: ");
                    String telefoneParticipante = scanner.next();
                    System.out.println("Digite o email do participante: ");
                    String emailParticipante = scanner.next();
                    System.out.println("Digite o id do evento: ");
                    int idEvento = scanner.nextInt();
 
                    Connection con = DriverManager.getConnection(url, user, password);
                    PreparedStatement stm = con.prepareStatement("INSERT INTO Participante "
                    + "(nomeParticipante, telefoneParticipante, emailParticipante, idEvento) VALUES "
                    + "(?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                    stm.setString(1, nomeParticipante);
                    stm.setString(2, telefoneParticipante);
                    stm.setString(3, emailParticipante);
                    stm.setInt(4, idEvento);
                    if (stm.executeUpdate() > 0) {
                        ResultSet rs = stm.getGeneratedKeys();
 
                        if (rs.next()) {
                            int idParticipante = rs.getInt(1);
                            new Participante(idParticipante, nomeParticipante ,telefoneParticipante, emailParticipante, idEvento);
                        }
                    }
                    con.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                break;
 
                case 5:
                try {
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM Organizador");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("idOrganizador"));
                        System.out.println("Nome: " + rs.getString("nomeOrganizador"));
                        System.out.println("Email: " + rs.getString("emailOrganizador"));
                        System.out.println("Telefone: " + rs.getString("telefoneOrganizador"));
                        System.out.println("===================================");
                    }
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
 
                case 6:
                try {
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM Local");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("idLocal"));
                        System.out.println("Nome: " + rs.getString("nomeLocal"));
                        System.out.println("Descricao: " + rs.getString("descricaoLocal"));
                        System.out.println("Vagas: " + rs.getInt("vagas"));
                        System.out.println("===================================");
                    }
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
 
                case 7:
                try {
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM Evento");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("idEvento"));
                        System.out.println("Nome: " + rs.getString("nomeEvento"));
                        System.out.println("ID Organizador: " + rs.getInt("idOrganizador"));
                        System.out.println("ID Local: " + rs.getInt("idLocal"));
                        System.out.println("Data: " + rs.getString("dataEvento"));
                        System.out.println("Descricao: " + rs.getString("descricaoEvento"));
                        System.out.println("===================================");
                    }
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
 
                case 8:
                try {
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("SELECT * FROM Participante");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("idParticipante"));
                        System.out.println("Nome: " + rs.getString("nomeParticipante"));
                        System.out.println("Telefone: " + rs.getString("telefoneParticipante"));
                        System.out.println("Email: " + rs.getString("emailParticipante"));
                        System.out.println("ID Evento: " + rs.getInt("idEvento"));
                        System.out.println("===================================");
                    }
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
 
                case 9:
                    Notificacao notificacao = new Notificacao(url, user, password);
 
                    System.out.print("Digite o nome do organizador ou participante para buscar: ");
                    scanner.nextLine();
                    String nomeBusca = scanner.nextLine();
 
                    notificacao.buscarEExibirOpcoes(nomeBusca, scanner);
                break;
 
                case 10: {
                    System.out.println("Alterar Organizador");
                    System.out.println("Digite o id do organizador: ");
                    int idOrganizador = scanner.nextInt();
                    System.out.println("Digite o nome do organizador: ");
                    String nomeOrganizador = scanner.next();
                    System.out.println("Digite o email do organizador: ");
                    String emailOrganizador = scanner.next();
                    System.out.println("Digite o telefone do organizador: ");
                    String telefoneOrganizador = scanner.next();
 
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("UPDATE Organizador SET nomeOrganizador = ?, emailOrganizador = ?, telefoneOrganizador = ? WHERE idOrganizador = ?");
                        stm.setString(1, nomeOrganizador);
                        stm.setString(2, emailOrganizador);
                        stm.setString(3, telefoneOrganizador);
                        stm.setInt(4, idOrganizador);
                        stm.executeUpdate();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                break;
                }
 
                case 11:
                    System.out.println("Deletar Organizador");
                    System.out.println("Digite o id do organizador: ");
                    int idOrganizadorDeletar = scanner.nextInt();
 
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("DELETE FROM Organizador WHERE idOrganizador = ?");
                        stm.setInt(1, idOrganizadorDeletar);
                        stm.executeUpdate();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                break;
 
                case 12: {
                    System.out.println("Alterar Local");
                    System.out.println("Digite o id do local: ");
                    int idLocal = scanner.nextInt();
                    System.out.println("Digite o nome do local: ");
                    String nomeLocal = scanner.next();
                    System.out.println("Digite a descricao do local: ");
                    String descricaoLocal = scanner.next();
                    System.out.println("Digite a capacidade do local: ");
                    int vagas = scanner.nextInt();
 
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("UPDATE Local SET nomeLocal = ?, descricaoLocal = ?, vagas = ? WHERE idLocal = ?");
                        stm.setString(1, nomeLocal);
                        stm.setString(2, descricaoLocal);
                        stm.setInt(3, vagas);
                        stm.setInt(4, idLocal);
                        stm.executeUpdate();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                break;
                }
 
                case 13:
                    System.out.println("Deletar Local");
                    System.out.println("Digite o id do local: ");
                    int idLocalDeletar = scanner.nextInt();
 
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("DELETE FROM Local WHERE idLocal = ?");
                        stm.setInt(1, idLocalDeletar);
                        stm.executeUpdate();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                break;
 
                case 14: {
                    System.out.println("Alterar Evento");
                    System.out.println("Digite o id do evento: ");
                    int idEvento = scanner.nextInt();
                    System.out.println("Digite o nome do evento: ");
                    String nomeEvento = scanner.next();
                    System.out.println("Digite o id do organizador: ");
                    int idOrganizador = scanner.nextInt();
                    System.out.println("Digite o id do local: ");
                    int idLocal = scanner.nextInt();
                    System.out.println("Digite a data do evento: ");
                    String dataEvento = scanner.next();
                    System.out.println("Digite a descricao do evento: ");
                    String descricaoEvento = scanner.next();
 
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("UPDATE Evento SET nomeEvento = ?, idOrganizador = ?, idLocal = ?, dataEvento = ?, descricaoEvento = ? WHERE idEvento = ?");
                        stm.setString(1, nomeEvento);
                        stm.setInt(2, idOrganizador);
                        stm.setInt(3, idLocal);
                        stm.setString(4, dataEvento);
                        stm.setString(5, descricaoEvento);
                        stm.setInt(6, idEvento);
                        stm.executeUpdate();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 15:
                    System.out.println("Deletar Evento");
                    System.out.println("Digite o id do evento: ");
                    int idEventoDeletar = scanner.nextInt();
 
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("DELETE FROM Evento WHERE idEvento = ?");
                        stm.setInt(1, idEventoDeletar);
                        stm.executeUpdate();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                break;
 
                case 16:
                    System.out.println("Alterar Participante");
                    System.out.println("Digite o id do participante: ");
                    int idParticipante = scanner.nextInt();
                    System.out.println("Digite o nome do participante: ");
                    String nomeParticipante = scanner.next();
                    System.out.println("Digite o telefone do participante: ");
                    String telefoneParticipante = scanner.next();
                    System.out.println("Digite o email do participante: ");
                    String emailParticipante = scanner.next();
                    System.out.println("Digite o id do evento: ");
                    int idEvento = scanner.nextInt();
 
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("UPDATE Participante SET nomeParticipante = ?, telefoneParticipante = ?, emailParticipante = ?, idEvento = ? WHERE idParticipante = ?");
                        stm.setString(1, nomeParticipante);
                        stm.setString(2, telefoneParticipante);
                        stm.setString(3, emailParticipante);
                        stm.setInt(4, idEvento);
                        stm.setInt(5, idParticipante);
                        stm.executeUpdate();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                break;
 
                case 17:
                    System.out.println("Deletar Participante");
                    System.out.println("Digite o id do participante: ");
                    int idParticipanteDeletar = scanner.nextInt();
 
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        PreparedStatement stm = con.prepareStatement("DELETE FROM Participante WHERE idParticipante = ?");
                        stm.setInt(1, idParticipanteDeletar);
                        stm.executeUpdate();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                break;
 
                case 18:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opt != 18);
 
        scanner.close();
    }
}