package viewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class CursaView extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    private JTextField tfID, tfDataPlecare, tfOraPlecare, tfIDAutogara, tfIDRutaFixa;
    private JButton btnRefresh, btnInsert, btnUpdate, btnDelete, btnSearch;

    // Datele de conectare
    private final String url = "jdbc:postgresql://localhost:5432/proiectautogara";
    private final String user = "postgres";
    private final String password = "fericirea14.";

    public CursaView() {
        setTitle("Gestionare Curse - Autogara");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tabelul
        model = new DefaultTableModel(new Object[]{
                "ID Cursa", "Data Plecare", "Ora Plecare", "ID Autogara", "ID RutaFixa"
        }, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel de input (NORTH)
        JPanel formPanel = new JPanel(new GridLayout(2, 5, 10, 5));
        tfID = new JTextField();
        tfDataPlecare = new JTextField();
        tfOraPlecare = new JTextField();
        tfIDAutogara = new JTextField();
        tfIDRutaFixa = new JTextField();

        formPanel.add(new JLabel("ID Cursa:"));
        formPanel.add(new JLabel("Data (yyyy-MM-dd):"));
        formPanel.add(new JLabel("Ora (HH:mm:ss):"));
        formPanel.add(new JLabel("ID Autogara:"));
        formPanel.add(new JLabel("ID RutaFixa:"));

        formPanel.add(tfID);
        formPanel.add(tfDataPlecare);
        formPanel.add(tfOraPlecare);
        formPanel.add(tfIDAutogara);
        formPanel.add(tfIDRutaFixa);

        add(formPanel, BorderLayout.NORTH);

        // Panel butoane (SOUTH)
        JPanel buttonPanel = new JPanel();
        btnRefresh = new JButton("Refresh");
        btnSearch = new JButton("Search");
        btnInsert = new JButton("Insert");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);

        // Logica evenimentelor
        btnRefresh.addActionListener(e -> loadData());
        btnInsert.addActionListener(e -> insertRecord());
        btnUpdate.addActionListener(e -> updateRecord());
        btnDelete.addActionListener(e -> deleteRecord());
        btnSearch.addActionListener(e -> searchRecords());

        // Selectie rand tabel
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                tfID.setText(model.getValueAt(row, 0).toString());
                tfDataPlecare.setText(model.getValueAt(row, 1).toString());
                tfOraPlecare.setText(model.getValueAt(row, 2).toString());
                tfIDAutogara.setText(model.getValueAt(row, 3).toString());
                tfIDRutaFixa.setText(model.getValueAt(row, 4).toString());
            }
        });

        loadData();
        setLocationRelativeTo(null);
    }

    private void loadData() {
        model.setRowCount(0);
        String query = "SELECT id_cursa, data_plecare, ora_plecare, id_autogara, id_rutafixa FROM cursa ORDER BY id_cursa";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_cursa"), rs.getDate("data_plecare"),
                        rs.getTime("ora_plecare"), rs.getInt("id_autogara"), rs.getInt("id_rutafixa")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Eroare: " + ex.getMessage());
        }
    }

    private void searchRecords() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT id_cursa, data_plecare, ora_plecare, id_autogara, id_rutafixa FROM cursa WHERE 1=1";

            if (!tfID.getText().trim().isEmpty()) sql += " AND id_cursa = ?";
            if (!tfDataPlecare.getText().trim().isEmpty()) sql += " AND data_plecare = ?";
            if (!tfIDAutogara.getText().trim().isEmpty()) sql += " AND id_autogara = ?";
            if (!tfIDRutaFixa.getText().trim().isEmpty()) sql += " AND id_rutafixa = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            int idx = 1;

            if (!tfID.getText().trim().isEmpty()) pst.setInt(idx++, Integer.parseInt(tfID.getText().trim()));
            if (!tfDataPlecare.getText().trim().isEmpty()) pst.setDate(idx++, Date.valueOf(tfDataPlecare.getText().trim()));
            if (!tfIDAutogara.getText().trim().isEmpty()) pst.setInt(idx++, Integer.parseInt(tfIDAutogara.getText().trim()));
            if (!tfIDRutaFixa.getText().trim().isEmpty()) pst.setInt(idx++, Integer.parseInt(tfIDRutaFixa.getText().trim()));

            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_cursa"), rs.getDate("data_plecare"),
                        rs.getTime("ora_plecare"), rs.getInt("id_autogara"), rs.getInt("id_rutafixa")
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Eroare cautare: " + ex.getMessage());
        }
    }

    private void insertRecord() {
        String sql = "INSERT INTO cursa (id_cursa, data_plecare, ora_plecare, id_autogara, id_rutafixa) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, Integer.parseInt(tfID.getText()));
            pst.setDate(2, Date.valueOf(tfDataPlecare.getText()));
            pst.setTime(3, Time.valueOf(tfOraPlecare.getText()));
            pst.setInt(4, Integer.parseInt(tfIDAutogara.getText()));
            pst.setInt(5, Integer.parseInt(tfIDRutaFixa.getText()));
            pst.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Cursa inserata!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Eroare: " + ex.getMessage());
        }
    }

    private void updateRecord() {
        String sql = "UPDATE cursa SET data_plecare=?, ora_plecare=?, id_autogara=?, id_rutafixa=? WHERE id_cursa=?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDate(1, Date.valueOf(tfDataPlecare.getText()));
            pst.setTime(2, Time.valueOf(tfOraPlecare.getText()));
            pst.setInt(3, Integer.parseInt(tfIDAutogara.getText()));
            pst.setInt(4, Integer.parseInt(tfIDRutaFixa.getText()));
            pst.setInt(5, Integer.parseInt(tfID.getText()));
            pst.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Cursa actualizata!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Eroare: " + ex.getMessage());
        }
    }

    private void deleteRecord() {
        String sql = "DELETE FROM cursa WHERE id_cursa = ?";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, Integer.parseInt(tfID.getText()));
            pst.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Cursa stearsa!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Eroare: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CursaView().setVisible(true));
    }
}