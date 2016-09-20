package bo.edu.ucbcba.videoclub.view;

import bo.edu.ucbcba.videoclub.controller.ClientController;
import bo.edu.ucbcba.videoclub.model.Client;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by privado on 16/05/2016.
 */
public class ViewClientsForm extends JFrame {
    private JTable clientsTable;
    private JTextField searchText;
    private JButton searchButton;
    private JPanel rootPanel;
    private JButton Export;
    private JButton Print;
    private ClientController clientController;

    public ViewClientsForm(JFrame parent) {
        super("View Clients");
        setContentPane(rootPanel);
        setSize(600, 400);
        this.setLocationRelativeTo(null);
        pack();
        setResizable(false);
        launchImage();
        clientController = new ClientController();
        populateTable();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateTable();
            }
        });

        Export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excel(clientsTable);
            }
        });

        Print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utilJTablePrint(clientsTable, "Clients", "End",
                        true);
            }
        });
    }

    private void launchImage() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("probando10.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(new ImageIcon(myPicture));
        ((JPanel) getContentPane()).setOpaque(false);
        getLayeredPane().add(label, JLayeredPane.FRAME_CONTENT_LAYER);
        label.setBounds(0, 0, myPicture.getWidth(), myPicture.getHeight());
    }


    private void populateTable() {
        List<Client> clients = clientController.searchClient(searchText.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CI");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Address");

        clientsTable.setModel(model);

        for (Client c : clients) {
            Object[] row = new Object[4];

            row[0] = c.getCi();
            row[1] = c.getFirstname();
            row[2] = c.getLastname();
            row[3] = c.getAddress();
            model.addRow(row);
        }
    }

    public void toExcel(JTable tabla, File ficheroXLS) throws IOException {
        TableModel modelo = tabla.getModel();
        FileWriter fichero = new FileWriter(ficheroXLS);

        for (int i = 0; i < modelo.getColumnCount(); i++) {
            fichero.write(modelo.getColumnName(i) + "\t");
        }
        fichero.write("\n");
        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                fichero.write(modelo.getValueAt(i, j).toString() + "\t");
            }
            fichero.write("\n");
        }
        fichero.close();
    }

    public void excel(JTable table) {
        {
            JFileChooser fc = new JFileChooser();
            int option = fc.showSaveDialog(table);
            if (option == JFileChooser.APPROVE_OPTION) {
                String filename = fc.getSelectedFile().getName();
                String path = fc.getSelectedFile().getParentFile().getPath();
                int len = filename.length();
                String ext = "";
                String file = "";
                if (len > 4) {
                    ext = filename.substring(len - 4, len);
                }
                if (ext.equals(".xls")) {
                    file = path + "\\" + filename;
                } else {
                    file = path + "\\" + filename + ".xls";
                }
                try {
                    toExcel(table, new File(file));
                    JOptionPane.showMessageDialog(this, "The table was exported successfully");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "\n" +
                            "An error has occurred when exporting", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void utilJTablePrint(JTable jTable, String header, String footer, boolean showPrintDialog) {
        boolean fitWidth = true;
        boolean interactive = true;
        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
        try {
            boolean complete = jTable.print(mode,
                    new MessageFormat(header),
                    new MessageFormat(footer),
                    showPrintDialog,
                    null,
                    interactive);
            if (complete) {
                JOptionPane.showMessageDialog(jTable,
                        "Print complete",
                        "Print result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(jTable,
                        "Print canceled",
                        "Print result",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(jTable,
                    "Print fail " + pe.getMessage(),
                    "Print result ",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(5, 7, new Insets(30, 20, 20, 10), -1, -1));
        rootPanel.setBackground(new Color(-3090213));
        rootPanel.setForeground(new Color(-3090213));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font("Courier New", Font.BOLD, 30));
        label1.setForeground(new Color(-4477584));
        label1.setText("Clients List");
        rootPanel.add(label1, new GridConstraints(0, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clientsTable = new JTable();
        clientsTable.setEnabled(false);
        clientsTable.setFocusable(true);
        clientsTable.setFont(new Font("Courier New", clientsTable.getFont().getStyle(), 16));
        clientsTable.setForeground(new Color(-11948357));
        clientsTable.setRowHeight(20);
        clientsTable.setUpdateSelectionOnSort(false);
        clientsTable.setVerifyInputWhenFocusTarget(true);
        rootPanel.add(clientsTable, new GridConstraints(3, 0, 2, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(800, 300), null, 0, false));
        searchText = new JTextField();
        searchText.setFont(new Font("Courier New", searchText.getFont().getStyle(), 16));
        rootPanel.add(searchText, new GridConstraints(1, 0, 1, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchButton = new JButton();
        searchButton.setBackground(new Color(-12828863));
        searchButton.setFont(new Font("Courier New", Font.BOLD, 18));
        searchButton.setForeground(new Color(-4486332));
        searchButton.setIcon(new ImageIcon(getClass().getResource("/icons/magnifier.png")));
        searchButton.setText("Search");
        rootPanel.add(searchButton, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 35), new Dimension(-1, 35), new Dimension(-1, 35), 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font("Courier New", Font.BOLD, 18));
        label2.setForeground(new Color(-4486332));
        label2.setText("CI");
        rootPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font("Courier New", Font.BOLD, 18));
        label3.setForeground(new Color(-4486332));
        label3.setText("Last Name");
        rootPanel.add(label3, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font("Courier New", Font.BOLD, 18));
        label4.setForeground(new Color(-4486332));
        label4.setText("Address");
        rootPanel.add(label4, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setFont(new Font("Courier New", Font.BOLD, 18));
        label5.setForeground(new Color(-4486332));
        label5.setText("First Name");
        rootPanel.add(label5, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText(" ");
        rootPanel.add(label6, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Export = new JButton();
        Export.setBackground(new Color(-12828863));
        Export.setFont(new Font("Courier New", Font.BOLD, 16));
        Export.setForeground(new Color(-4486332));
        Export.setIcon(new ImageIcon(getClass().getResource("/icons/spreadsheet-cell-row.png")));
        Export.setText("Export Current List");
        rootPanel.add(Export, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 40), new Dimension(-1, 40), new Dimension(-1, 40), 0, false));
        Print = new JButton();
        Print.setBackground(new Color(-12828863));
        Print.setFont(new Font("Courier New", Font.BOLD, 18));
        Print.setForeground(new Color(-4486332));
        Print.setIcon(new ImageIcon(getClass().getResource("/icons/printer.png")));
        Print.setText("Print");
        rootPanel.add(Print, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 35), new Dimension(-1, 35), new Dimension(-1, 35), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
