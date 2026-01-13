package dao;

import model.Autogara;
import model.Autobuz;
import model.Cursa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAutogara {

    public Connection connect() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                MyDBInfo.PostgreSQL_DATABASE_SERVER,
                MyDBInfo.PostgreSQL_USERNAME,
                MyDBInfo.PostgreSQL_PASSWORD
        );
    }

    // ===== AUTOGARA =====
    public List<Autogara> citesteAutogari(Connection con) throws SQLException {
        List<Autogara> lista = new ArrayList<>();
        String sql = "SELECT * FROM Autogara";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // Actualizat cu 4 parametri: ID, Nume, Adresa, Oras
            Autogara a = new Autogara(
                    rs.getInt("ID_autogara"),
                    rs.getString("Nume"),
                    rs.getString("Adresa"),
                    rs.getString("Oras")
            );
            lista.add(a);
        }
        return lista;
    }

    // ===== AUTOBUZ =====
    public List<Autobuz> citesteAutobuze(Connection con) throws SQLException {
        List<Autobuz> lista = new ArrayList<>();
        String sql = "SELECT * FROM Autobuz";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Autobuz a = new Autobuz(
                    rs.getInt("ID_autobuz"),
                    rs.getString("Numar_inmatriculare"),
                    rs.getInt("Capacitate")
            );
            lista.add(a);
        }
        return lista;
    }

    // ===== AUTOBUZE FOLOSITE LA O CURSA (dupa idCursa) =====
    public List<Autobuz> citesteAutobuzeFolositeLaCursa(Connection con, int idCursa) throws SQLException {
        List<Autobuz> lista = new ArrayList<>();
        String sql = """
                SELECT A.ID_autobuz, A.Numar_inmatriculare, A.Capacitate
                FROM Foloseste F
                JOIN Autobuz A ON F.ID_autobuz = A.ID_autobuz
                WHERE F.ID_cursa = ?
                """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idCursa);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Autobuz a = new Autobuz(
                    rs.getInt("ID_autobuz"),
                    rs.getString("Numar_inmatriculare"),
                    rs.getInt("Capacitate")
            );
            lista.add(a);
        }
        return lista;
    }

    // ===== CURSA =====
    public List<Cursa> citesteCurse(Connection con) throws SQLException {
        List<Cursa> lista = new ArrayList<>();
        // Am adaugat A.Adresa in SELECT
        String sql = """
                SELECT C.ID_cursa, C.Data_plecare, C.Ora_plecare, C.ID_autogara, C.ID_rutaFixa, A.Nume, A.Adresa, A.Oras
                FROM Cursa C
                JOIN Autogara A ON C.ID_autogara = A.ID_autogara
                """;

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            // Actualizat cu 4 parametri: ID, Nume, Adresa, Oras
            Autogara autogara = new Autogara(
                    rs.getInt("ID_autogara"),
                    rs.getString("Nume"),
                    rs.getString("Adresa"),
                    rs.getString("Oras")
            );

            Cursa c = new Cursa(
                    rs.getInt("ID_cursa"),
                    rs.getDate("Data_plecare"),
                    rs.getTime("Ora_plecare"),
                    autogara,
                    rs.getInt("ID_rutaFixa")
            );

            // Citim și autobuzele folosite pentru cursa curenta
            List<Autobuz> autobuzeFolosite = citesteAutobuzeFolositeLaCursa(con, c.getIdCursa());
            c.setAutobuzeFolosite(autobuzeFolosite);

            lista.add(c);
        }
        return lista;
    }
}