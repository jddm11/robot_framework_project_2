package bo.edu.ucbcba.videoclub.view;

import bo.edu.ucbcba.videoclub.controller.UserController;
import bo.edu.ucbcba.videoclub.model.User;
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
 * Created by privado on 26/06/2016.
 */
public class ViewUsersForm extends JFrame {
    private JPanel rootPanel;
    private JTable usersTable;
    private JTextField searchText;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton Export;
    private JButton Print;
    private UserController userController;

    public ViewUsersForm(JFrame parent) {
        super("View Clients");
        setContentPane(rootPanel);
        setSize(600, 400);
        this.setLocationRelativeTo(null);
        pack();
        setResizable(false);
        launchImage();
        userController = new UserController();
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
                excel(usersTable);
            }
        });

        Print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                utilJTablePrint(usersTable, "Users", "End",
                        true);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, " Are You sure you want to Delete this user? ", " Warning! ", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    deleteUser();
                    populateTable("");
                    searchText.setText("");
                }
            }
        });
    }

    private void launchImage() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("probando5.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(myPicture));
        ((JPanel) getContentPane()).setOpaque(false);
        getLayeredPane().add(label, JLayeredPane.FRAME_CONTENT_LAYER);
        label.setBounds(0, 0, myPicture.getWidth(), myPicture.getHeight());
    }

    private void populateTable() {
        List<User> users = userController.searchUser(searchText.getText());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Username");

        usersTable.setModel(model);

        for (User u : users) {
            Object[] row = new Object[1];
            row[0] = u.getUsername();
            model.addRow(row);
        }
    }

    private void populateTable(String g) {
        List<User> users = userController.searchUser(g);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Username");

        usersTable.setModel(model);

        for (User u : users) {
            Object[] row = new Object[1];
            row[0] = u.getUsername();
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
        // We define the print mode (Definimos el modo de impresión)
        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
        try {
            // Print the table (Imprimo la <span id="IL_AD1" class="IL_AD">tabla</span>)
            boolean complete = jTable.print(mode,
                    new MessageFormat(header),
                    new MessageFormat(footer),
                    showPrintDialog,
                    null,
                    interactive);
            if (complete) {
                // Mostramos el mensaje de impresión existosa
                JOptionPane.showMessageDialog(jTable,
                        "Print complete",
                        "Print result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Mostramos un mensaje indicando que la impresión fue cancelada
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

    private void deleteUser() {

        if (searchText.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username to delete is empty ", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        } else {
            switch (userController.deleteUser(searchText.getText())) {
                case 1:
                    JOptionPane.showMessageDialog(this, "User Deleted successfully", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 2:
                    JOptionPane.showMessageDialog(this, "No User has the username: '" + searchText.getText() + "'", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    break;

                default:
                    break;
            }
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(6, 7, new Insets(30, 20, 20, 10), -1, -1));
        rootPanel.setBackground(new Color(-3090213));
        rootPanel.setForeground(new Color(-3090213));
        panel1.add(rootPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font("Courier New", Font.BOLD, 26));
        label1.setForeground(new Color(-4477584));
        label1.setText("Staff Users List");
        rootPanel.add(label1, new GridConstraints(0, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usersTable = new JTable();
        usersTable.setEnabled(false);
        usersTable.setFocusable(true);
        usersTable.setFont(new Font("Courier New", usersTable.getFont().getStyle(), 16));
        usersTable.setForeground(new Color(-11948357));
        usersTable.setIntercellSpacing(new Dimension(2, 1));
        usersTable.setRowHeight(22);
        usersTable.setRowMargin(1);
        usersTable.setUpdateSelectionOnSort(false);
        usersTable.setVerifyInputWhenFocusTarget(true);
        rootPanel.add(usersTable, new GridConstraints(3, 0, 3, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(800, 300), null, 0, false));
        searchText = new JTextField();
        searchText.setFont(new Font("Courier New", searchText.getFont().getStyle(), 16));
        rootPanel.add(searchText, new GridConstraints(1, 0, 1, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchButton = new JButton();
        searchButton.setBackground(new Color(-12828863));
        searchButton.setFont(new Font("Courier New", Font.BOLD, 18));
        searchButton.setForeground(new Color(-4486332));
        searchButton.setIcon(new ImageIcon(getClass().getResource("/icons/magnifier.png")));
        searchButton.setText("Search");
        rootPanel.add(searchButton, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font("Courier New", label2.getFont().getStyle(), 20));
        label2.setForeground(new Color(-4486332));
        label2.setText(" Username: ");
        rootPanel.add(label2, new GridConstraints(2, 0, 1, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setBackground(new Color(-12828863));
        deleteButton.setFont(new Font("Courier New", Font.BOLD, 18));
        deleteButton.setForeground(new Color(-4486332));
        deleteButton.setIcon(new ImageIcon(getClass().getResource("/icons/minus-sign.png")));
        deleteButton.setText("Delete");
        rootPanel.add(deleteButton, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Export = new JButton();
        Export.setBackground(new Color(-12828863));
        Export.setFont(new Font("Courier New", Font.BOLD, 16));
        Export.setForeground(new Color(-4486332));
        Export.setIcon(new ImageIcon(getClass().getResource("/icons/spreadsheet-cell-row.png")));
        Export.setText("Export Current User List");
        rootPanel.add(Export, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Print = new JButton();
        Print.setBackground(new Color(-12828863));
        Print.setFont(new Font("Courier New", Font.BOLD, 18));
        Print.setForeground(new Color(-4486332));
        Print.setIcon(new ImageIcon(getClass().getResource("/icons/printer.png")));
        Print.setText("Print");
        rootPanel.add(Print, new GridConstraints(5, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }
}
