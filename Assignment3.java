import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
        addMouseListenerToTable();
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

    private void editBook()
    {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) 
        {
            showMessage("Select a book to edit.", "Error");
            return;
        }

        String title = JOptionPane.showInputDialog("Edit Title:", model.getValueAt(selectedRow, 1));
        String author = JOptionPane.showInputDialog("Edit Author:", model.getValueAt(selectedRow, 2));
        int year = Integer.parseInt(JOptionPane.showInputDialog("Edit Year:", model.getValueAt(selectedRow, 3)));

        model.setValueAt(title, selectedRow, 1);
        model.setValueAt(author, selectedRow, 2);
        model.setValueAt(year, selectedRow, 3);
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
        p.add(createButton("Edit Book", e -> editBook()));
        p.add(createButton("Save Data", e -> saveData()));
        p.add(createButton("Load Data", e -> loadData()));
        return p;
    }
    private void saveData() 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("library_data.txt"))) 
        {
            for (int row = 0; row < model.getRowCount(); row++) {
                String id = model.getValueAt(row, 0).toString();
                String title = model.getValueAt(row, 1).toString();
                String author = model.getValueAt(row, 2).toString();
                String year = model.getValueAt(row, 3).toString();
                String line = id + "," + title + "," + author + "," + year;
                writer.write(line);
                writer.newLine();
            }
            showMessage("Data saved successfully.", "Data Saved");
        } catch (IOException e) 
        {
            showMessage("Error saving data.", "Error");
        }
    }
    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("library_data.txt"))) {
            model.setRowCount(0);
            String line;
            int id = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String title = parts[1];
                    String author = parts[2];
                    String year = parts[3];
                    model.addRow(new Object[]{id, title, author, year});
                    id++;
                }
            }
            showMessage("Data loaded successfully.", "Data Loaded");
        } catch (IOException e) 
        {
            showMessage("Error loading data.", "Error");
        }
    }
        private void addMouseListenerToTable() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(row, row);
            }
        });
    }
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new Assignment3());
    }
}