import dao.DatabaseAutogara;
import model.Autogara;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        DatabaseAutogara db = new DatabaseAutogara();
        Connection con = null;

        List<Autogara> listaAutogari = new ArrayList<>();

        try {
            con = db.connect();

            // citire autogari din baza de date
            listaAutogari = db.citesteAutogari(con);

            // afisare in consola (test)
            for (Autogara a : listaAutogari) {
                System.out.println(
                        a.getIdAutogara() + " | " +
                                a.getNume() + " | " +
                                a.getOras()
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
