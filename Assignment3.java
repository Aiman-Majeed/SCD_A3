import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Assignment3 
{

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
        frame.add(createButtonPanel(), BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void addBook()
    {
        String title = JOptionPane.showInputDialog("Enter Title:");
        String author = JOptionPane.showInputDialog("Enter Author:");
        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter Publication Year:"));
        model.addRow(new Object[]{model.getRowCount() + 1, title, author, year});
    }

    private void deleteBook() 
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) 
        {
            showMessage("Select a book to delete.", "Error");
            return;
        }
        int confirmDelete = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this book?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirmDelete == JOptionPane.YES_OPTION)
        {
            model.removeRow(selectedRow);
        }
    }

    private void showMessage(String message, String title) 
    {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private JButton createButton(String text, ActionListener listener)
    {
        JButton b = new JButton(text);
        b.addActionListener(listener);
        return b;
    }

    private JPanel createButtonPanel() 
    {
        JPanel p = new JPanel();
        p.add(createButton("Add Book", e -> addBook()));
        p.add(createButton("Delete Book", e -> deleteBook()));
        return p;
    }
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new Assignment3());
    }
}