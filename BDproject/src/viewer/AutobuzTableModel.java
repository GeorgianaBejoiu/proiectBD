package viewer;

import model.Autobuz;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AutobuzTableModel extends AbstractTableModel {

    private List<Autobuz> autobuzList;
    private String[] colNames = {"ID Autobuz", "Numar Inmatriculare", "Capacitate"};

    public AutobuzTableModel(List<Autobuz> autobuzList) {
        this.autobuzList = autobuzList;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public int getRowCount() {
        return autobuzList.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if(row < 0 || row >= autobuzList.size()) return null;
        Autobuz a = autobuzList.get(row);
        switch(column) {
            case 0: return a.getIdAutobuz();
            case 1: return a.getNumarInmatriculare();
            case 2: return a.getCapacitate();
            default: return null;
        }
    }
}
