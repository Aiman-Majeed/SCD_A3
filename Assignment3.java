import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Assignment3 {

    public JFrame frame;
    public JTable table;
    public DefaultTableModel model;

    public Assignment3()
    {
        frame = new JFrame("Library Management System");
        frame.setSize(800, 400);
        model = new DefaultTableModel(new Object[]{"ID", "Title", "Author", "Year"}, 0);
        table = new JTable(model);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Assignment3());
    }
}