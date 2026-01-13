import dao.DatabaseAutogara;
import model.Autogara;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class TestView extends JFrame {

    private JList<Autogara> listAutogari;
    private JTextField txtNume;
    private JTextField txtOras;
    private JTextField txtId;

    private DatabaseAutogara db;

    public TestView() {
        db = new DatabaseAutogara();

        setTitle("Autogari");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        incarcaAutogari();
    }

    private void initComponents() {
        listAutogari = new JList<>();
        txtId = new JTextField();
        txtNume = new JTextField();
        txtOras = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JScrollPane(listAutogari));
        panel.add(new JLabel("ID Autogara"));
        panel.add(txtId);
        panel.add(new JLabel("Nume"));
        panel.add(txtNume);
        panel.add(new JLabel("Oras"));
        panel.add(txtOras);

        add(panel);
    }

    private void incarcaAutogari() {
        try {
            Connection con = db.connect();
            List<Autogara> autogari = db.citesteAutogari(con);

            DefaultListModel<Autogara> model = new DefaultListModel<>();
            for (Autogara a : autogari) {
                model.addElement(a);
            }

            listAutogari.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
