package viewer;

import model.Autogara;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AutogaraTableModel extends AbstractTableModel {

    private List<Autogara> autogaraList;
    // Am adaugat "Adresa" conform scriptului SQL trimis
    private final String[] colNames = {"ID", "Nume", "Adresa", "Oras"};

    public AutogaraTableModel(List<Autogara> autogaraList) {
        this.autogaraList = autogaraList;
    }

    public void setAutogaraList(List<Autogara> autogaraList) {
        this.autogaraList = autogaraList;
        fireTableDataChanged();
    }

    public Autogara getAutogaraAt(int row) {
        return (row >= 0 && row < autogaraList.size()) ? autogaraList.get(row) : null;
    }

    @Override
    public int getRowCount() { return autogaraList.size(); }

    @Override
    public int getColumnCount() { return colNames.length; }

    @Override
    public String getColumnName(int col) { return colNames[col]; }

    @Override
    public Object getValueAt(int row, int col) {
        Autogara a = autogaraList.get(row);
        return switch (col) {
            case 0 -> a.getIdAutogara();
            case 1 -> a.getNume();
            case 2 -> a.getAdresa(); // Asigura-te ca adaugi acest camp in model!
            case 3 -> a.getOras();
            default -> null;
        };
    }
}