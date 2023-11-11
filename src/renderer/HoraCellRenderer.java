/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package renderer;

/**
 *
 * @author edson
 */
import javax.swing.table.DefaultTableCellRenderer;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.Component;
import javax.swing.JTable;

public class HoraCellRenderer extends DefaultTableCellRenderer {
    private DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof LocalTime) {
            LocalTime hora = (LocalTime) value;
            value = hora.format(horaFormatter);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
