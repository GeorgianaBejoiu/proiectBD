package viewer;

import dao.DatabaseAutogara;
import model.Autobuz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutobuzView extends JFrame {

    private JTable table;
    private AutobuzTableModel model;
    private DatabaseAutogara db;

    private JTextField txtId, txtNumar, txtCapacitate;
    private JButton btnRefresh, btnSearch, btnAdd, btnUpdate, btnDelete;

    public AutobuzView() {
        setTitle("Gestionare Autobuze");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        db = new DatabaseAutogara();

        initComponents();
        loadData();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        // Tabelul
        model = new AutobuzTableModel(new ArrayList<>());
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel pentru campuri (NORTH) - Stil unitar
        JPanel formPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        txtId = new JTextField();
        txtNumar = new JTextField();
        txtCapacitate = new JTextField();

        formPanel.add(new JLabel("ID Autobuz:"));
        formPanel.add(new JLabel("Numar Inmatriculare:"));
        formPanel.add(new JLabel("Capacitate:"));

        formPanel.add(txtId);
        formPanel.add(txtNumar);
        formPanel.add(txtCapacitate);

        // Panel pentru butoane (SOUTH) - Insirate exact ca la celelalte
        JPanel buttonPanel = new JPanel();
        btnRefresh = new JButton("Refresh");
        btnSearch = new JButton("Search");
        btnAdd = new JButton("Insert");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Eveniment selectie tabel
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = table.getSelectedRow();
                if (index >= 0) {
                    Autobuz a = model.getAutobuzAt(index);
                    txtId.setText(String.valueOf(a.getIdAutobuz()));
                    txtNumar.setText(a.getNumarInmatriculare());
                    txtCapacitate.setText(String.valueOf(a.getCapacitate()));
                }
            }
        });

        // Action Listeners
        btnRefresh.addActionListener(e -> loadData());
        btnSearch.addActionListener(e -> searchRecords());
        btnAdd.addActionListener(e -> addAutobuz());
        btnUpdate.addActionListener(e -> updateAutobuz());
        btnDelete.addActionListener(e -> deleteAutobuz());
    }

    private void loadData() {
        try (Connection con = db.connect()) {
            List<Autobuz> autobuze = db.citesteAutobuze(con);
            model.setAutobuzList(autobuze);
            model.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare incarcare: " + e.getMessage());
        }
    }

    // Cautare dupa orice criteriu
    private void searchRecords() {
        try (Connection con = db.connect()) {
            String sql = "SELECT * FROM Autobuz WHERE 1=1";

            if (!txtId.getText().trim().isEmpty()) sql += " AND ID_autobuz = ?";
            if (!txtNumar.getText().trim().isEmpty()) sql += " AND Numar_inmatriculare ILIKE ?";
            if (!txtCapacitate.getText().trim().isEmpty()) sql += " AND Capacitate = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            int idx = 1;

            if (!txtId.getText().trim().isEmpty()) pst.setInt(idx++, Integer.parseInt(txtId.getText().trim()));
            if (!txtNumar.getText().trim().isEmpty()) pst.setString(idx++, "%" + txtNumar.getText().trim() + "%");
            if (!txtCapacitate.getText().trim().isEmpty()) pst.setInt(idx++, Integer.parseInt(txtCapacitate.getText().trim()));

            ResultSet rs = pst.executeQuery();
            List<Autobuz> rezultate = new ArrayList<>();
            while (rs.next()) {
                Autobuz a = new Autobuz();
                a.setIdAutobuz(rs.getInt("ID_autobuz"));
                a.setNumarInmatriculare(rs.getString("Numar_inmatriculare"));
                a.setCapacitate(rs.getInt("Capacitate"));
                rezultate.add(a);
            }
            model.setAutobuzList(rezultate);
            model.fireTableDataChanged();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Eroare la cautare: " + ex.getMessage());
        }
    }

    private void addAutobuz() {
        String sql = "INSERT INTO Autobuz (ID_autobuz, Numar_inmatriculare, Capacitate) VALUES (?, ?, ?)";
        try (Connection con = db.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(txtId.getText().trim()));
            ps.setString(2, txtNumar.getText().trim());
            ps.setInt(3, Integer.parseInt(txtCapacitate.getText().trim()));
            ps.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Autobuz inserat!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare: " + e.getMessage());
        }
    }

    private void updateAutobuz() {
        String sql = "UPDATE Autobuz SET Numar_inmatriculare = ?, Capacitate = ? WHERE ID_autobuz = ?";
        try (Connection con = db.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, txtNumar.getText().trim());
            ps.setInt(2, Integer.parseInt(txtCapacitate.getText().trim()));
            ps.setInt(3, Integer.parseInt(txtId.getText().trim()));
            ps.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Autobuz actualizat!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare: " + e.getMessage());
        }
    }

    private void deleteAutobuz() {
        String sql = "DELETE FROM Autobuz WHERE ID_autobuz = ?";
        try (Connection con = db.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(txtId.getText().trim()));
            ps.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Autobuz sters!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare: " + e.getMessage());
        }
    }

    private static class AutobuzTableModel extends javax.swing.table.AbstractTableModel {
        private List<Autobuz> autobuzList;
        private String[] colNames = {"ID Autobuz", "Numar Inmatriculare", "Capacitate"};

        public AutobuzTableModel(List<Autobuz> autobuzList) { this.autobuzList = autobuzList; }
        public void setAutobuzList(List<Autobuz> autobuzList) { this.autobuzList = autobuzList; }
        public Autobuz getAutobuzAt(int row) { return autobuzList.get(row); }
        @Override public int getRowCount() { return autobuzList.size(); }
        @Override public int getColumnCount() { return colNames.length; }
        @Override public String getColumnName(int column) { return colNames[column]; }
        @Override public Object getValueAt(int rowIndex, int columnIndex) {
            Autobuz a = autobuzList.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> a.getIdAutobuz();
                case 1 -> a.getNumarInmatriculare();
                case 2 -> a.getCapacitate();
                default -> null;
            };
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AutobuzView().setVisible(true));
    }
}