package bo.edu.ucbcba.videoclub.view;

import bo.edu.ucbcba.videoclub.controller.CompanyController;
import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.Company;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Samuel on 29/05/2016.
 */
public class RegisterCompanyForm extends JDialog {
    private JPanel rootPanel;
    private JTextField nameTextField;
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox CountrycomboBox;
    private JTable CompanyTable;
    private JButton exportButton;
    private CompanyController controller;

    public RegisterCompanyForm(HomeGamesForm parent) {
        super(parent, "Register Company");
        setContentPane(rootPanel);
        setSize(500, 600);
        this.setLocationRelativeTo(null);
        setResizable(false);
        launchImage();
        this.setLocationRelativeTo(null);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCompany();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                export(CompanyTable);
            }
        });
        controller = new CompanyController();
        populateTable();

    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void saveCompany() {
        try {
            String name = nameTextField.getText();
            String country = (String) CountrycomboBox.getSelectedItem();
            controller.create(name, country);
            JOptionPane.showMessageDialog(this, "Company created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            populateTable();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateTable() {
        List<Company> companies;
        companies = controller.getAllCompanies();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Country");
        CompanyTable.setModel(model);
        for (Company c : companies) {
            Object[] row = new Object[2];
            row[0] = c.getName();
            row[1] = c.getCountry();
            model.addRow(row);
        }
    }

    private void launchImage() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("companyBG.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(new ImageIcon(myPicture));
        ((JPanel) getContentPane()).setOpaque(false);
        getLayeredPane().add(label, JLayeredPane.FRAME_CONTENT_LAYER);
        label.setBounds(0, 0, myPicture.getWidth(), myPicture.getHeight());

    }

    public void export(JTable table) {
        {
            JOptionPane.showMessageDialog(null, "Select the folder where you want to save it" + "\n" + "and enter the name of the new file");
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
        rootPanel.setLayout(new GridLayoutManager(10, 11, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setFont(new Font("Courier New", rootPanel.getFont().getStyle(), 20));
        nameTextField = new JTextField();
        rootPanel.add(nameTextField, new GridConstraints(3, 2, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, label1.getFont().getSize()));
        label1.setForeground(new Color(-4420796));
        label1.setText("Name");
        rootPanel.add(label1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CountrycomboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Australia");
        defaultComboBoxModel1.addElement("Austria");
        defaultComboBoxModel1.addElement("Beralus");
        defaultComboBoxModel1.addElement("Belgium");
        defaultComboBoxModel1.addElement("Brazil");
        defaultComboBoxModel1.addElement("Bulgaria");
        defaultComboBoxModel1.addElement("Canada");
        defaultComboBoxModel1.addElement("Chile");
        defaultComboBoxModel1.addElement("China");
        defaultComboBoxModel1.addElement("Croatia");
        defaultComboBoxModel1.addElement("Czech Republic");
        defaultComboBoxModel1.addElement("Denmark");
        defaultComboBoxModel1.addElement("Estonia");
        defaultComboBoxModel1.addElement("Finland");
        defaultComboBoxModel1.addElement("France");
        defaultComboBoxModel1.addElement("Germany");
        defaultComboBoxModel1.addElement("Greece");
        defaultComboBoxModel1.addElement("Hungary");
        defaultComboBoxModel1.addElement("India");
        defaultComboBoxModel1.addElement("Ireland");
        defaultComboBoxModel1.addElement("Italy");
        defaultComboBoxModel1.addElement("Japan");
        defaultComboBoxModel1.addElement("Netherlands");
        defaultComboBoxModel1.addElement("New Zealand");
        defaultComboBoxModel1.addElement("Norway");
        defaultComboBoxModel1.addElement("Pakistan");
        defaultComboBoxModel1.addElement("Philippines");
        defaultComboBoxModel1.addElement("Poland");
        defaultComboBoxModel1.addElement("Romania");
        defaultComboBoxModel1.addElement("Russia");
        defaultComboBoxModel1.addElement("Serbia");
        defaultComboBoxModel1.addElement("Slovakia");
        defaultComboBoxModel1.addElement("Slovenia");
        defaultComboBoxModel1.addElement("South Korea");
        defaultComboBoxModel1.addElement("Spain");
        defaultComboBoxModel1.addElement("Sweden");
        defaultComboBoxModel1.addElement("Taiwan");
        defaultComboBoxModel1.addElement("Thailand");
        defaultComboBoxModel1.addElement("Turkey");
        defaultComboBoxModel1.addElement("Ukraine");
        defaultComboBoxModel1.addElement("United Kingdom");
        defaultComboBoxModel1.addElement("United States");
        CountrycomboBox.setModel(defaultComboBoxModel1);
        rootPanel.add(CountrycomboBox, new GridConstraints(4, 2, 1, 8, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), Font.BOLD, label2.getFont().getSize()));
        label2.setForeground(new Color(-4420796));
        label2.setText("Country");
        rootPanel.add(label2, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CompanyTable = new JTable();
        rootPanel.add(CompanyTable, new GridConstraints(7, 2, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        exportButton = new JButton();
        exportButton.setBackground(new Color(-12828863));
        exportButton.setFont(new Font("Courier New", exportButton.getFont().getStyle(), 18));
        exportButton.setForeground(new Color(-4486332));
        exportButton.setIcon(new ImageIcon(getClass().getResource("/icons/spreadsheet-cell-row.png")));
        exportButton.setText("Export");
        rootPanel.add(exportButton, new GridConstraints(8, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setBackground(new Color(-12828863));
        saveButton.setForeground(new Color(-4420796));
        saveButton.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
        saveButton.setText("Save");
        rootPanel.add(saveButton, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-12828863));
        cancelButton.setForeground(new Color(-4420796));
        cancelButton.setIcon(new ImageIcon(getClass().getResource("/icons/remove-symbol.png")));
        cancelButton.setText("Cancel");
        rootPanel.add(cancelButton, new GridConstraints(5, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(7, 10, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        rootPanel.add(spacer3, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        rootPanel.add(spacer4, new GridConstraints(9, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        rootPanel.add(spacer5, new GridConstraints(5, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setFont(new Font(panel1.getFont().getName(), Font.BOLD, 18));
        rootPanel.add(panel1, new GridConstraints(6, 2, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setEnabled(true);
        label3.setFont(new Font(label3.getFont().getName(), Font.BOLD, label3.getFont().getSize()));
        label3.setText("Name");
        panel1.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font(label4.getFont().getName(), Font.BOLD, label4.getFont().getSize()));
        label4.setText("Country");
        panel1.add(label4, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel1.add(spacer6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        rootPanel.add(spacer7, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setFont(new Font("Courier New", label5.getFont().getStyle(), 20));
        label5.setForeground(new Color(-4486332));
        label5.setHorizontalAlignment(0);
        label5.setText("Register Company");
        rootPanel.add(label5, new GridConstraints(1, 5, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
