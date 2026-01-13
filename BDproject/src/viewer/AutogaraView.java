package viewer;

import dao.DatabaseAutogara;
import model.Autogara;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AutogaraView extends JFrame {

    private JTable table;
    private AutogaraTableModel model;
    private DatabaseAutogara db;

    private JTextField txtId, txtNume, txtAdresa, txtOras;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnSearch;

    public AutogaraView() {
        setTitle("Gestionare Autogari");
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
        model = new AutogaraTableModel(new ArrayList<>());
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel pentru campuri (NORTH) - exact ca in exemplul tau
        JPanel formPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        txtId = new JTextField();
        txtNume = new JTextField();
        txtAdresa = new JTextField();
        txtOras = new JTextField();

        formPanel.add(new JLabel("ID Autogara:"));
        formPanel.add(new JLabel("Nume:"));
        formPanel.add(new JLabel("Adresa:"));
        formPanel.add(new JLabel("Oras:"));

        formPanel.add(txtId);
        formPanel.add(txtNume);
        formPanel.add(txtAdresa);
        formPanel.add(txtOras);

        // Panel pentru butoane (SOUTH) - insirate exact ca in cerinta
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

        // Evenimente
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int index = table.getSelectedRow();
                if (index >= 0) {
                    Autogara a = model.getAutogaraAt(index);
                    txtId.setText(String.valueOf(a.getIdAutogara()));
                    txtNume.setText(a.getNume());
                    txtAdresa.setText(a.getAdresa());
                    txtOras.setText(a.getOras());
                }
            }
        });

        btnRefresh.addActionListener(e -> loadData());
        btnSearch.addActionListener(e -> searchRecords());
        btnAdd.addActionListener(e -> addAutogara());
        btnUpdate.addActionListener(e -> updateAutogara());
        btnDelete.addActionListener(e -> deleteAutogara());
    }

    private void loadData() {
        try (Connection con = db.connect()) {
            List<Autogara> autogari = db.citesteAutogari(con);
            model.setAutogaraList(autogari);
            model.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare incarcare: " + e.getMessage());
        }
    }

    // Metoda de cautare multi-criteriu (Exact ca in exemplul tau)
    private void searchRecords() {
        try (Connection con = db.connect()) {
            String sql = "SELECT * FROM Autogara WHERE 1=1";

            if (!txtId.getText().trim().isEmpty()) sql += " AND ID_autogara = ?";
            if (!txtNume.getText().trim().isEmpty()) sql += " AND Nume ILIKE ?";
            if (!txtAdresa.getText().trim().isEmpty()) sql += " AND Adresa ILIKE ?";
            if (!txtOras.getText().trim().isEmpty()) sql += " AND Oras ILIKE ?";

            PreparedStatement pst = con.prepareStatement(sql);
            int idx = 1;

            if (!txtId.getText().trim().isEmpty()) pst.setInt(idx++, Integer.parseInt(txtId.getText().trim()));
            if (!txtNume.getText().trim().isEmpty()) pst.setString(idx++, "%" + txtNume.getText().trim() + "%");
            if (!txtAdresa.getText().trim().isEmpty()) pst.setString(idx++, "%" + txtAdresa.getText().trim() + "%");
            if (!txtOras.getText().trim().isEmpty()) pst.setString(idx++, "%" + txtOras.getText().trim() + "%");

            ResultSet rs = pst.executeQuery();
            List<Autogara> rezultate = new ArrayList<>();
            while (rs.next()) {
                rezultate.add(new Autogara(rs.getInt("ID_autogara"), rs.getString("Nume"), rs.getString("Adresa"), rs.getString("Oras")));
            }
            model.setAutogaraList(rezultate);
            model.fireTableDataChanged();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Eroare la cautare: " + ex.getMessage());
        }
    }

    private void addAutogara() {
        String sql = "INSERT INTO Autogara (ID_autogara, Nume, Adresa, Oras) VALUES (?, ?, ?, ?)";
        try (Connection con = db.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(txtId.getText().trim()));
            ps.setString(2, txtNume.getText().trim());
            ps.setString(3, txtAdresa.getText().trim());
            ps.setString(4, txtOras.getText().trim());
            ps.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Inserat cu succes!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare: " + e.getMessage());
        }
    }

    private void updateAutogara() {
        String sql = "UPDATE Autogara SET Nume = ?, Adresa = ?, Oras = ? WHERE ID_autogara = ?";
        try (Connection con = db.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, txtNume.getText().trim());
            ps.setString(2, txtAdresa.getText().trim());
            ps.setString(3, txtOras.getText().trim());
            ps.setInt(4, Integer.parseInt(txtId.getText().trim()));
            ps.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Actualizat cu succes!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare: " + e.getMessage());
        }
    }

    private void deleteAutogara() {
        String sql = "DELETE FROM Autogara WHERE ID_autogara = ?";
        try (Connection con = db.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(txtId.getText().trim()));
            ps.executeUpdate();
            loadData();
            JOptionPane.showMessageDialog(this, "Sters cu succes!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Eroare: " + e.getMessage());
        }
    }

    private static class AutogaraTableModel extends javax.swing.table.AbstractTableModel {
        private List<Autogara> list;
        private String[] colNames = {"ID Autogara", "Nume", "Adresa", "Oras"};

        public AutogaraTableModel(List<Autogara> list) { this.list = list; }
        public void setAutogaraList(List<Autogara> list) { this.list = list; }
        public Autogara getAutogaraAt(int row) { return list.get(row); }
        @Override public int getRowCount() { return list.size(); }
        @Override public int getColumnCount() { return colNames.length; }
        @Override public String getColumnName(int col) { return colNames[col]; }
        @Override public Object getValueAt(int row, int col) {
            Autogara a = list.get(row);
            return switch (col) {
                case 0 -> a.getIdAutogara();
                case 1 -> a.getNume();
                case 2 -> a.getAdresa();
                case 3 -> a.getOras();
                default -> null;
            };
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AutogaraView().setVisible(true));
    }
}