package viewer;

import model.Cursa;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.text.SimpleDateFormat;

public class CursaTableModel extends AbstractTableModel {

    private List<Cursa> cursaList;
    private final String[] colNames = {"ID Cursa", "Data Plecare", "Ora Plecare", "Autogara", "ID RutaFixa", "ID Autobuz"};

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    public CursaTableModel(List<Cursa> cursaList) {
        this.cursaList = cursaList;
    }

    public void setCursaList(List<Cursa> cursaList) {
        this.cursaList = cursaList;
    }

    public Cursa getCursaAt(int row) {
        return cursaList.get(row);
    }

    @Override
    public int getRowCount() {
        return cursaList.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cursa c = cursaList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> c.getIdCursa();
            case 1 -> c.getDataPlecare() != null ? dateFormat.format(c.getDataPlecare()) : "";
            case 2 -> c.getOraPlecare() != null ? timeFormat.format(c.getOraPlecare()) : "";
            case 3 -> c.getAutogara() != null ? c.getAutogara().getNume() + " (" + c.getAutogara().getOras() + ")" : "N/A";
            case 4 -> c.getIdRutaFixa();
            case 5 -> c.getIdAutobuz() != null ? c.getIdAutobuz() : "N/A";
            default -> null;
        };
    }
}
